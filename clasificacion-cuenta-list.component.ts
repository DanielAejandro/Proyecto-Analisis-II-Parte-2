// src/app/component/clasificacion-cuenta-list/clasificacion-cuenta-list.component.ts
import { Component, OnInit } from '@angular/core';
import { ClasificacionCuenta } from '../../../entity/ClasificacionCuenta';
import { ClasificacionCuentaService } from '../../../service/ClasificacionCuentaService';

@Component({
  selector: 'app-clasificacion-cuenta-list',
  templateUrl: './clasificacion-cuenta-list.component.html',
  styleUrls: ['./clasificacion-cuenta-list.component.css']
})
export class ClasificacionCuentaListComponent implements OnInit {
  clasificaciones: ClasificacionCuenta[] = [];
  filteredClasificaciones: ClasificacionCuenta[] = [];
  searchTerm: string = '';
  loading: boolean = false;
  errorMessage: string = '';

  constructor(private clasificacionService: ClasificacionCuentaService) { }

  ngOnInit(): void {
    this.loadClasificaciones();
  }

  loadClasificaciones(): void {
    this.loading = true;
    this.clasificacionService.getAllClasificaciones().subscribe({
      next: (data) => {
        this.clasificaciones = data;
        this.filteredClasificaciones = data;
        this.loading = false;
      },
      error: (error) => {
        this.errorMessage = 'Error al cargar las clasificaciones';
        this.loading = false;
        console.error('Error:', error);
      }
    });
  }

  searchClasificaciones(): void {
    if (this.searchTerm.trim() === '') {
      this.filteredClasificaciones = this.clasificaciones;
    } else {
      this.clasificacionService.searchClasificaciones(this.searchTerm).subscribe({
        next: (data) => {
          this.filteredClasificaciones = data;
        },
        error: (error) => {
          this.errorMessage = 'Error al buscar clasificaciones';
          console.error('Error:', error);
        }
      });
    }
  }

  filterByNaturaleza(naturaleza: string): void {
    if (naturaleza === 'TODAS') {
      this.filteredClasificaciones = this.clasificaciones;
    } else {
      this.clasificacionService.getClasificacionesByNaturaleza(naturaleza).subscribe({
        next: (data) => {
          this.filteredClasificaciones = data;
        },
        error: (error) => {
          this.errorMessage = 'Error al filtrar por naturaleza';
          console.error('Error:', error);
        }
      });
    }
  }

  deleteClasificacion(id: number): void {
    if (confirm('¿Está seguro de que desea eliminar esta clasificación?')) {
      this.clasificacionService.deleteClasificacion(id).subscribe({
        next: () => {
          this.loadClasificaciones(); // Recargar la lista
        },
        error: (error) => {
          this.errorMessage = 'Error al eliminar la clasificación';
          console.error('Error:', error);
        }
      });
    }
  }

  clearSearch(): void {
    this.searchTerm = '';
    this.filteredClasificaciones = this.clasificaciones;
  }
}
