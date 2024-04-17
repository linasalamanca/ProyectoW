import { Component } from '@angular/core';
import { EscogerEstrella } from '../model/escoger-estrella';
import { EscogerEstrellaService } from '../shared/escoger-estrella.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { InformacionJuego } from '../dto/informacion-juego';
import { InformacionJuegoService } from '../shared/informacion-juego.service';

@Component({
  selector: 'app-escoger-estrella',
  templateUrl: './escoger-estrella.component.html',
  styleUrl: './escoger-estrella.component.css'
})
export class EscogerEstrellaComponent {


  estrellas:EscogerEstrella [] = [];
  

  constructor(
    public infoService: InformacionJuegoService,
    private estrellaService: EscogerEstrellaService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.estrellaService.listarEstrellas().subscribe(estrella => this.estrellas = estrella)
  }
 
  realizarViaje(id: number): void {
    
    this.estrellaService.calcularTiempoJuego(id).subscribe(resultado => {
        if (resultado) {
            this.router.navigate([`/finalizar`]);
        } else {
          this.infoService.obtenerTiempo().subscribe(t => this.infoService.setInfoTiempo(t));
          this.estrellaService.cambiarCoordenadasNave(id).subscribe(_=>this.router.navigate([`/escoger-planeta/list/${id}`]));
        }
      }
    );
      
  }

}
