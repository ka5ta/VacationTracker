package com.example.VTracker.entities;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "users")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "country")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotEmpty
    private String adUserID;

    @NotEmpty
    private String name;

    @NotEmpty
    private String lastname;

    @NotEmpty
    private String email;

    @NotEmpty
    private String hashedPassword;



    @JsonDeserialize(as = Country.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;
    //todo URGENT: Country is mapped as String not Object which cause throwing exception, and Country require object

    private int vacationDays;

    public User(@NotEmpty String adUserID, @NotEmpty String name, @NotEmpty String lastname, @NotEmpty String email, @NotEmpty String hashedPassword, Country country, int vacationDays) {
        this.adUserID = adUserID;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.country = country;
        this.vacationDays = vacationDays;
    }
}

//todo user Standard user,  Storing requested holidays, approval route
