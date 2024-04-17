import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EscogerEstrella } from '../model/escoger-estrella';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class EscogerEstrellaService {

  constructor(
    private http: HttpClient
  ) { }

  private headers = new HttpHeaders(
    {"Content-Type": "application/json"}
  )

  listarEstrellas(): Observable<EscogerEstrella[]>{
    return this.http.get<EscogerEstrella[]>(`${environment.serverUrl}/api/escoger-estrella/list`)
  }

  cambiarCoordenadasNave(id: number): Observable<any> {
    return this.http.post<any>(`${environment.serverUrl}/api/escoger-estrella/cambiar-coordenadas-nave/${id}`,id);
  }

  calcularTiempoJuego(id: number): Observable<any> {
    return this.http.post<any>(`${environment.serverUrl}/api/escoger-estrella/calcular-tiempo/${id}`,id);
  }

}
