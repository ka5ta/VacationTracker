package com.example.VTracker.controllers;


import com.example.VTracker.entities.Country;
import com.example.VTracker.entities.User;
import com.example.VTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping(path = "/demo")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path="/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }


    @GetMapping(path = "/all")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }


    @GetMapping(path = "/usersByNameOrLastname")
    public @ResponseBody
    Iterable<User> findByLastnameOrName(@RequestParam String lastname, @RequestParam String name) {
        return userRepository.findByLastnameOrName(lastname, name);
    }

    @DeleteMapping(path = "/delete/{adUserID}")
    public @ResponseBody
    String deleteUser(@PathVariable("adUserID") String adUserID) {
        User foundUser = userRepository.findByAdUserID(adUserID).orElseThrow(
                () -> new ResourceNotFoundException("User not found with userId " + adUserID)
        );

        userRepository.delete(foundUser);
        return String.format("User with ID:%s, Name:%s, Lastname:%s was removed.", foundUser.getId(), foundUser.getName(), foundUser.getLastname());
    }
}

