import { Component, OnInit } from '@angular/core';
import { Car } from '../model/car';
import { CarService } from '../service/car.service';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-car-view',
  templateUrl: './car-view.component.html',
  styleUrls: ['./car-view.component.css']
})
export class CarViewComponent implements OnInit {

  car: Car;
  id: string;
  carNotFound: boolean;

  constructor(
    private carService: CarService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit() {
    this.carNotFound = false;
    this.id = this.route.snapshot.params['id'];

    if (this.id) {
      this.carService.getById(this.id).toPromise()
        .then(car => this.car = car)
        .catch(error => this.handleError(error));
    }
  }

  handleError(error) {
    if (error && error.status == 404) {
      this.carNotFound = true;
      //this.router.navigate(['/carnotfound']);
    }
    else {
      this.router.navigate(['/cars']);
    }
  }
}
