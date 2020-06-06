package com.example.VTracker.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Data
@Table(name="country_list")
public class Country implements Comparable<Country> {

    @Id
    @Column(length=2)
    @Size(min=2, max=2)
    private String id;

    @NotNull
    @Column(unique = true)
    private String countryName;


    public Country() {
        super();
    }

    @JsonCreator
    public Country(@JsonProperty("id") String id) {
        this.id = id;
        this.countryName= new Locale("", id).getDisplayCountry();
    }

    @Override
    public int compareTo(Country country) {
        return this.id.compareTo(country.getId());
    }
}
