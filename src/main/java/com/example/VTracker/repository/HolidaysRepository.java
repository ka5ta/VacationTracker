package com.example.VTracker.repository;

import com.example.VTracker.entities.BankHoliday;
import com.example.VTracker.entities.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


public interface HolidaysRepository extends CrudRepository<BankHoliday, Integer> {

    @Query("SELECT h FROM BankHoliday h " +
            "WHERE h.country = :country AND h.day BETWEEN :startDate AND :endDate")
    List<BankHoliday> findAllHolidaysByCountryAndDateRange(
            @Param("country") Country country,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}
