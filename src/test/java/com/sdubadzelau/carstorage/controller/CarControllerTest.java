package com.sdubadzelau.carstorage.controller;

import com.sdubadzelau.carstorage.model.Car;
import com.sdubadzelau.carstorage.service.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

    @InjectMocks
    private CarController carController;

    @Mock
    private CarServiceImpl carService;

    private Car car1;
    private Car car2;
    private List<Car> cars;

    @BeforeEach
    public void init() {
        car1 = new Car("Make1", "Model1", "Color1", 0x2015);
        car2 = new Car("Make2", "Model2", "Color2", 0x2016);
        cars = Arrays.asList(car1, car2);
    }

    @Test
    public void whenGetCar_thenControllerReturnCar() {
        //given
        //when
        Mockito.when(carService.getCar("car-1")).thenReturn(Optional.of(car1));
        //then
        assertThat(carController.getCar("car-1")).isEqualTo(car1);
    }

    @Test
    public void whenGetCar_thenControllerReturnEmpty() {
        //given
        //when
        Mockito.when(carService.getCar("car-1")).thenReturn(Optional.empty());
        //then
        Throwable exception = assertThrows(ResponseStatusException.class,
                ()->{carController.getCar("car-1");} );
        System.out.println(exception);
        assertThat(exception.getMessage()).isEqualTo("404 NOT_FOUND \"Unable to find a car\"");
    }

    @Test
    public void whenGetAllCars_thenControllerShouldReturnAll() {
        //given
        //when
        Mockito.when(carService.getAllCars()).thenReturn(cars);
        //then
        assertThat(carController.getCars()).hasSize(2).hasSameElementsAs(cars);
    }

    @Test
    public void whenDeleteById_thenReturnNoContentStatus() {

        //given
        //when
        Mockito.when(carService.deleteCar("car-1")).thenReturn(true);
        ResponseEntity result = carController.deleteCar("car-1");
        //then
        verify(carService, times(1)).deleteCar("car-1");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void whenDeleteNotExistentCar_thenReturnNotFoundStatus() {

        //given
        //when
        Mockito.when(carService.deleteCar("car-1")).thenReturn(false);
        ResponseEntity result = carController.deleteCar("car-1");
        //then
        verify(carService, times(1)).deleteCar("car-1");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
