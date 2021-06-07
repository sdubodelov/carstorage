import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { CarListComponent } from './car-list/car-list.component';
import { CarFormComponent } from './car-form/car-form.component';
import {AppRoutingModule} from "./app-routing.module";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {CarService} from "./service/car.service";
import { CarViewComponent } from './car-view/car-view.component';
import { CarNotFoundComponent } from './car-not-found/car-not-found.component';


@NgModule({
  declarations: [
    AppComponent,
    CarListComponent,
    CarFormComponent,
    CarViewComponent,
    CarNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [CarService],
  bootstrap: [AppComponent]
})
export class AppModule { }
