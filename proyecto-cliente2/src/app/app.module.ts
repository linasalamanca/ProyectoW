import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { VenderComponent } from './vender/vender.component';
import { HttpClientModule } from '@angular/common/http';
import { ComprarComponent } from './comprar/comprar.component';
import { IniciarComponent } from './iniciar/iniciar.component';
import { FinalizarComponent } from './finalizar/finalizar.component';
import { EscogerEstrellaComponent } from './escoger-estrella/escoger-estrella.component';
import { EscogerPlanetaComponent } from './escoger-planeta/escoger-planeta.component';

@NgModule({
  declarations: [
    AppComponent,
    VenderComponent,
    ComprarComponent,
    IniciarComponent,
    FinalizarComponent,
    EscogerEstrellaComponent,
    EscogerPlanetaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
