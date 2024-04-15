import { Component, OnInit } from '@angular/core';
import { EscogerPlaneta } from '../model/escoger-planeta';
import { EscogerPlanetaService } from '../shared/escoger-planeta.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, switchMap } from 'rxjs';

@Component({
  selector: 'app-escoger-planeta',
  templateUrl: './escoger-planeta.component.html',
  styleUrl: './escoger-planeta.component.css'
})
export class EscogerPlanetaComponent implements OnInit {
  timeElapsed: Observable<number>| undefined;
  planetas: EscogerPlaneta [] = []

  constructor(
    private planetaServicio: EscogerPlanetaService,
    private router: Router,
    private route: ActivatedRoute
  ){}

  ngOnInit(): void {
    this.route.paramMap.pipe(
      switchMap(params => {
        const idStr = params.get('id'); 
        if (idStr === null) {
          throw new Error('Estrella ID is required'); 
        }
        const id = Number(idStr);
        if (isNaN(id)) {
          throw new Error('Estrella ID must be a number');
        }
        return this.planetaServicio.listarPlanetas(id);
      })
    ).subscribe(planetas => {
      this.planetas = planetas;
    }, error => {
      console.error('Error al obtener planetas:', error);
    });
  }

  verAcciones(planetaId: number): void {
    this.router.navigate(['/planeta/${planetaId}/accion'])
  }
  
  irAComprar(planetaId: number): void {
    this.router.navigate([`/escoger-planeta/${planetaId}/comprar/list`]);
  }
}