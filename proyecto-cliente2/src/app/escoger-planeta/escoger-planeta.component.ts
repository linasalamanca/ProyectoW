import { Component } from '@angular/core';
import { EscogerPlaneta } from '../model/escoger-planeta';
import { EscogerPlanetaService } from '../shared/escoger-planeta.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-escoger-planeta',
  templateUrl: './escoger-planeta.component.html',
  styleUrl: './escoger-planeta.component.css'
})
export class EscogerPlanetaComponent {
  timeElapsed: Observable<number>| undefined;
  planetas: EscogerPlaneta [] = []

  constructor(
    private planetaServicio: EscogerPlanetaService,
    private router: Router
  ){}

  ngOnInit(): void {
    this.planetaServicio.listarPlanetas().subscribe(planetas => this.planetas = planetas)
  }

  verAcciones(planetaId: number): void {
    this.router.navigate(['/planeta/${planetaId}/accion'])
  }
}