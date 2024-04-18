import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { InformacionJuegoService } from '../shared/informacion-juego.service';

@Component({
  selector: 'app-iniciar',
  templateUrl: './iniciar.component.html',
  styleUrl: './iniciar.component.css'
})
export class IniciarComponent {

  constructor(private router: Router,
    public infoService: InformacionJuegoService,) { }
  iniciarJuego() {
    this.infoService.obtenerPuntajeCompra().subscribe(puntaje => this.infoService.setInfoPuntaje(puntaje));
    this.router.navigate(['/escoger-estrella/list']); 
  }
}
