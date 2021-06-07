package com.sdubadzelau.carstorage.service;

import com.sdubadzelau.carstorage.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Optional<Car> getCar(String id);

    List<Car> getAllCars();

    Car addCar(Car car);

    /**
     * If Car already exists, then update car.
     * If Car doesn't exist, then create car.
     * @param id car -id
     * @param car car object
     * @return true - if Car was created, false - if Car was updated
     */
    boolean createOrUpdateCar(String id, Car car);

    /**
     * If Car exists, then delete car.
     * @param id - car id
     * @return true - if Car was deleted, false - if there is no car wit this id.
     */
    boolean deleteCar(String id);
}
