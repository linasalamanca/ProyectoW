import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EscogerPlaneta } from '../model/escoger-planeta'
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EscogerPlanetaService {

    constructor(
        private http: HttpClient
    ) { }
    
    private headers = new HttpHeaders(
        {"Content-Type": "application/json"}
    )
    
    listarPlanetas(id: number): Observable<EscogerPlaneta[]>{
        return this.http.get<EscogerPlaneta[]>(`${environment.serverUrl}/api/escoger-planeta/list/${id}`)
    }
}