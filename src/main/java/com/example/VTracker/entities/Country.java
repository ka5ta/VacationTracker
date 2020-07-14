package com.example.VTracker.entities;

import com.example.VTracker.errorAPI.NoSuchCountryCodeException;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Entity
@Data
@Table(name = "country_list")
public class Country implements Comparable<Country> {

    @Id
    @Column(length = 2)
    @Size(min = 2, max = 2)
    @JsonProperty("id")
    private String id;

    @NotNull
    @Column(unique = true)
    private String countryName;



    public Country() {
        super();
    }

    @JsonCreator
    public Country(String id) throws NoSuchCountryCodeException {
        this.id = id;
        List<String> countryIDs = Arrays.asList(Locale.getISOCountries());
        if (countryIDs.contains(getId())) {
            this.countryName = new Locale("", id).getDisplayCountry();
        } else throw new NoSuchCountryCodeException(id);
    }

    @Override
    public int compareTo(Country country) {
        return this.id.compareTo(country.getId());
    }
}
