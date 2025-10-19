import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { StatusCuenta } from '../entity/StatusCuenta';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class StatusCuentaService {
  private apiUrl = 'http://localhost:8080/api/status-cuenta'; // Cambia según tu backend

  constructor(private http: HttpClient) {}

  getAll(): Observable<StatusCuenta[]> {
    return this.http.get<StatusCuenta[]>(this.apiUrl);
  }

  getById(id: number): Observable<StatusCuenta> {
    return this.http.get<StatusCuenta>(`${this.apiUrl}/${id}`);
  }

  create(status: StatusCuenta): Observable<StatusCuenta> {
    return this.http.post<StatusCuenta>(this.apiUrl, status);
  }

  update(id: number, status: StatusCuenta): Observable<StatusCuenta> {
    return this.http.put<StatusCuenta>(`${this.apiUrl}/${id}`, status);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  
}
