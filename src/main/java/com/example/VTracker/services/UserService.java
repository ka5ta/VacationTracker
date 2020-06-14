package com.example.VTracker.services;

import com.example.VTracker.entities.User;
import com.example.VTracker.errorAPI.NoSuchUserException;
import com.example.VTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void deleteByAdUserID(String adUserID) throws NoSuchUserException {
        User user = findUserByAdUserID(adUserID);
        userRepository.delete(user);
    }

    public User findUserByAdUserID(String adUserID) throws NoSuchUserException {
        Optional<User> optionalUser = userRepository.findByAdUserID(adUserID);
        User user = optionalUser.orElseThrow(() -> new NoSuchUserException(adUserID));
        return user;
    }
}
