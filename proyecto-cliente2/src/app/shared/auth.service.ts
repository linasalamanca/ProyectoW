import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { tap, catchError, map } from 'rxjs/operators';
import { environment } from '../../environments/environment.development';
import { JwtAuthenticationResponse } from '../dto/jwt-authentication-response';
import { LoginDto } from '../dto/login-dto';

const JWT_TOKEN = "jwt-token";
const USERNAME = "user-username";
const ROLE = "user-role";
const ID = "user-id";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = `${environment.serverUrl}/api/auth/login`;



  constructor(
    private http: HttpClient,
  ) {
    
   }

  login(loginDto: LoginDto): Observable<JwtAuthenticationResponse> {
    return this.http.post<JwtAuthenticationResponse>(this.apiUrl, loginDto)
      .pipe(
        map(jwt => {
          // Importante: https://stackoverflow.com/questions/27067251/where-to-store-jwt-in-browser-how-to-protect-against-csrf
          sessionStorage.setItem(JWT_TOKEN, jwt.token);
          sessionStorage.setItem(USERNAME, jwt.user);
          sessionStorage.setItem(ROLE, jwt.role);
          sessionStorage.setItem(ID, jwt.id.toString());
          return jwt;
        })
        /*tap(response => {
          if (response && response.id) {
            sessionStorage.setItem('usuario', JSON.stringify({ id: response.id, nombre: usuario }));
          }
        }),
        catchError(this.handleError)*/
      );
  }

  private handleError(error: HttpErrorResponse) {
    console.error('Login failed:', error);
    return throwError('An error occurred while logging in.');
  }

  logout(): void {
   // sessionStorage.removeItem('usuario');
   sessionStorage.removeItem(JWT_TOKEN);
   sessionStorage.removeItem(USERNAME);
   sessionStorage.removeItem(ROLE);
  }


  isAuthenticated() {
    return sessionStorage.getItem(JWT_TOKEN) != null;
  }

  token() {
    return sessionStorage.getItem(JWT_TOKEN);
  }

  role() {
    return sessionStorage.getItem(ROLE);
  }

  id() {
    return sessionStorage.getItem(ID) || '';
  }
}
