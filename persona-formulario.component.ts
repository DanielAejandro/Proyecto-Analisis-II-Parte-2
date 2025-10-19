// src/app/component/persona-formulario/persona-formulario.component.ts
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonaService } from '../../../service/PersonaService';
import { EstadoCivilService } from '../../../service/EstadoCivil';
import { TipoDocumentoService } from '../../../service/TipoDocumento';
import { GeneroService } from '../../../service/GeneroService';
import { Persona } from '../../../entity/Persona';
import { EstadoCivil } from '../../../entity/EstadoCivil';
import { TipoDocumento } from '../../../entity/TipoDocumento';
import { Genero } from '../../../entity/Genero';
import { forkJoin, Observable } from 'rxjs';


@Component({
  selector: 'app-persona-formulario',
  templateUrl: './persona-formulario.component.html',
  styleUrls: ['./persona-formulario.component.css']
})
export class PersonaFormularioComponent implements OnInit {
  personaForm: FormGroup;
  isEditMode: boolean = false;
  personaId?: number;
  loading: boolean = false;
  submitting: boolean = false;

  estadosCiviles: EstadoCivil[] = [];
  tiposDocumento: TipoDocumento[] = [];
  generos: Genero[] = [];
clasificacionesCuenta: any;
statusCuenta: any;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private personaService: PersonaService,
    private estadoCivilService: EstadoCivilService,
    private tipoDocumentoService: TipoDocumentoService,
    private generoService: GeneroService
  ) {
    this.personaForm = this.createForm();
  }

  ngOnInit(): void {
    this.loadDropdownData();

    this.personaId = this.route.snapshot.params['id'];
    if (this.personaId) {
      this.isEditMode = true;
      this.loadPersona(this.personaId);
    }
  }

  createForm(): FormGroup {
    return this.fb.group({
      nombre: ['', [Validators.required, Validators.minLength(2)]],
      apellido: ['', [Validators.required, Validators.minLength(2)]],
      tipoDocumentoId: ['', Validators.required],
      numeroIdentificacion: ['', [Validators.required, Validators.minLength(3)]],
      direccion: ['', [Validators.required, Validators.minLength(5)]],
      telefono: ['', [Validators.required, Validators.pattern('^[0-9+()-\\s]+$')]],
      correo: ['', [Validators.required, Validators.email]],
      estadoCivilId: ['', Validators.required],
      generoId: ['', Validators.required],
      fechaNacimiento: [''],
      esCliente: [false],
      esSocio: [false]
    });
  }
  estadosCiviles$!: Observable<EstadoCivil[]>;
  tiposDocumento$!: Observable<TipoDocumento[]>;
  generos$!: Observable<Genero[]>;

 loadDropdownData(): void {
  this.loading = true;

  this.estadosCiviles$ = this.estadoCivilService.getAll();
  this.tiposDocumento$ = this.tipoDocumentoService.getAll();


  forkJoin([
    this.estadosCiviles$,
    this.tiposDocumento$,
    this.generos$
  ]).subscribe(() => {
    this.loading = false;
  });
}

  loadPersona(id: number): void {
    this.loading = true;
    this.personaService.getPersonaById(id).subscribe({
      next: (persona) => {
        this.personaForm.patchValue({
          ...persona,
          fechaNacimiento: persona.fechaNacimiento ?
            new Date(persona.fechaNacimiento).toISOString().split('T')[0] : ''
        });
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading persona:', error);
        this.loading = false;
        alert('Error al cargar la persona');
      }
    });
  }

  onSubmit(): void {
    if (this.personaForm.valid) {
      this.submitting = true;
      const personaData: Persona = this.personaForm.value;

      if (this.isEditMode && this.personaId) {
        this.personaService.updatePersona(this.personaId, personaData).subscribe({
          next: () => {
            this.submitting = false;
            alert('Persona actualizada correctamente');
            this.router.navigate(['/personas']);
          },
          error: (error) => {
            console.error('Error updating persona:', error);
            this.submitting = false;
            alert('Error al actualizar la persona');
          }
        });
      } else {
        this.personaService.createPersona(personaData).subscribe({
          next: () => {
            this.submitting = false;
            alert('Persona creada correctamente');
            this.router.navigate(['/personas']);
          },
          error: (error) => {
            console.error('Error creating persona:', error);
            this.submitting = false;
            alert('Error al crear la persona');
          }
        });
      }
    } else {
      this.markFormGroupTouched();
    }
  }

  markFormGroupTouched(): void {
    Object.keys(this.personaForm.controls).forEach(key => {
      const control = this.personaForm.get(key);
      control?.markAsTouched();
    });
  }

  onCancel(): void {
    this.router.navigate(['/personas']);
  }

  // Getters for form controls for easy access in template
  get nombre() { return this.personaForm.get('nombre'); }
  get apellido() { return this.personaForm.get('apellido'); }
  get tipoDocumentoId() { return this.personaForm.get('tipoDocumentoId'); }
  get numeroIdentificacion() { return this.personaForm.get('numeroIdentificacion'); }
  get direccion() { return this.personaForm.get('direccion'); }
  get telefono() { return this.personaForm.get('telefono'); }
  get correo() { return this.personaForm.get('correo'); }
  get estadoCivilId() { return this.personaForm.get('estadoCivilId'); }
  get generoId() { return this.personaForm.get('generoId'); }
}
