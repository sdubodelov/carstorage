import { Component, OnInit } from '@angular/core';
import { Car } from '../model/car';
import { CarService } from '../service/car.service';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {

  cars: Car[];

  constructor(private carService: CarService) {
  }

  ngOnInit() {
    this.carService.findAll().subscribe(data => {
      this.cars = data;
    });
  }

  delete(event, id) {
      this.carService.delete(id).toPromise()
        .then(result => this.cars = this.cars.filter(item => item.id !== id))
        .catch(error => console.log(error));
  }
}
