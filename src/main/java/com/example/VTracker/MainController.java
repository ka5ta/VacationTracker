package com.example.VTracker;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/demo")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser (@RequestParam String adUserID, @RequestParam Country country){

        User newUser = new User();
        newUser.setAdUserID(adUserID);
        newUser.setCountry(country);
        String response = "User "+newUser.getAdUserID() + " saved.";
        return response;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }


}
