import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { TransaccionService } from '../../../service/TransaccionService';
import { Transaccion, TipoMovimientoCXC, SaldoCuenta } from '../../../entity/Transaccion';

@Component({
  selector: 'app-transaccion-formulario',
  templateUrl: './transaccion-formulario.component.html',
  styleUrls: ['./transaccion-formulario.component.css']
})
export class TransaccionFormularioComponent implements OnInit {
  transaccionForm: FormGroup;
  esEdicion: boolean = false;
  idTransaccion?: number;
  tiposMovimiento: TipoMovimientoCXC[] = [];
  saldoCuenta?: SaldoCuenta;
  cuentaActiva: boolean = true;

  constructor(
    private fb: FormBuilder,
    private transaccionService: TransaccionService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.transaccionForm = this.createForm();
  }

  ngOnInit(): void {
    this.cargarTiposMovimiento();
    this.verificarParametrosRuta();
    this.setupValidaciones();
  }

  createForm(): FormGroup {
    return this.fb.group({
      idSaldoCuenta: ['', [Validators.required, Validators.min(1)]],
      idTipoMovimientoCXC: ['', Validators.required],
      fechaMovimiento: ['', Validators.required],
      valorMovimiento: ['', [Validators.required, Validators.min(0.01)]],
      documentoRespaldo: ['', [Validators.required, Validators.minLength(3)]],
      usuarioRegistro: [this.getCurrentUser(), Validators.required]
    });
  }

  setupValidaciones(): void {
    // Validar estado de cuenta cuando cambia idSaldoCuenta
    this.transaccionForm.get('idSaldoCuenta')?.valueChanges.subscribe(id => {
      if (id) {
        this.verificarEstadoCuenta(id);
        this.obtenerSaldoCuenta(id);
      }
    });
  }

  cargarTiposMovimiento(): void {
    this.transaccionService.getTiposMovimiento().subscribe({
      next: (tipos) => {
        this.tiposMovimiento = tipos;
      },
      error: (error) => {
        console.error('Error al cargar tipos de movimiento:', error);
        alert('Error al cargar tipos de movimiento');
      }
    });
  }

  verificarEstadoCuenta(idSaldoCuenta: number): void {
    this.transaccionService.verificarEstadoCuenta(idSaldoCuenta).subscribe({
      next: (activa) => {
        this.cuentaActiva = activa;
        if (!activa) {
          alert('La cuenta no está activa. No se pueden registrar movimientos.');
        }
      },
      error: (error) => {
        console.error('Error al verificar estado de cuenta:', error);
        this.cuentaActiva = false;
      }
    });
  }

  obtenerSaldoCuenta(idSaldoCuenta: number): void {
    this.transaccionService.getSaldoCuentaById(idSaldoCuenta).subscribe({
      next: (saldo) => {
        this.saldoCuenta = saldo;
      },
      error: (error) => {
        console.error('Error al obtener saldo de cuenta:', error);
      }
    });
  }

  verificarParametrosRuta(): void {
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.esEdicion = true;
        this.idTransaccion = +params['id'];
        this.cargarTransaccion(this.idTransaccion);
      }
    });
  }

  cargarTransaccion(id: number): void {
    this.transaccionService.getTransaccionById(id).subscribe({
      next: (transaccion) => {
        this.transaccionForm.patchValue({
          ...transaccion,
          fechaMovimiento: this.formatDateForInput(transaccion.fechaMovimiento)
        });
        this.verificarEstadoCuenta(transaccion.idSaldoCuenta);
        this.obtenerSaldoCuenta(transaccion.idSaldoCuenta);
      },
      error: (error) => {
        console.error('Error al cargar transacción:', error);
        alert('Error al cargar transacción');
      }
    });
  }

  formatDateForInput(date: any): string {
    const d = new Date(date);
    return d.toISOString().split('T')[0];
  }

  getCurrentUser(): string {
    // En una aplicación real, esto vendría del servicio de autenticación
    return localStorage.getItem('currentUser') || 'admin';
  }

  onSubmit(): void {
    if (this.transaccionForm.valid && this.cuentaActiva) {
      const transaccion: Transaccion = this.transaccionForm.value;

      if (this.esEdicion && this.idTransaccion) {
        this.actualizarTransaccion(this.idTransaccion, transaccion);
      } else {
        this.crearTransaccion(transaccion);
      }
    } else {
      this.marcarControlesComoTocados();
      if (!this.cuentaActiva) {
        alert('No se puede registrar el movimiento porque la cuenta no está activa.');
      }
    }
  }

  crearTransaccion(transaccion: Transaccion): void {
    this.transaccionService.createTransaccion(transaccion).subscribe({
      next: () => {
        alert('Transacción registrada exitosamente');
        this.router.navigate(['/transacciones']);
      },
      error: (error) => {
        console.error('Error al crear transacción:', error);
        alert('Error al registrar transacción');
      }
    });
  }

  actualizarTransaccion(id: number, transaccion: Transaccion): void {
    this.transaccionService.updateTransaccion(id, transaccion).subscribe({
      next: () => {
        alert('Transacción actualizada exitosamente');
        this.router.navigate(['/transacciones']);
      },
      error: (error) => {
        console.error('Error al actualizar transacción:', error);
        alert('Error al actualizar transacción');
      }
    });
  }

  marcarControlesComoTocados(): void {
    Object.keys(this.transaccionForm.controls).forEach(key => {
      const control = this.transaccionForm.get(key);
      control?.markAsTouched();
    });
  }

  cancelar(): void {
    this.router.navigate(['/transacciones']);
  }

  // Getters para facilitar el acceso en el template
  get idSaldoCuenta() { return this.transaccionForm.get('idSaldoCuenta'); }
  get idTipoMovimientoCXC() { return this.transaccionForm.get('idTipoMovimientoCXC'); }
  get fechaMovimiento() { return this.transaccionForm.get('fechaMovimiento'); }
  get valorMovimiento() { return this.transaccionForm.get('valorMovimiento'); }
  get documentoRespaldo() { return this.transaccionForm.get('documentoRespaldo'); }
}
