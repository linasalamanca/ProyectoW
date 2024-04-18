import { ComprarService } from './../shared/comprar.service';
import { Component, OnInit } from '@angular/core';
import { InformacionVentaProducto } from '../dto/informacion-venta-producto';
import { VenderService } from '../shared/vender.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, switchMap } from 'rxjs';
import { InformacionJuegoService } from '../shared/informacion-juego.service';


@Component({
  selector: 'app-comprar-list',
  templateUrl: './comprar.component.html',
  styleUrl: './comprar.component.css'
})
export class ComprarComponent implements OnInit {
  timeElapsed: Observable<number>| undefined;
  productos: InformacionVentaProducto[] = [];

  constructor(
    public infoService: InformacionJuegoService,
    private comprarService: ComprarService,
    private route: ActivatedRoute,
    private router: Router,
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
        return this.comprarService.listarProductos(planetaId);
      })
    ).subscribe(productos => {
      console.log('Productos loaded:', productos); // Log the products after subscription
      this.productos = productos;
    }, error => {
      console.error('Error al obtener productos:', error);
    });
  }


  verInventario(planetaId: number): void {
    this.router.navigate([`/inventario/${planetaId}`]);
  }

  realizarCompra(inventarioId: number){
    
  }


}
