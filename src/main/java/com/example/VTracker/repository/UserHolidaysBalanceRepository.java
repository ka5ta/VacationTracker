package com.example.VTracker.repository;

import com.example.VTracker.entities.UserHolidaysBalance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserHolidaysBalanceRepository extends CrudRepository<UserHolidaysBalance, Integer> {

    @Query("Select uhv from UserHolidaysBalance uhv where uhv.adUserID=:id")
    UserHolidaysBalance getUserHolidaysBalanceByAdUserID(
            @Param("id") String id);

}
