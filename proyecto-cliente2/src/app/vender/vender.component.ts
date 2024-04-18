import { Component, OnInit } from '@angular/core';
import { InformacionVentaProducto } from '../dto/informacion-venta-producto';
import { VenderService } from '../shared/vender.service';
import { Observable, switchMap } from 'rxjs';
import { InformacionJuegoService } from '../shared/informacion-juego.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';


@Component({
  selector: 'app-vender-list',
  templateUrl: './vender.component.html',
  styleUrl: './vender.component.css'
})

export class VenderComponent implements OnInit {
  timeElapsed: Observable<number>| undefined;
  productos: InformacionVentaProducto[] = [];
 

  constructor(
    public infoService: InformacionJuegoService,
    private venderService: VenderService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location,
  ) { }

  ngOnInit(): void {
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
        return this.venderService.listarProductos(planetaId);
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
      console.log('Inventario ID:', idInventario);
      this.infoService.obtenerPuntaje().pipe(
        switchMap(t => {
          this.infoService.setInfoPuntaje(t);
          return this.venderService.realizarVenta(idInventario);
        })
      ).subscribe({
        next: () => this.location.back(),
        error: err => {
          console.error('Ocurrió un error al realizar la venta:', err);
        }
      });
  }
}
