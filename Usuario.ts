export interface Usuario {
  idUsuario: string;
  nombre: string;
  apellido: string;
  fechaNacimiento: string;
  statusUsuario: { idStatusUsuario: number } | null;
  password?: string;
  genero: { idGenero: number } | null;
  ultimaFechaIngreso?: string;
  intentosDeAcceso?: number;
  sesionActual?: string;
  ultimaFechaCambioPassword?: string;
  correoElectronico: string;
  requiereCambiarPassword?: number;
  fotografia?: string;
  telefonoMovil?: string;
  sucursal: { idSucursal: number } | null;
  pregunta: string;
  respuesta: string;
  role: { idRole: number } | null;
  fechaCreacion?: string;
  usuarioCreacion?: string;
  fechaModificacion?: string;
  usuarioModificacion?: string;
}
