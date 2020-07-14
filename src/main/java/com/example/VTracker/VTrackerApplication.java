package com.example.VTracker;

import com.example.VTracker.entities.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.DataInput;

@SpringBootApplication
public class VTrackerApplication {
    @Autowired
    private ObjectMapper objectMapper;

    public static void main(String[] args) {


        SpringApplication.run(VTrackerApplication.class, args);
    }


}
