import { Menu } from './Menu';  // Asegúrate de tener esta entidad también

/*export interface Opcion {
  idOpcion: number;
  menu: Menu;
  nombre: string;
  ordenMenu: number;
  pagina: string;
  fechaCreacion: string;
  usuarioCreacion: string;
  fechaModificacion?: string | null;
  usuarioModificacion?: string | null;
} */

export interface Opcion {
  idOpcion: number;
  nombre: string;
  pagina: string;
  ordenMenu: number;
  menu?: { idMenu: number; nombre: string };
}


