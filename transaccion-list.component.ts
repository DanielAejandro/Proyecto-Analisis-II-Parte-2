import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TransaccionService } from '../../../service/TransaccionService';
import { Transaccion } from '../../../entity/Transaccion';

@Component({
  selector: 'app-transaccion-list',
  templateUrl: './transaccion-list.component.html',
  styleUrls: ['./transaccion-list.component.css']
})
export class TransaccionListComponent implements OnInit {
  transacciones: Transaccion[] = [];
  transaccionesFiltradas: Transaccion[] = [];
  cargando: boolean = true;
  filtro = {
    idSaldoCuenta: '',
    fechaInicio: '',
    fechaFin: ''
  };

  constructor(
    private transaccionService: TransaccionService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.cargarTransacciones();
  }

  cargarTransacciones(): void {
    this.cargando = true;
    this.transaccionService.getTransacciones().subscribe({
      next: (data) => {
        this.transacciones = data;
        this.transaccionesFiltradas = data;
        this.cargando = false;
      },
      error: (error) => {
        console.error('Error al cargar transacciones:', error);
        alert('Error al cargar transacciones');
        this.cargando = false;
      }
    });
  }

  aplicarFiltros(): void {
    this.transaccionesFiltradas = this.transacciones.filter(transaccion => {
      let coincide = true;

      if (this.filtro.idSaldoCuenta) {
        coincide = coincide && transaccion.idSaldoCuenta.toString().includes(this.filtro.idSaldoCuenta);
      }

      if (this.filtro.fechaInicio) {
        const fechaMovimiento = new Date(transaccion.fechaMovimiento);
        const fechaInicio = new Date(this.filtro.fechaInicio);
        coincide = coincide && fechaMovimiento >= fechaInicio;
      }

      if (this.filtro.fechaFin) {
        const fechaMovimiento = new Date(transaccion.fechaMovimiento);
        const fechaFin = new Date(this.filtro.fechaFin);
        coincide = coincide && fechaMovimiento <= fechaFin;
      }

      return coincide;
    });
  }

  limpiarFiltros(): void {
    this.filtro = {
      idSaldoCuenta: '',
      fechaInicio: '',
      fechaFin: ''
    };
    this.transaccionesFiltradas = this.transacciones;
  }

  editarTransaccion(id: number): void {
    this.router.navigate(['/transacciones/editar', id]);
  }

  eliminarTransaccion(id: number): void {
    if (confirm('¿Está seguro de que desea eliminar esta transacción?')) {
      this.transaccionService.deleteTransaccion(id).subscribe({
        next: () => {
          alert('Transacción eliminada exitosamente');
          this.cargarTransacciones();
        },
        error: (error) => {
          console.error('Error al eliminar transacción:', error);
          alert('Error al eliminar transacción');
        }
      });
    }
  }

  nuevaTransaccion(): void {
    this.router.navigate(['/transacciones/nueva']);
  }

  formatearFecha(fecha: any): string {
    return new Date(fecha).toLocaleDateString('es-ES');
  }

  formatearMoneda(valor: number): string {
    return new Intl.NumberFormat('es-ES', {
      style: 'currency',
      currency: 'USD'
    }).format(valor);
  }
}
