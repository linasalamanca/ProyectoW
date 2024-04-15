import { ComprarService } from './../shared/comprar.service';
import { Component } from '@angular/core';
import { InformacionVentaProducto } from '../dto/informacion-venta-producto';
import { VenderService } from '../shared/vender.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-comprar-list',
  templateUrl: './comprar.component.html',
  styleUrl: './comprar.component.css'
})
export class ComprarComponent {


  productos: InformacionVentaProducto[] = [];

  constructor(
    private comprarService: ComprarService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit(): void {
    const planetaId = this.route.snapshot.paramMap.get('planetaId');
    if (planetaId) {
      this.comprarService.listarProductos(+planetaId).subscribe(
        productos => this.productos = productos,
        error => {
          console.error('Error al cargar productos:', error);
          this.router.navigate(['/error']); // Asegúrate de que esta ruta está definida.
        }
      );
    } else {
      console.error('No se proporcionó un ID de planeta válido.');
      this.router.navigate(['/error']); // Redirigir a una ruta de error o página principal.
    }
  }
  
  // comprar.component.ts
verInventario(inventarioId: number): void {
  this.router.navigate([`/inventario/${inventarioId}`]);
}

}
