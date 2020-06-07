package com.example.VTracker.services;

import com.example.VTracker.entities.User;
import com.example.VTracker.errorAPI.NoSuchUserException;
import com.example.VTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void deleteByAdUserID(String adUserID) throws NoSuchUserException {
        Optional<User> optionalUser = userRepository.findByAdUserID(adUserID);
        User user = optionalUser.orElseThrow(() -> new NoSuchUserException(adUserID));
        userRepository.delete(user);
    }//todo NEXT: build Exception Handler for this method
}
