import { Injectable } from '@angular/core';
import { InformacionJuego } from '../dto/informacion-juego';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class InformacionJuegoService {

  private info: InformacionJuego = new InformacionJuego(0,0);

  constructor(
    private http: HttpClient
  ) { }

  private headers = new HttpHeaders(
    {"Content-Type": "application/json"}
  )

  getInfoPuntaje(): number {
    return this.info.puntaje;
  }

  getInfoTiempo(): number {
    return this.info.tiempo;
  }

  setInfoPuntaje(puntaje: number) {
    this.info.puntaje = puntaje;
  }

  setInfoTiempo(tiempo: number) {
    this.info.tiempo = tiempo;
  }


  obtenerTiempo(): Observable<number> {
    return this.http.get<number>(`${environment.serverUrl}/api/escoger-estrella/tiempo`);
  }


}
