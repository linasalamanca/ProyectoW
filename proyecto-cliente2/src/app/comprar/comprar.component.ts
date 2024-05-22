import { ComprarService } from './../shared/comprar.service';
import { Component, OnInit } from '@angular/core';
import { InformacionVentaProducto } from '../dto/informacion-venta-producto';
import { VenderService } from '../shared/vender.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, switchMap } from 'rxjs';
import { InformacionJuegoService } from '../shared/informacion-juego.service';
import { AuthService } from '../shared/auth.service';
import { Location } from '@angular/common';



@Component({
  selector: 'app-comprar-list',
  templateUrl: './comprar.component.html',
  styleUrl: './comprar.component.css'
})
export class ComprarComponent implements OnInit {
  timeElapsed: Observable<number>| undefined;
  productos: InformacionVentaProducto[] = [];
  idJugador: number | undefined

  constructor(
    public infoService: InformacionJuegoService,
    private comprarService: ComprarService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location,
    private authService: AuthService,
  ) { }

  ngOnInit(): void {
    const currentUser = this.authService.getCurrentUser();
    if (currentUser) {
      this.idJugador = currentUser.id;
    } else {
      console.error('No hay un usuario autenticado');
    }

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
        return this.comprarService.listarProductos(planetaId);
      })
    ).subscribe(productos => {
      console.log('Productos loaded:', productos);
      this.productos = productos;
    });
  }


  verInventario(planetaId: number): void {
    this.router.navigate([`/inventario/${planetaId}`]);
  }

  realizarCompra(inventarioId: number) {
    if (this.idJugador === undefined) {
      console.error('No hay un ID de jugador disponible');
      return;
    }
    
    this.comprarService.actualizarPuntaje(this.idJugador!,inventarioId).subscribe(_=> 
      this.infoService.obtenerPuntajeCompra(this.idJugador!).subscribe(
        puntaje => this.infoService.setInfoPuntaje(puntaje)));

    /*this.comprarService.actualizarPuntaje(inventarioId).subscribe(() =>
          this.infoService.obtenerPuntajeCompra().subscribe(
            puntaje => this.infoService.setInfoPuntaje(puntaje))
        );*/
    

    this.comprarService.realizarCompra(inventarioId,this.idJugador).subscribe(() => this.location.back());
  }
}
