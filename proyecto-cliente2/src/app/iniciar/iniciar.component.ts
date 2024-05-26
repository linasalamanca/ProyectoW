import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../shared/auth.service';
import { InformacionJuegoService } from '../shared/informacion-juego.service';
import { LoginDto } from '../dto/login-dto';


@Component({
  selector: 'app-iniciar',
  templateUrl: './iniciar.component.html',
  styleUrls: ['./iniciar.component.css']
})
export class IniciarComponent {
  loginDto: LoginDto = new LoginDto("", "");
  usuario: string = '';
  contrasena: string = '';

  constructor(
    private router: Router,
    private authService: AuthService,
    public infoService: InformacionJuegoService,
  ) { }
  iniciarJuego() {
    console.log('Intentando iniciar sesión con', this.loginDto);
    this.authService.login(this.loginDto).subscribe(jugador => {
      console.log('Autenticado:', jugador);
      if (jugador) {
        this.infoService.obtenerPuntajeCompra(jugador.id!).subscribe(puntaje => {
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
