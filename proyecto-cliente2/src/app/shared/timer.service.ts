import { Injectable } from '@angular/core';
import { Observable, timer } from 'rxjs';
import { map, startWith } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TimerService {

  constructor() { }

  startTimer(): Observable<number> {
    // Empieza a contar desde 0 y aumenta cada segundo
    return timer(0, 1000).pipe(
      map(seconds => seconds),
      startWith(0)
    );
  }
}
