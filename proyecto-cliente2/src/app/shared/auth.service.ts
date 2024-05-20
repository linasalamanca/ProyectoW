// auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = `${environment.serverUrl}/api/iniciar`;

  constructor(private http: HttpClient) { }

  login(usuario: string, contrasena: string): Observable<any> {
    return this.http.post<any>(this.apiUrl, { usuario, contrasena })
      .pipe(tap(response => {
        if (response) {
          // Guardar el usuario en el almacenamiento local
          localStorage.setItem('usuario', usuario);
        }
      }));
  }

  logout(): void {
    localStorage.removeItem('usuario');
  }

  getUsuario(): string | null {
    return localStorage.getItem('usuario');
  }
}
