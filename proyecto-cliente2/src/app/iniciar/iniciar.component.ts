import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-iniciar',
  templateUrl: './iniciar.component.html',
  styleUrl: './iniciar.component.css'
})
export class IniciarComponent {
  constructor(private router: Router) { }
  iniciarJuego() {
    this.router.navigate(['/escoger-estrella/list']); 
  }
}
