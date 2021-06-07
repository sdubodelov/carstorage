package com.sdubadzelau.carstorage.repository;

import com.sdubadzelau.carstorage.CarstorageApplication;
import com.sdubadzelau.carstorage.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {CarstorageApplication.class})
public class CarRepositoryUnitTest {

    @Autowired
    private CarRepository repository;

    private Car car1;
    private Car car2;
    private List<Car> cars;

    @BeforeEach
    public void init() {
        car1 = new Car("Make1", "Model1", "Color1", 0x2015 );
        car2 = new Car("Make2", "Model2", "Color2", 0x2016);
        cars = Arrays.asList(car1, car2);
        repository.deleteAll();
    }

    @Test
    public void whenDeleteByIdFromRepository_thenDeletingShouldBeSuccessful() {

        // given
        repository.save(car1);
        repository.save(car2);
        //when
        repository.deleteById(car1.getId());
        //then
        assertThat(repository.count()).isEqualTo(1);
    }

    @Test
    public void whenDeleteAllFromRepository_thenRepositoryShouldBeEmpty() {
        repository.save(car1);
        repository.save(car2);

        repository.deleteAll();

        assertThat(repository.count()).isEqualTo(0);
    }

    @Test
    public void whenDeleteNotExistentCar_thenThrownException() {
        repository.save(car1);
        repository.save(car2);

        assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById("nonexistendid");
        });
    }

    @Test
    public void whenFindById_thenRepositoryShouldHaveThisCar() {
        repository.save(car1);

        Optional<Car> carFromRepository =  repository.findById(car1.getId());

        assertThat(carFromRepository.isPresent()).isEqualTo(true);
    }

    @Test
    public void whenFindById_thenRepositoryReturnEmpty() {
        repository.save(car1);
        System.out.println("count - " + repository.count());
        assertThat(repository.findById("nonexistentid").isPresent()).isEqualTo(false);
    }

    @Test
    public void whenFindAllFromRepository_thenRepositoryShouldReturnAll() {
        repository.save(car1);
        repository.save(car2);

        List<Car> allCarsFromRepo = (List<Car>) repository.findAll();

        assertThat(allCarsFromRepo).hasSize(2).hasSameElementsAs(cars);
    }
}
