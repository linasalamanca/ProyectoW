import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { InformacionVentaProducto} from '../dto/informacion-venta-producto'
import { Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class VenderService {

  constructor(
    private http: HttpClient
  ) { }

  private headers = new HttpHeaders(
    {"Content-Type": "application/json"}
  )

  listarProductos(planetaId: number): Observable<InformacionVentaProducto[]>{
    return this.http.get<InformacionVentaProducto[]>(`${environment.serverUrl}/api/vender/list/${planetaId}`)
    .pipe(tap(data => console.log('Data from API:',data)));
  }

  realizarVenta(idInventario: number, idJugador: number): Observable<any> {
    return this.http.post<any>(`${environment.serverUrl}/api/vender/realizar-venta`,  { idInventario, idJugador })
    .pipe( catchError(error => {
      alert(error.error); // Muestra la alerta con el mensaje de error del servidor
      return throwError(error);
    })
  );
  }

  actualizarPuntaje(idJugador: number, idInventario: number): Observable<any> {
    return this.http.patch<any>(`${environment.serverUrl}/api/vender/actualizar-puntaje/${idJugador}/${idInventario}`, {});
  }



}

