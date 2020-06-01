package com.example.VTracker.services;

import com.example.VTracker.entities.User;

import java.util.List;

public interface IUserService {

    User findByAdUserID(Integer id);
    User save(User user);
    List<User> findAll();
    void delete(Integer AdUserID);
    List<User> findByLastname(String lastName);

}
