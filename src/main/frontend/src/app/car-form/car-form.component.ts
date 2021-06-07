import {Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CarService } from '../service/car.service';
import { Car } from '../model/car';
import {first} from "rxjs/operators";

@Component({
  selector: 'app-car-form',
  templateUrl: './car-form.component.html',
  styleUrls: ['./car-form.component.css']
})
export class CarFormComponent implements OnInit {

  car: Car;
  id: string;
  isAddMode: boolean;
  carNotFound: boolean;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private carService: CarService) {
    this.car = new Car();
  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.isAddMode = !this.id;
    this.carNotFound = false;

    if (!this.isAddMode) {
      this.carService.getById(this.id)
        .pipe(first())
        .subscribe(x => this.car = x,
          error => this.handleError(error, this));
    }
  }

  onSubmit() {
    if (this.isAddMode) {
      this.carService.save(this.car).subscribe(result => this.gotoUserList(), error => console.log(error));
    }
    else {
      this.carService.edit(this.car).toPromise()
        .then(result => this.gotoUserList())
        .catch(error => console.log(error));
    }
  }

  gotoUserList() {
    this.router.navigate(['/cars']);
  }

  handleError(error, component) {
    if (error && error.status == 404) {
      component.carNotFound = true;
      //this.router.navigate(['/carnotfound']);
    }
    else {
      this.router.navigate(['/cars']);
    }
  }
}
