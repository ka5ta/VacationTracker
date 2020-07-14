package com.example.VTracker.repository;

import com.example.VTracker.entities.LeaveRequest;
import com.example.VTracker.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LeaveRepository extends CrudRepository<LeaveRequest, Integer> {

    Iterable<LeaveRequest> getLeaveRequestsByUser(User user);
}
