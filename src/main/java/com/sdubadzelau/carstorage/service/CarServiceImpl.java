package com.sdubadzelau.carstorage.service;

import com.sdubadzelau.carstorage.model.Car;
import com.sdubadzelau.carstorage.model.datamuse.SimilarWord;
import com.sdubadzelau.carstorage.repository.CarRepository;
import com.sdubadzelau.carstorage.utils.JSONParser;
import datamuse.DatamuseQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.datamuseQuery = new DatamuseQuery();
    }

    @Autowired
    private CarRepository carRepository;

    private DatamuseQuery datamuseQuery;

    @Override
    public Optional<Car> getCar(String id) {
        Optional<Car> optCar =  carRepository.findById(id);
        if (optCar.isPresent()) {
            Car thisCar = optCar.get();
            thisCar.setSoundsLike(JSONParser.parseSimilarWords(datamuseQuery.soundsSimilar(thisCar.getModel()))
                    .stream().limit(2).map(SimilarWord::getWord).collect(Collectors.joining(", ")));
        }
        return optCar;
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> cars = (List<Car>) carRepository.findAll();
        if (!CollectionUtils.isEmpty(cars)) {
            CompletableFuture[] responseFutures =
                    cars.stream()
                        .map(car -> CompletableFuture
                                .supplyAsync(() -> datamuseQuery.soundsSimilar(car.getModel()))
                                .thenAccept(similarWordsJson -> car.setSoundsLike(
                                                JSONParser.parseSimilarWords(similarWordsJson)
                                                .stream().limit(2).map(SimilarWord::getWord).collect(Collectors.joining(", ")))
                                )
                        )
                        .toArray(CompletableFuture[]::new);
            CompletableFuture.allOf(responseFutures).join();
        }
        return cars;
    }

    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public boolean createOrUpdateCar(String id, Car car) {
        Optional<Car> carById = carRepository.findById(id);
        carRepository.save(car);
        if (!carById.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCar(String id) {
        try {
            carRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }
}
