package com.example.VTracker;


import lombok.Data;


import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String adUserID;

    private String name;

    private String email;

    @ManyToOne
    @JoinColumn(name="country_id")
    private Country country;

    private int vacationDays;


}
