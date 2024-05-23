import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = `${environment.serverUrl}/api/iniciar`;



  constructor(
    private http: HttpClient,
  ) {
    
   }

  login(usuario: string, contrasena: string): Observable<any> {
    return this.http.post<any>(this.apiUrl, { usuario, contrasena })
      .pipe(
        tap(response => {
          if (response && response.id) {
            sessionStorage.setItem('usuario', JSON.stringify({ id: response.id, nombre: usuario }));
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

  getCurrentUser(): { id: number, nombre: string } | null {
    const user = sessionStorage.getItem('usuario');
    return user ? JSON.parse(user) : null;
  }

  setCurrentUser(user: { id: number, nombre: string }): void {
    sessionStorage.setItem('usuario', JSON.stringify(user));
  }
}
