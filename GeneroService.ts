// genero.service.ts
/*import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Genero } from '../entity/Genero';

@Injectable({
  providedIn: 'root',
})
export class GeneroService {
  private apiUrl = 'http://localhost:8080/api/genero';

  constructor(private http: HttpClient) {}

  // Obtener todos los géneros
  getGeneros(): Observable<Genero[]> {
    return this.http.get<Genero[]>(this.apiUrl);
  }

  // Obtener un género por ID (opcional si necesitas)
  getGeneroById(id: number): Observable<Genero> {
    return this.http.get<Genero>(`${this.apiUrl}/${id}`);
  }
}*/

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Genero } from '../entity/Genero';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GeneroService {
  getAll() {
    throw new Error('Method not implemented.');
  }
  private baseUrl = 'http://localhost:8080/api/generos'; // Ajusta si es necesario

  constructor(private http: HttpClient) {}

  getGeneros(): Observable<Genero[]> {
    return this.http.get<Genero[]>(this.baseUrl);
  }

  getGenero(id: number): Observable<Genero> {
    return this.http.get<Genero>(`${this.baseUrl}/${id}`);
  }

  crearGenero(genero: Genero): Observable<Genero> {
    return this.http.post<Genero>(this.baseUrl, genero);
  }

  actualizarGenero(id: number, genero: Genero): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/${id}`, genero);
  }

  eliminarGenero(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}


