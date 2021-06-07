package com.sdubadzelau.carstorage.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Make {

    @Id
    @GeneratedValue(generator = "make-id-generator")
    @GenericGenerator(name = "make-id-generator",
            parameters = @Parameter(name = "prefix", value = "make"),
            strategy = "com.sdubadzelau.carstorage.StringBasedIdGenerator")
    private String id;
    private String name;

    public Make() {}

    public Make(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
