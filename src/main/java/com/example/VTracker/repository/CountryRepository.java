package com.example.VTracker.repository;

import com.example.VTracker.entities.Country;
import org.springframework.data.repository.CrudRepository;



public interface CountryRepository extends CrudRepository<Country, String > {


}
