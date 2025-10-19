/*export interface Modulo {
  idModulo: number;
  nombre: string;
  ordenMenu: number;
  fechaCreacion: string;
  usuarioCreacion: string;
  fechaModificacion?: string | null;
  usuarioModificacion?: string | null;
}
*/


// src/app/entity/modulo.ts
export interface Modulo {
  idModulo?: number;
  nombre: string;
  ordenMenu: number;
  fechaCreacion?: string;  // usualmente las fechas vienen en string ISO
  usuarioCreacion: string;
  fechaModificacion?: string;
  usuarioModificacion?: string;
}
