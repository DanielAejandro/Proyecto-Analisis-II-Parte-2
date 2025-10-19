// src/app/entity/Persona.ts
export interface Persona {
  id?: number;
  nombre: string;
  apellido: string;
  tipoDocumentoId: number;
  tipoDocumento?: string;
  numeroIdentificacion: string;
  direccion: string;
  telefono: string;
  correo: string;
  estadoCivilId: number;
  estadoCivil?: string;
  generoId: number;
  genero?: string;
  fechaNacimiento?: Date;
  esCliente: boolean;
  esSocio: boolean;
  fechaCreacion?: Date;
  fechaModificacion?: Date;
  usuarioCreacion?: string;
  usuarioModificacion?: string;
}
