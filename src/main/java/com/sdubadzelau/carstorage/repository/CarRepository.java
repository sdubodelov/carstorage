package com.sdubadzelau.carstorage.repository;

import com.sdubadzelau.carstorage.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, String> {}
