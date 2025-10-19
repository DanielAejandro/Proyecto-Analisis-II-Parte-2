import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../entity/Usuario';
import { LoginRequest } from '../entity/LoginRequest';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = 'http://localhost:8080/api/auth/login';


  constructor(private http: HttpClient) {}

  login(credentials: LoginRequest): Observable<Usuario> {
    return this.http.post<Usuario>(this.loginUrl, credentials);
  }

  // Aquí puedes agregar más métodos como logout, isLoggedIn, etc.
}
