import { Component, OnInit } from '@angular/core';
import { InformacionVentaProducto } from '../dto/informacion-venta-producto';
import { VenderService } from '../shared/vender.service';
import { Observable, mergeMap, switchMap } from 'rxjs';
import { InformacionJuegoService } from '../shared/informacion-juego.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { AuthService } from '../shared/auth.service';


@Component({
  selector: 'app-vender-list',
  templateUrl: './vender.component.html',
  styleUrl: './vender.component.css'
})

export class VenderComponent implements OnInit {
  timeElapsed: Observable<number>| undefined;
  productos: InformacionVentaProducto[] = [];
  idJugador: number | undefined
 

  constructor(
    public infoService: InformacionJuegoService,
    private venderService: VenderService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location,
    private authService: AuthService,
  ) { }

  ngOnInit(): void {
    this.idJugador = isNaN(parseInt(this.authService.id())) ? undefined : parseInt(this.authService.id());

    this.route.paramMap.pipe(
      switchMap(params => {
        const idStr = params.get('planetaId'); 
        if (idStr === null) {
          throw new Error('Planeta ID is required'); 
        }
        const planetaId = Number(idStr);
        if (isNaN(planetaId)) {
          throw new Error('Planeta ID must be a number');
        }
        return this.venderService.listarProductos(this.idJugador!);
      })
    ).subscribe(productos => {
      console.log('Productos loaded:', productos); // Log the products after subscription
      this.productos = productos;
    }, error => {
      console.error('Error al obtener productos:', error);
    });  }

    verInventario(planetaId: number): void {
      this.router.navigate([`/inventario/${planetaId}`]);
    }
  
    realizarVenta(idInventario: number) {
      if (this.idJugador === undefined) {
        console.error('No hay un ID de jugador disponible');
        return;
      }

     /* this.venderService.actualizarPuntaje(this.idJugador!,idInventario).subscribe(_=> 
        this.infoService.obtenerPuntajeVenta(this.idJugador!).subscribe(
          puntaje => this.infoService.setInfoPuntaje(puntaje)));
          
      this.venderService.realizarVenta(idInventario,this.idJugador).subscribe(() => this.location.back());*/
      this.venderService.realizarVenta(idInventario, this.idJugador!).pipe(
         mergeMap(() => this.venderService.actualizarPuntaje(this.idJugador!, idInventario)),
         mergeMap(() => this.infoService.obtenerPuntajeVenta(this.idJugador!)),
      ).subscribe(puntaje => {
         this.infoService.setInfoPuntaje(puntaje);
         this.location.back();
      });
  }

}
