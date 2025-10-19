// src/app/service/ClasificacionCuentaService.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ClasificacionCuenta } from '../entity/ClasificacionCuenta';

@Injectable({
  providedIn: 'root'
})
export class ClasificacionCuentaService {
  private apiUrl = 'http://localhost:8080/api/clasificaciones-cuenta';

  constructor(private http: HttpClient) { }

  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json'
    });
  }

  // Obtener todas las clasificaciones activas
  getAllClasificaciones(): Observable<ClasificacionCuenta[]> {
    return this.http.get<ClasificacionCuenta[]>(this.apiUrl, { headers: this.getHeaders() });
  }

  // Obtener clasificación por ID
  getClasificacionById(id: number): Observable<ClasificacionCuenta> {
    return this.http.get<ClasificacionCuenta>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }

  // Obtener clasificación por código
  getClasificacionByCodigo(codigo: string): Observable<ClasificacionCuenta> {
    return this.http.get<ClasificacionCuenta>(`${this.apiUrl}/codigo/${codigo}`, { headers: this.getHeaders() });
  }

  // Obtener clasificaciones por naturaleza
  getClasificacionesByNaturaleza(naturaleza: string): Observable<ClasificacionCuenta[]> {
    return this.http.get<ClasificacionCuenta[]>(`${this.apiUrl}/naturaleza/${naturaleza}`, { headers: this.getHeaders() });
  }

  // Crear nueva clasificación
  createClasificacion(clasificacion: ClasificacionCuenta): Observable<ClasificacionCuenta> {
    return this.http.post<ClasificacionCuenta>(this.apiUrl, clasificacion, { headers: this.getHeaders() });
  }

  // Actualizar clasificación existente
  updateClasificacion(id: number, clasificacion: ClasificacionCuenta): Observable<ClasificacionCuenta> {
    return this.http.put<ClasificacionCuenta>(`${this.apiUrl}/${id}`, clasificacion, { headers: this.getHeaders() });
  }

  // Eliminar clasificación (eliminación lógica)
  deleteClasificacion(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }

  // Buscar clasificaciones
  searchClasificaciones(term: string): Observable<ClasificacionCuenta[]> {
    const params = new HttpParams().set('term', term);
    return this.http.get<ClasificacionCuenta[]>(`${this.apiUrl}/search`, {
      headers: this.getHeaders(),
      params: params
    });
  }
}
