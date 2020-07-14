package com.example.VTracker.services;

import com.example.VTracker.entities.Country;
import com.example.VTracker.errorAPI.NoSuchCountryCodeException;
import com.example.VTracker.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public Country searchById(String id) throws NoSuchCountryCodeException {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        return optionalCountry.orElseThrow(() -> new NoSuchCountryCodeException(id));

    }

}
