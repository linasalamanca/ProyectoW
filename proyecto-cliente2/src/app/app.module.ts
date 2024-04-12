import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { VenderComponent } from './vender/vender.component';
import { HttpClientModule } from '@angular/common/http';
import { ComprarComponent } from './comprar/comprar.component';

@NgModule({
  declarations: [
    AppComponent,
    VenderComponent,
    ComprarComponent
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
