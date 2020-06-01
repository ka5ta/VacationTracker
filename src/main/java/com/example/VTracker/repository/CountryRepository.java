package com.example.VTracker.repository;

import com.example.VTracker.entities.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, String > {


}
