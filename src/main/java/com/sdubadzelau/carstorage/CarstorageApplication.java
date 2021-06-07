package com.sdubadzelau.carstorage;

import com.sdubadzelau.carstorage.model.Car;
import com.sdubadzelau.carstorage.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Year;
import java.util.stream.Stream;

@SpringBootApplication
public class CarstorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarstorageApplication.class, args);
	}

	/*@Bean
	CommandLineRunner init(CarRepository carRepository) {
		return args -> {
			Stream.of("VW", "Audi", "Mercedes", "Tesla", "Dodge").forEach(make -> {
				Car user = new Car(make, make + "_model", make + "_color", Year.of(2020));
				carRepository.save(user);
			});
			carRepository.findAll().forEach(System.out::println);
		};
	}*/
}
