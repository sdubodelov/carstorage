import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Car } from '../model/car';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class CarService {

  private carsUrl: string;

  constructor(private http: HttpClient) {
    this.carsUrl = 'http://localhost:8080/cars';
  }

  public findAll(): Observable<Car[]> {
    return this.http.get<Car[]>(this.carsUrl);
  }

  public getById(id: string): Observable<Car> {
    return this.http.get<Car>(this.carsUrl+"/" +id);
  }

  public save(car: Car) {
    return this.http.post<Car>(this.carsUrl, car);
  }

  public edit(car: Car) {
    return this.http.put<Car>(this.carsUrl, car);
  }

  public delete(id: string): Observable<Car> {
    return this.http.delete<Car>(this.carsUrl+"/" +id);
  }
}
