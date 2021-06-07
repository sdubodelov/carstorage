package com.sdubadzelau.carstorage.service;


import com.sdubadzelau.carstorage.model.Car;
import com.sdubadzelau.carstorage.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;

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
    public void whenGetCar_thenServiceReturnCar() {
        //given
        //when
        Mockito.when(carRepository.findById("car-1")).thenReturn(Optional.of(car1));
        //then
        assertThat(carService.getCar("car-1").isPresent()).isEqualTo(true);
    }

    @Test
    public void whenGetCar_thenServiceReturnEmpty() {
        //given
        //when
        Mockito.when(carRepository.findById("car-1")).thenReturn(Optional.empty());
        //then
        assertThat(carService.getCar("car-1").isPresent()).isEqualTo(false);
    }

    @Test
    public void whenGetAllCars_thenServiceShouldReturnAll() {
        //given
        //when
        Mockito.when(carRepository.findAll()).thenReturn(cars);
        //then
        assertThat(carService.getAllCars()).hasSize(2).hasSameElementsAs(cars);
    }

    @Test
    public void whenDeleteByIdFromRepository_thenDeletingShouldBeSuccessful() {

        //given
        //when
        doNothing().when(carRepository).deleteById("car-1");
        boolean result = carService.deleteCar("car-1");
        //then
        verify(carRepository, times(1)).deleteById("car-1");
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void whenDeleteNotExistentCar_thenThrownException() {

        //given
        //when
        doThrow(EmptyResultDataAccessException.class).when(carRepository).deleteById("car-1");
        boolean result = carService.deleteCar("car-1");
        //then
        verify(carRepository, times(1)).deleteById("car-1");
        assertThat(result).isEqualTo(false);
    }
}
