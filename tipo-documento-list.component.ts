import { Component } from '@angular/core';
import { TipoDocumento } from '../../../../entity/TipoDocumento';
import { TipoDocumentoService } from '../../../../service/TipoDocumento';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tipo-documento-list',
  standalone: false,
  templateUrl: './tipo-documento-list.component.html',
  styleUrl: './tipo-documento-list.component.css'
})
export class TipoDocumentoListComponent {

  tipoDocumentoList: TipoDocumento[] = [];
  cargando = false;
  error = '';

  constructor(
    private service: TipoDocumentoService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.cargarLista();
  }

  cargarLista(): void {
    this.cargando = true;
    this.service.getAll().subscribe({
      next: (data) => {
        this.tipoDocumentoList = data;
        this.cargando = false;
      },
      error: () => {
        this.error = 'Error al cargar tipos de documento';
        this.cargando = false;
      }
    });
  }

  crearNuevo(): void {
    this.router.navigate(['/tipo-documento/nuevo']);
  }

  editar(id: number): void {
    this.router.navigate(['/tipo-documento/editar', id]);
  }

  eliminar(id: number): void {
    if (confirm('¿Está seguro que desea eliminar este tipo de documento?')) {
      this.service.delete(id).subscribe({
        next: () => this.cargarLista(),
        error: () => this.error = 'Error al eliminar'
      });
    }
  }

  irAOpiones(): void {
    this.router.navigate(['/opciones']); // Asegúrate de que esta ruta coincida con la ruta de "Opciones" en tu aplicación
  }
}
