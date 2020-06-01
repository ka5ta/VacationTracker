package com.example.VTracker.entities;



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
    private String adUserID;

    @NotEmpty
    private String name;

    @NotEmpty
    private String lastname;

    @NotEmpty
    private String email;

    @ManyToOne
    @JoinColumn(name="country_id")
    private Country country;

    private int vacationDays;


}
