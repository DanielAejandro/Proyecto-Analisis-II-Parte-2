export interface Transaccion {
  idTransaccion?: number;
  idSaldoCuenta: number;
  idTipoMovimientoCXC: number;
  fechaMovimiento: Date;
  valorMovimiento: number;
  documentoRespaldo: string;
  usuarioRegistro: string;
  fechaRegistro?: Date;
  estado?: boolean;
}

export interface TipoMovimientoCXC {
  idTipoMovimientoCXC?: number;
  nombre: string;
  descripcion: string;
  tipo: 'DEBITO' | 'CREDITO';
  estado?: boolean;
}

export interface SaldoCuenta {
  idSaldoCuenta?: number;
  idCuenta: number;
  saldoActual: number;
  fechaUltimaActualizacion?: Date;
  estado?: boolean;
}
