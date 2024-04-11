import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { InformacionVentaProducto} from '../dto/informacion-venta-producto'
import { Observable } from 'rxjs';
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

  listarProductos(): Observable<InformacionVentaProducto[]>{
    return this.http.get<InformacionVentaProducto[]>(`${environment.serverUrl}/api/vender/list`)
  }
}

