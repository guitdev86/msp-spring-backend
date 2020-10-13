package com.mspproject.backendmspapp.service;

import com.mspproject.backendmspapp.helpers.UserSessionState;
import com.mspproject.backendmspapp.model.User;
import com.mspproject.backendmspapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserRegistrationAndLoginService {

    @Autowired
    UserRepository userRepository;

    UserSessionState sessionState = new UserSessionState();

    public UserSessionState isValidUser(String username, String password) {
        Iterable<User> users = userRepository.findAll();
        users.forEach( user -> {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                sessionState.setConnected(true);
                sessionState.setCurrentUser(username);
            }
        });

        return sessionState;
    }

    public void saveNewUser(User user) {
        userRepository.save(user);
    }

    public UserSessionState getSessionState() {
        return sessionState;
    }

    public void logoutUser() {
        sessionState.setConnected(false);
    }
}
