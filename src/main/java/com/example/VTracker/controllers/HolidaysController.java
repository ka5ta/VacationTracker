package com.example.VTracker.controllers;

import com.example.VTracker.entities.BankHoliday;
import com.example.VTracker.entities.User;
import com.example.VTracker.errorAPI.NoSuchUserException;
import com.example.VTracker.repository.HolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/holidays")
public class HolidaysController {


    @Autowired
    private HolidaysRepository holidaysRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BankHoliday addBankHoliday(@RequestBody BankHoliday holiday){
        return holidaysRepository.save(holiday);
    }

    @GetMapping(path="/all")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<BankHoliday> getAllBankHolidays() {
        return holidaysRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBankHoliday(@PathVariable("id") Integer id)  {
        holidaysRepository.deleteById(id);

    }

}
