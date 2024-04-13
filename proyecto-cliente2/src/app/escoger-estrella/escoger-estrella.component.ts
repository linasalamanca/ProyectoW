import { Component } from '@angular/core';
import { EscogerEstrella } from '../model/escoger-estrella';
import { EscogerEstrellaService } from '../shared/escoger-estrella.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-escoger-estrella',
  templateUrl: './escoger-estrella.component.html',
  styleUrl: './escoger-estrella.component.css'
})
export class EscogerEstrellaComponent {

  timeElapsed: Observable<number>| undefined;

  estrellas:EscogerEstrella [] = [];

  constructor(
    private estrellaService: EscogerEstrellaService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.estrellaService.listarEstrellas().subscribe(estrella => this.estrellas = estrella)
  }
  verPlanetas(estrellaId: number): void {
    // Navegar a una ruta que muestra los planetas de la estrella
    // Ejemplo: '/estrellas/1/planetas'
    this.router.navigate([`/estrellas/${estrellaId}/planetas`]);
  }

}
