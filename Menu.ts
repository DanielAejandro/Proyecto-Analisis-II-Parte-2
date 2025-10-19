import { Modulo } from './Modulo';

/*export interface Menu {
  idMenu: number;
  modulo: Modulo;
  nombre: string;
  ordenMenu: number;
  fechaCreacion: string;
  usuarioCreacion: string;
  fechaModificacion?: string | null;
  usuarioModificacion?: string | null;
}*/



export interface Menu {
  idMenu?: number;
  modulo: Modulo;
  nombre: string;
  ordenMenu: number;
  fechaCreacion?: string;
  usuarioCreacion: string;
  fechaModificacion?: string;
  usuarioModificacion?: string;
}
