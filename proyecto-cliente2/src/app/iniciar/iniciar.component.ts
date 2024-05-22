import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../shared/auth.service';
import { InformacionJuegoService } from '../shared/informacion-juego.service';

@Component({
  selector: 'app-iniciar',
  templateUrl: './iniciar.component.html',
  styleUrls: ['./iniciar.component.css']
})
export class IniciarComponent {
  usuario: string = '';
  contrasena: string = '';
  idJugador: number | undefined

  constructor(
    private router: Router,
    private authService: AuthService,
    public infoService: InformacionJuegoService
  ) { }
  iniciarJuego() {
    const currentUser = this.authService.getCurrentUser();
    if (currentUser) {
      this.idJugador = currentUser.id;
    } else {
      console.error('No hay un usuario autenticado');
    }
    console.log('Intentando iniciar sesión con', this.usuario, this.contrasena);
    this.authService.login(this.usuario, this.contrasena).subscribe(jugador => {
      console.log('Autenticado:', jugador);
      if (jugador) {
        // Aquí obtenemos el puntaje y luego navegamos
        this.infoService.obtenerPuntajeCompra(this.idJugador!).subscribe(puntaje => {
          this.infoService.setInfoPuntaje(puntaje);
          console.log('Navegando a /escoger-estrella/list');
          this.router.navigate(['/escoger-estrella/list']);
        }, error => {
          console.error('Error obteniendo puntaje:', error);
          alert('Error al obtener el puntaje. Por favor, intenta nuevamente más tarde.');
        });
      } else {
        alert('Usuario o contraseña incorrectos');
      }
    }, error => {
      console.error('Error durante la autenticación', error);
      alert('Error en el servidor. Por favor, intenta nuevamente más tarde.');
    });
  }
}
