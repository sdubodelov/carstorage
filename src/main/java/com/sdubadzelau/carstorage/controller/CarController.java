package com.sdubadzelau.carstorage.controller;

import com.sdubadzelau.carstorage.model.Car;
import com.sdubadzelau.carstorage.repository.CarRepository;
import com.sdubadzelau.carstorage.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CarController {

    public CarController(CarRepository carRepository, CarService carService) {
        this.carRepository = carRepository;
        this.carService = carService;
    }

    private CarService carService;

    private final CarRepository carRepository;

    @GetMapping("/cars")
    public List<Car> getCars() {
        return carService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public Car getCar(@PathVariable("id") String id) {

        return carService.getCar(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find a car"));
    }

    @PostMapping("/cars")
    public ResponseEntity addCar(@RequestBody Car car) {

        carService.addCar(car);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity editCar(@PathVariable("id") String id, @RequestBody Car car) {

        if (carService.createOrUpdateCar(id, car)) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity deleteCar(@PathVariable("id") String id) {

        if (carService.deleteCar(id)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
