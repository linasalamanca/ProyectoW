import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
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
        if (response && response.id) {
          localStorage.setItem('usuario', JSON.stringify({ id: response.id, nombre: usuario }));
        }
      }));
  }

  logout(): void {
    localStorage.removeItem('usuario');
  }

  getCurrentUser(): { id: number, nombre: string } | null {
    const user = localStorage.getItem('usuario');
    return user ? JSON.parse(user) : null;
  }

  setCurrentUser(user: { id: number, nombre: string }): void {
    localStorage.setItem('usuario', JSON.stringify(user));
  }
}
