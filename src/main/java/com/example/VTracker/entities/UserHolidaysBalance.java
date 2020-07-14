package com.example.VTracker.entities;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
@Immutable
//@Synchronize({"users", "leave_request"})
@Table(name = "users_holidays_view")
@Subselect("select uhv.* from users_holidays_view uhv")
public class UserHolidaysBalance implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "ad_userid")
    private String adUserID;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "hashed_password")
    private String hashedPassword;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "vacation_days")
    private Integer vacationDays;
    @Column(name = "remaining_holidays")
    private Integer remainingHolidays;
    @Column(name = "approved_holidays")
    private Integer approvedHolidays;

    public UserHolidaysBalance() {
    }
}
