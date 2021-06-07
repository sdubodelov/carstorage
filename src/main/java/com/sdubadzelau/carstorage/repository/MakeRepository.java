package com.sdubadzelau.carstorage.repository;

import com.sdubadzelau.carstorage.model.Make;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakeRepository extends CrudRepository<Make, String> {}
