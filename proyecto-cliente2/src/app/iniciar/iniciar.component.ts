import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { TimerService } from '../shared/timer.service';

@Component({
  selector: 'app-iniciar',
  templateUrl: './iniciar.component.html',
  styleUrl: './iniciar.component.css'
})
export class IniciarComponent {

  timeElapsed: Observable<number>| undefined;

  constructor(private router: Router, private timerService: TimerService) { }
  iniciarJuego() {
    this.router.navigate(['/escoger-estrella/list']); 
    this.timeElapsed = this.timerService.startTimer()
  }
}
