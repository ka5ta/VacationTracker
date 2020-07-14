package com.example.VTracker.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "holidays")
public class BankHoliday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String name;

    @NotEmpty
    private LocalDate day;

    @NotEmpty
    @JoinColumn(name = "country_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Country country;


}
