package com.example.VTracker.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name="country_list")
public class Country implements Comparable<Country> {

    @Id
    @Column(length=2)
    @Size(min=2, max=2)
    private String id;

    @Column(unique = true)
    private String countryName;

    @Override
    public int compareTo(Country country) {
        return this.id.compareTo(country.getId());
    }
}
