package com.sdubadzelau.carstorage.service;

import com.sdubadzelau.carstorage.model.Make;

import java.util.List;
import java.util.Optional;

public interface MakeService {
    Optional<Make> getMake(String id);

    List<Make> getAllMakes();

    Make addMake(Make make);

    /**
     * If Make already exists, then update make.
     * If Make doesn't exist, then create make.
     * @param id make -id
     * @param make make object
     * @return true - if Make was created, false - if make was updated
     */
    boolean createOrUpdateMake(String id, Make make);

    /**
     * If Make exists, then delete make.
     * @param id - make id
     * @return true - if Make was deleted, false - if there is no make wit this id.
     */
    boolean deleteMake(String id);
}
