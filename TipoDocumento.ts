import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TipoDocumento } from '../entity/TipoDocumento';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TipoDocumentoService {
  private apiUrl = 'http://localhost:8080/api/tipo-documento'; // Cambia según tu backend

  constructor(private http: HttpClient) {}

  getAll(): Observable<TipoDocumento[]> {
    return this.http.get<TipoDocumento[]>(this.apiUrl);
  }

  getById(id: number): Observable<TipoDocumento> {
    return this.http.get<TipoDocumento>(`${this.apiUrl}/${id}`);
  }

  create(status: TipoDocumento): Observable<TipoDocumento> {
    return this.http.post<TipoDocumento>(this.apiUrl, status);
  }

  update(id: number, status: TipoDocumento): Observable<TipoDocumento> {
    return this.http.put<TipoDocumento>(`${this.apiUrl}/${id}`, status);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

}
