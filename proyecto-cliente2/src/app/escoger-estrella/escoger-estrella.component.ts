import { Component } from '@angular/core';
import { EscogerEstrella } from '../model/escoger-estrella';
import { EscogerEstrellaService } from '../shared/escoger-estrella.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';

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
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.estrellaService.listarEstrellas().subscribe(estrella => this.estrellas = estrella)
  }
 
  verPlanetas(id: number): void {
    this.estrellaService.cambiarCoordenadasNave(id).subscribe(_=>this.router.navigate([`/escoger-planeta/list/${id}`]));
      
  }

}
