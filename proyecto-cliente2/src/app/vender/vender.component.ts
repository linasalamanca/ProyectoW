import { Component } from '@angular/core';
import { InformacionVentaProducto } from '../dto/informacion-venta-producto';
import { VenderService } from '../shared/vender.service';


@Component({
  selector: 'app-vender-list',
  templateUrl: './vender.component.html',
  styleUrl: './vender.component.css'
})

export class VenderComponent {

 
  productos: InformacionVentaProducto[] = [];

  constructor(
    private venderService: VenderService,
  ) { }

  ngOnInit(): void {
    this.venderService.listarProductos().subscribe(producto => this.productos = producto)
  }
}
