import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CarListComponent } from './car-list/car-list.component';
import { CarFormComponent } from './car-form/car-form.component';
import {CarViewComponent} from "./car-view/car-view.component";
import {CarNotFoundComponent} from "./car-not-found/car-not-found.component";

const routes: Routes = [
  { path: 'cars', component: CarListComponent },
  { path: 'addcar', component: CarFormComponent },
  { path: 'editcar/:id', component: CarFormComponent },
  { path: 'viewcar/:id', component: CarViewComponent },
  { path: 'carnotfound', component: CarNotFoundComponent },
  { path: 'deletecar/:id', component: CarListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
