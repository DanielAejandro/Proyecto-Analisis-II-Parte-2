// src/app/component/clasificacion-cuenta-formulario/clasificacion-cuenta-formulario.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { ClasificacionCuenta } from '../../../entity/ClasificacionCuenta';
import { ClasificacionCuentaService } from '../../../service/ClasificacionCuentaService';

@Component({
  selector: 'app-clasificacion-cuenta-formulario',
  templateUrl: './clasificacion-cuenta-formulario.component.html',
  styleUrls: ['./clasificacion-cuenta-formulario.component.css']
})
export class ClasificacionCuentaFormularioComponent implements OnInit {
  clasificacion: ClasificacionCuenta = new ClasificacionCuenta();
  isEdit: boolean = false;
  loading: boolean = false;
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private clasificacionService: ClasificacionCuentaService
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.loadClasificacion(Number(id));
    }
  }

  loadClasificacion(id: number): void {
    this.loading = true;
    this.clasificacionService.getClasificacionById(id).subscribe({
      next: (data) => {
        this.clasificacion = data;
        this.loading = false;
      },
      error: (error) => {
        this.errorMessage = 'Error al cargar la clasificación';
        this.loading = false;
        console.error('Error:', error);
      }
    });
  }

  onSubmit(form: NgForm): void {
    if (form.invalid) {
      // Marcar todos los campos como touched para mostrar errores
      Object.keys(form.controls).forEach(key => {
        form.controls[key].markAsTouched();
      });
      return;
    }

    this.loading = true;
    this.errorMessage = '';
    this.successMessage = '';

    if (this.isEdit) {
      this.clasificacionService.updateClasificacion(this.clasificacion.id!, this.clasificacion).subscribe({
        next: (data) => {
          this.successMessage = 'Clasificación actualizada exitosamente';
          this.loading = false;
          setTimeout(() => this.router.navigate(['/clasificacion-cuenta']), 2000);
        },
        error: (error) => {
          this.errorMessage = error.error?.error || 'Error al actualizar la clasificación';
          this.loading = false;
          console.error('Error:', error);
        }
      });
    } else {
      this.clasificacionService.createClasificacion(this.clasificacion).subscribe({
        next: (data) => {
          this.successMessage = 'Clasificación creada exitosamente';
          this.loading = false;
          setTimeout(() => this.router.navigate(['/clasificacion-cuenta']), 2000);
        },
        error: (error) => {
          this.errorMessage = error.error?.error || 'Error al crear la clasificación';
          this.loading = false;
          console.error('Error:', error);
        }
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/clasificacion-cuenta']);
  }

  validateCodigo(): void {
    if (this.clasificacion.codigo && !this.isEdit) {
      this.clasificacionService.getClasificacionByCodigo(this.clasificacion.codigo).subscribe({
        next: (data) => {
          if (data) {
            this.errorMessage = 'Ya existe una clasificación con este código';
          }
        },
        error: (error) => {
          // Código no existe, está bien - limpiar error
          if (this.errorMessage.includes('código')) {
            this.errorMessage = '';
          }
        }
      });
    }
  }
}
