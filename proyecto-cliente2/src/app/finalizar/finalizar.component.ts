import { Component } from '@angular/core';
import { InformacionJuegoService } from '../shared/informacion-juego.service';
import { AuthService } from '../shared/auth.service';


@Component({
  selector: 'app-finalizar',
  templateUrl: './finalizar.component.html',
  styleUrl: './finalizar.component.css'
})
export class FinalizarComponent {

  respuesta:string = "No hay nada aun";
  idJugador: number | undefined
  constructor(
    public infoService: InformacionJuegoService,
    private authService: AuthService,
  ){}



  ngOnInit(): void {
    this.idJugador = isNaN(parseInt(this.authService.id())) ? undefined : parseInt(this.authService.id());

    this.infoService.obtenerTiempo().subscribe(t => this.infoService.setInfoTiempo(t));
    this.infoService.obtenerPuntajeCompra(this.idJugador!).subscribe(puntaje => this.infoService.setInfoPuntaje(puntaje));
  }

}
