import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { InformacionVentaProducto} from '../dto/informacion-venta-producto'
import { Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment.development';

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

  realizarVenta(id: number): Observable<any> {
    return this.http.post<any>(`${environment.serverUrl}/api/vender/realizar-venta/${id}`, null);
  }
  actualizarPuntaje(id: number): Observable<any> {
    return this.http.post<any>(`${environment.serverUrl}/api/vender/actualizar-puntaje/${id}`, id);
  }

}

