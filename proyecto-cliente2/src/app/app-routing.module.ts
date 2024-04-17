import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VenderComponent } from './vender/vender.component';
import { IniciarComponent } from './iniciar/iniciar.component';
import { EscogerEstrellaComponent } from './escoger-estrella/escoger-estrella.component';
import { ComprarComponent } from './comprar/comprar.component';
import { EscogerPlanetaComponent } from './escoger-planeta/escoger-planeta.component';
import { FinalizarComponent } from './finalizar/finalizar.component';

const routes: Routes = [
  { path: 'vender/list', component: VenderComponent},
  { path: 'comprar/list', component: ComprarComponent},
  { path: 'iniciar', component: IniciarComponent},
  { path: 'escoger-estrella/list', component: EscogerEstrellaComponent},
  { path: 'escoger-planeta/:planetaId/comprar/list', component: ComprarComponent},
  { path: 'estrella/planeta-list/:id', component: EscogerPlanetaComponent },
  { path: 'estrellas/:estrellaId/planetas', component: EscogerPlanetaComponent },
  { path: 'escoger-planeta/list/:id', component: EscogerPlanetaComponent },
  { path: 'escoger-planeta/:planetaId/comprar/list', component: ComprarComponent},
  { path: 'finalizar', component: FinalizarComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 
  
}
