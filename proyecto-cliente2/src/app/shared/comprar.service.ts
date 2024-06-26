import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { InformacionVentaProducto } from '../dto/informacion-venta-producto';
import { environment } from '../../environments/environment.development';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
@Injectable({
  providedIn: 'root'
})


export class ComprarService {
  constructor(private http: HttpClient) {}


  listarProductos(planetaId: number): Observable<InformacionVentaProducto[]> {
    return this.http.get<InformacionVentaProducto[]>(`${environment.serverUrl}/api/comprar/list/${planetaId}`)
      .pipe(tap(data => console.log('Data from API:', data)));
  }

  realizarCompra(idInventario: number, idJugador: number): Observable<any> {
    return this.http.post(`${environment.serverUrl}/api/comprar/realizar-compra`, { idInventario, idJugador })
    .pipe( catchError(error => {
      alert(error.error); // Muestra la alerta con el mensaje de error del servidor
      return throwError(error);
    })
  );
  }

  actualizarPuntaje(idJugador: number, idInventario: number): Observable<any> {
    return this.http.patch(`${environment.serverUrl}/api/comprar/actualizar-puntaje/${idJugador}/${idInventario}`, {});
  }
  
}
