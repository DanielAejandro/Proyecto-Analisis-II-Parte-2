import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EstadoCivil } from '../entity/EstadoCivil';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EstadoCivilService {
  private apiUrl = 'http://localhost:8080/api/estado-civil'; // Cambia según tu backend

  constructor(private http: HttpClient) {}

  getAll(): Observable<EstadoCivil[]> {
    return this.http.get<EstadoCivil[]>(this.apiUrl);
  }

  getById(id: number): Observable<EstadoCivil> {
    return this.http.get<EstadoCivil>(`${this.apiUrl}/${id}`);
  }

  create(status: EstadoCivil): Observable<EstadoCivil> {
    return this.http.post<EstadoCivil>(this.apiUrl, status);
  }

  update(id: number, status: EstadoCivil): Observable<EstadoCivil> {
    return this.http.put<EstadoCivil>(`${this.apiUrl}/${id}`, status);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

}
