import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment.development';
import { UsuarioDto } from '../dto/usuariodto';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = `${environment.serverUrl}/api/iniciar`;



  constructor(
    private http: HttpClient,
    public usuario: UsuarioDto
  ) {
    
   }

  login(usuario: string, contrasena: string): Observable<any> {
    return this.http.post<any>(this.apiUrl, { usuario, contrasena })
      .pipe(
        tap(response => {
          if (response && response.id) {
            localStorage.setItem('usuario', JSON.stringify({ id: response.id, nombre: usuario }));
          }
        }),
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    console.error('Login failed:', error);
    return throwError('An error occurred while logging in.');
  }

  logout(): void {
    localStorage.removeItem('usuario');
  }

  getCurrentUser(): UsuarioDto | null {
    const userString = sessionStorage.getItem('usuario');
  
    if (userString) {
      const userObj = JSON.parse(userString);
      this.usuario.id = userObj.id;
      this.usuario.nombre = userObj.nombre;
      return this.usuario;
    }
    return null;
  }
  

  

  setCurrentUser(user: { id: number, nombre: string }): void {
    sessionStorage.setItem('usuario', JSON.stringify(user));
  }
}
