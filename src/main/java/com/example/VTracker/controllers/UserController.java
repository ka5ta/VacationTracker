package com.example.VTracker.controllers;


import com.example.VTracker.entities.User;
import com.example.VTracker.repository.UserRepository;
import com.example.VTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }


    @GetMapping(path = "/usersByNameOrLastname")
    public @ResponseBody
    Iterable<User> findByLastnameOrName(@RequestParam String lastname, @RequestParam String name) {
        return userRepository.findByLastnameOrName(lastname, name);
        //todo NEXT: searching by part of lastName
    }


    @DeleteMapping(path = "/{adUserID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("adUserID") String adUserID) {
        userService.deleteByAdUserID(adUserID);


        //return String.format("User with ID:%s, Name:%s, Lastname:%s was removed.", user.getId(), user.getName(), user.getLastname());
    }
}

