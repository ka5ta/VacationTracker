package com.example.VTracker.repository;

import com.example.VTracker.entities.LeaveRequest;
import org.springframework.data.repository.CrudRepository;

public interface LeaveRepository extends CrudRepository<LeaveRequest, Integer> {

}
