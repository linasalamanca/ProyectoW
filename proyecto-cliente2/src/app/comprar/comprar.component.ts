import { ComprarService } from './../shared/comprar.service';
import { Component } from '@angular/core';
import { InformacionVentaProducto } from '../dto/informacion-venta-producto';
import { VenderService } from '../shared/vender.service';


@Component({
  selector: 'app-comprar-list',
  templateUrl: './comprar.component.html',
  styleUrl: './comprar.component.css'
})
export class ComprarComponent {


  productos: InformacionVentaProducto[] = [];

  constructor(
    private comprarService: ComprarService,
  ) { }

  ngOnInit(): void {
    this.comprarService.listarProductos().subscribe(producto => this.productos = producto)
  }
}
