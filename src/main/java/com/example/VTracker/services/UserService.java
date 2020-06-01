package com.example.VTracker.services;

import com.example.VTracker.entities.User;
import com.example.VTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findByAdUserID(Integer id) {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void delete(Integer AdUserID) {

    }

    @Override
    public List<User> findByLastname(String lastName) {
       return null;
    }
}
