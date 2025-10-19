// src/app/service/OpcionService.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Opcion } from '../entity/Opcion';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OpcionService {
  private apiUrl = 'http://localhost:8080/api/opcion';  // tu endpoint backend

  constructor(private http: HttpClient) {}

  // Obtener todas las opciones
  getAll(): Observable<Opcion[]> {
    return this.http.get<Opcion[]>(this.apiUrl);
  }
}
