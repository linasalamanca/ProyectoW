export class InformacionVentaProducto {
    constructor(
        public nombreProducto: string,
        public cantidad: number,
        public precio: number,
        public inventarioId: number,
    ){}
}
