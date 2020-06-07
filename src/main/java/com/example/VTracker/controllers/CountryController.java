package com.example.VTracker.controllers;

import com.example.VTracker.entities.Country;
import com.example.VTracker.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/country")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Country saveCountry(@RequestBody Country country) {
        return countryRepository.save(country);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Country> getCountryList() {
        return countryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Country>> country(@PathVariable("id") String id) {
        Optional<Country> countryById = countryRepository.findById(id);
        if (countryById.isPresent()) {
            return new ResponseEntity(countryById, HttpStatus.OK);
        } else {
            return new ResponseEntity("No such country: "+id, HttpStatus.NOT_FOUND);
        }
    }//todo OPTIONAL: create CountryService that will handle throwing exception instead of Optional

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCountry(@PathVariable("id") String id) {
        countryRepository.deleteById(id);
        return String.format("Country id: %s is deleted.", id);
    }


    @GetMapping("/full-list")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> countryList() {
        List<String> countries = Arrays.asList(Locale.getISOCountries());

        return  countries.stream()
                .map(String::toUpperCase)  //country->country.toUpperCase()
                .collect(Collectors.toMap(
                        Function.identity(),  //key: country->country
                        cc -> new Locale("", cc).getDisplayCountry(), //value
                        (v1,v2)->{throw new RuntimeException(String.format("Duplicate key for values %s and %s", v1, v2)); },
                        TreeMap::new  //TreeMap enforce countries sorting order
                ));
    }
}
// todo OPTIONAL: Tests for Controllers
