// src/app/component/persona-list/persona-list.component.ts
import { Component, OnInit } from '@angular/core';
import { PersonaService } from '../../../service/PersonaService';
import { Persona } from '../../../entity/Persona';
import { EstadoCivilService } from '../../../service/EstadoCivil';
import { TipoDocumentoService } from '../../../service/TipoDocumento';
import { GeneroService } from '../../../service/GeneroService';
import { EstadoCivil } from '../../../entity/EstadoCivil';
import { TipoDocumento } from '../../../entity/TipoDocumento';
import { Genero } from '../../../entity/Genero';

@Component({
  selector: 'app-persona-list',
  templateUrl: './persona-list.component.html',
  styleUrls: ['./persona-list.component.css']
})
export class PersonaListComponent implements OnInit {
  personas: Persona[] = [];
  filteredPersonas: Persona[] = [];
  searchTerm: string = '';
  loading: boolean = false;

  constructor(
    private personaService: PersonaService,
    private estadoCivilService: EstadoCivilService,
    private tipoDocumentoService: TipoDocumentoService,
    private generoService: GeneroService
  ) { }

  ngOnInit(): void {
    this.loadPersonas();
  }

  loadPersonas(): void {
    this.loading = true;
    this.personaService.getAllPersonas().subscribe({
      next: (data) => {
        this.personas = data;
        this.filteredPersonas = data;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading personas:', error);
        this.loading = false;
      }
    });
  }

  searchPersonas(): void {
    if (this.searchTerm.trim() === '') {
      this.filteredPersonas = this.personas;
    } else {
      this.filteredPersonas = this.personas.filter(persona =>
        persona.nombre.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        persona.apellido.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        persona.numeroIdentificacion.includes(this.searchTerm)
      );
    }
  }

  deletePersona(id: number): void {
    if (confirm('¿Está seguro de que desea eliminar esta persona?')) {
      this.personaService.deletePersona(id).subscribe({
        next: () => {
          this.personas = this.personas.filter(p => p.id !== id);
          this.filteredPersonas = this.filteredPersonas.filter(p => p.id !== id);
        },
        error: (error) => {
          console.error('Error deleting persona:', error);
          alert('Error al eliminar la persona');
        }
      });
    }
  }

  getTipoPersona(persona: Persona): string {
    if (persona.esCliente && persona.esSocio) return 'Cliente y Socio';
    if (persona.esCliente) return 'Cliente';
    if (persona.esSocio) return 'Socio';
    return 'N/A';
  }
}
