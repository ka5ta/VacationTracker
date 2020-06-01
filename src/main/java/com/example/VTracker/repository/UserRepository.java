package com.example.VTracker.repository;

import com.example.VTracker.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {

    List<User> findByLastnameOrName(String lastname, String name);
    Optional<User> findByAdUserID(String adUserID);
}
