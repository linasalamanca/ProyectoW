import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VenderComponent } from './vender/vender.component';
import { IniciarComponent } from './iniciar/iniciar.component';
import { EscogerEstrellaComponent } from './escoger-estrella/escoger-estrella.component';

const routes: Routes = [
  { path: 'vender/list', component: VenderComponent},
  { path: 'comprar/list', component: VenderComponent},
  { path: 'iniciar', component: IniciarComponent},
  { path: 'escoger-estrella/list', component: EscogerEstrellaComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 
  
}
