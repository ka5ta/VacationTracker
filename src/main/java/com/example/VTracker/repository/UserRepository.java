package com.example.VTracker.repository;

import com.example.VTracker.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {



    Optional <User> findByAdUserID(String adUserID);

    Iterable<User> findByOrderByLastnameAsc();


    @Query(value="SELECT * FROM users u " +
            "WHERE u.name COLLATE utf8_general_ci LIKE %:keyword% COLLATE utf8_general_ci " +
            "OR u.lastname COLLATE utf8_general_ci LIKE %:keyword% COLLATE utf8_general_ci " +
            "ORDER BY u.lastname ASC", nativeQuery = true)
    List<User> findUsersByKeyword(@Param("keyword") String keyword);


}

