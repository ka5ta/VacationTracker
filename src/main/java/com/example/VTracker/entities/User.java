package com.example.VTracker.entities;



import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "users")
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="country_id")
    private Country country;
    //todo URGENT: Country is mapped as String not Object which cause throwing exception, and Country require object

    private int vacationDays;


}
