// src/app/entity/ClasificacionCuenta.ts
export class ClasificacionCuenta {
    id?: number;
    codigo: string;
    nombre: string;
    descripcion: string;
    naturaleza: string; // 'DEUDORA' | 'ACREEDORA'
    activo: boolean;

    constructor(
        codigo: string = '',
        nombre: string = '',
        descripcion: string = '',
        naturaleza: string = 'DEUDORA',
        activo: boolean = true
    ) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.naturaleza = naturaleza;
        this.activo = activo;
    }
}
