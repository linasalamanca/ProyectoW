import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VenderComponent } from './vender/vender.component';
import { IniciarComponent } from './iniciar/iniciar.component';
import { EscogerEstrellaComponent } from './escoger-estrella/escoger-estrella.component';
import { ComprarComponent } from './comprar/comprar.component';
import { EscogerPlanetaComponent } from './escoger-planeta/escoger-planeta.component';
import { FinalizarComponent } from './finalizar/finalizar.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  { path: 'vender/list', component: VenderComponent,canActivate: [AuthGuard]},
  { path: 'comprar/list', component: ComprarComponent,canActivate: [AuthGuard]},
  { path: 'iniciar', component: IniciarComponent},
  { path: 'escoger-estrella/list', component: EscogerEstrellaComponent,canActivate: [AuthGuard] },
  { path: 'escoger-planeta/:planetaId/comprar/list', component: ComprarComponent,canActivate: [AuthGuard]},
  { path: 'escoger-planeta/:planetaId/vender/list', component: VenderComponent,canActivate: [AuthGuard]},
  { path: 'estrella/planeta-list/:id', component: EscogerPlanetaComponent,canActivate: [AuthGuard] },
  { path: 'estrellas/:estrellaId/planetas', component: EscogerPlanetaComponent,canActivate: [AuthGuard] },
  { path: 'escoger-planeta/list/:id', component: EscogerPlanetaComponent ,canActivate: [AuthGuard]},
  { path: 'escoger-planeta/:planetaId/comprar/list', component: ComprarComponent,canActivate: [AuthGuard]},
  { path: 'finalizar', component: FinalizarComponent,canActivate: [AuthGuard]},
  { path: '', pathMatch: 'full', redirectTo: 'iniciar' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 
  
}
