import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaccion, TipoMovimientoCXC, SaldoCuenta } from '../entity/Transaccion';

@Injectable({
  providedIn: 'root'
})
export class TransaccionService {
  private apiUrl = 'http://localhost:8080/api/transacciones';
  private tipoMovimientoUrl = 'http://localhost:8080/api/tipos-movimiento';
  private saldoCuentaUrl = 'http://localhost:8080/api/saldos-cuenta';

  constructor(private http: HttpClient) { }

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  // Transacciones
  createTransaccion(transaccion: Transaccion): Observable<Transaccion> {
    return this.http.post<Transaccion>(this.apiUrl, transaccion, { headers: this.getHeaders() });
  }

  getTransacciones(): Observable<Transaccion[]> {
    return this.http.get<Transaccion[]>(this.apiUrl, { headers: this.getHeaders() });
  }

  getTransaccionById(id: number): Observable<Transaccion> {
    return this.http.get<Transaccion>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }

  updateTransaccion(id: number, transaccion: Transaccion): Observable<Transaccion> {
    return this.http.put<Transaccion>(`${this.apiUrl}/${id}`, transaccion, { headers: this.getHeaders() });
  }

  deleteTransaccion(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }

  // Tipos de Movimiento
  getTiposMovimiento(): Observable<TipoMovimientoCXC[]> {
    return this.http.get<TipoMovimientoCXC[]>(this.tipoMovimientoUrl, { headers: this.getHeaders() });
  }

  // Saldos de Cuenta
  getSaldoCuentaById(id: number): Observable<SaldoCuenta> {
    return this.http.get<SaldoCuenta>(`${this.saldoCuentaUrl}/${id}`, { headers: this.getHeaders() });
  }

  verificarEstadoCuenta(idSaldoCuenta: number): Observable<boolean> {
    return this.http.get<boolean>(`${this.saldoCuentaUrl}/${idSaldoCuenta}/activa`, { headers: this.getHeaders() });
  }
}
