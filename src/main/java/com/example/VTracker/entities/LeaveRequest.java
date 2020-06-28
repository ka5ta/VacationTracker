package com.example.VTracker.entities;

import com.example.VTracker.staticCategories.LeaveCategories;
import com.example.VTracker.staticCategories.LeaveStatus;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name="leave_request")
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    private User user;
    @NotNull
    private LeaveCategories leaveCategory;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

    @NotNull
    @ManyToOne
    private User approver;

    @NotNull
    private LeaveStatus leaveStatus;

    @NotNull
    private Integer leaveCost;


}
