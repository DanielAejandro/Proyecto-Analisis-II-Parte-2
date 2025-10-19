import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TipoDocumentoService } from '../../../../service/TipoDocumento';
import { ActivatedRoute, Router } from '@angular/router';
import { TipoDocumento } from '../../../../entity/TipoDocumento';

@Component({
  selector: 'app-tipo-documento-formulario',
  standalone: false,
  templateUrl: './tipo-documento-formulario.component.html',
  styleUrl: './tipo-documento-formulario.component.css'
})
export class TipoDocumentoFormularioComponent {

  form!: FormGroup;
  modoEditar = false;
  id!: number;

  constructor(
    private fb: FormBuilder,
    private service: TipoDocumentoService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.modoEditar = !!this.id;

    this.form = this.fb.group({
      nombre: ['', Validators.required]
    });

    if (this.modoEditar) {
      this.service.getById(this.id).subscribe({
        next: (tipoDocumento) => {
          this.form.patchValue({
            nombre: tipoDocumento.nombre
          });
        },
        error: () => alert('Error al cargar tipo de documento')
      });
    }
  }

  guardar(): void {
    const ahora = new Date().toISOString();
    const tipoDocumento: TipoDocumento = {
      nombre: this.form.value.nombre,
      usuarioCreacion: 'admin',
      fechaCreacion: ahora,
      usuarioModificacion: 'admin',
      fechaModificacion: ahora
    };

    if (this.modoEditar) {
      this.service.update(this.id, tipoDocumento).subscribe({
        next: () => this.router.navigate(['/tipo-documento']),
        error: () => alert('Error al actualizar tipo de documento')
      });
    } else {
      this.service.create(tipoDocumento).subscribe({
        next: () => this.router.navigate(['/tipo-documento']),
        error: () => alert('Error al crear tipo de documento')
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/tipo-documento']);
  }
}
