package com.example.VTracker.controllers;


import com.example.VTracker.entities.Country;
import com.example.VTracker.entities.User;
import com.example.VTracker.entities.UserHolidaysBalance;
import com.example.VTracker.errorAPI.NoSuchUserException;
import com.example.VTracker.repository.UserHolidaysBalanceRepository;
import com.example.VTracker.repository.UserRepository;
import com.example.VTracker.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserHolidaysBalanceRepository userHolidaysBalanceRepository;
    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user)  {
        return userRepository.save(user);
    }


    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> getAllUsers() {
        return userRepository.findByOrderByLastnameAsc();
    }


    @GetMapping
    public @ResponseBody
    List<User> findUsersByKeyword(@RequestParam String keyword) {
        return userRepository.findUsersByKeyword(keyword);
    }


    @DeleteMapping(path = "/{adUserID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("adUserID") String adUserID) throws NoSuchUserException {
        userService.deleteByAdUserID(adUserID);
        //return String.format("User with ID:%s, Name:%s, Lastname:%s was removed.", user.getId(), user.getName(), user.getLastname());
    }

    // ---- return current holiday balance for user -----//
    @GetMapping(path = "/{adUserID}/balance")
    @ResponseStatus(HttpStatus.OK)
    public UserHolidaysBalance getUserBalanceById(@PathVariable("adUserID") String id) {

        UserHolidaysBalance userHolidaysBalanceById = userHolidaysBalanceRepository.getUserHolidaysBalanceByAdUserID(id);
        return userHolidaysBalanceById;
    }

    @GetMapping(path = "/{adUserID}")
    @ResponseStatus(HttpStatus.OK)
    public User findUserByID(@PathVariable("adUserID") String adUserID) throws NoSuchUserException {
        return userService.findUserByAdUserID(adUserID);
    }


}

