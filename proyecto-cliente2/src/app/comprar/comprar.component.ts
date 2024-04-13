import { Component } from '@angular/core';
import { InformacionVentaProducto } from '../dto/informacion-venta-producto';
import { VenderService } from '../shared/vender.service';


@Component({
  selector: 'app-person-list',
  templateUrl: './comprar.component.html',
  styleUrl: './comprar.component.css'
})
export class ComprarComponent {


  productos: InformacionVentaProducto[] = [];

  constructor(
    private venderService: VenderService,
  ) { }

  ngOnInit(): void {
    this.venderService.listarProductos().subscribe(producto => this.productos = producto)
  }
}
