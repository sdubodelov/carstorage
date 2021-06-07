package com.sdubadzelau.carstorage.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Objects;

@Entity
public class Car {

    public Car() {}

    public Car(String make, String model, String color, Integer year) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
    }

    @Id
    @GeneratedValue(generator = "car-id-generator")
    @GenericGenerator(name = "car-id-generator",
            parameters = @Parameter(name = "prefix", value = "car"),
            strategy = "com.sdubadzelau.carstorage.CarIdGenerator")
    private String id;
    private String make;
    private String model;
    private String color;
    private Integer year;
    @Transient
    private String soundsLike;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getSoundsLike() {
        return soundsLike;
    }

    public void setSoundsLike(String soundsLike) {
        this.soundsLike = soundsLike;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && make.equals(car.make) && model.equals(car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, make, model);
    }
}
