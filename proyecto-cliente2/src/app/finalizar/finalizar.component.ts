import { Component } from '@angular/core';
import { InformacionJuegoService } from '../shared/informacion-juego.service';

@Component({
  selector: 'app-finalizar',
  templateUrl: './finalizar.component.html',
  styleUrl: './finalizar.component.css'
})
export class FinalizarComponent {

  respuesta:string = "No hay nada aun";
  constructor(
    public infoService: InformacionJuegoService
  ){}



  ngOnInit(): void {
    this.infoService.obtenerTiempo().subscribe(t => this.infoService.setInfoTiempo(t));
    this.infoService.obtenerPuntajeCompra().subscribe(puntaje => this.infoService.setInfoPuntaje(puntaje));
  }

}
