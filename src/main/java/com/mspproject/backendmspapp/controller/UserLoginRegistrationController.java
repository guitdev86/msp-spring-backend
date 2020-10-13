package com.mspproject.backendmspapp.controller;

import com.mspproject.backendmspapp.helpers.UserSessionState;
import com.mspproject.backendmspapp.model.SessionTracking;
import com.mspproject.backendmspapp.model.User;
import com.mspproject.backendmspapp.service.SessionService;
import com.mspproject.backendmspapp.service.UserRegistrationAndLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value="/user")
public class UserLoginRegistrationController {

    @Autowired
    UserRegistrationAndLoginService service;

    @Autowired
    SessionService sessionService;

    //----------- Login Section -------------//



    @GetMapping(value="/connected")
    public @ResponseBody UserSessionState isLoggedIn() {
        return service.getSessionState();
    }

    @PostMapping(value="/{user}&{password}")
    public @ResponseBody UserSessionState loginUser(@PathVariable String user, @PathVariable String password) {
        UserSessionState loginAttempt = service.isValidUser(user, password);

        if(loginAttempt.isConnected()) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Optional<SessionTracking> session = sessionService.getSessionByName(user);

            if(session.isPresent()) {
                sessionService.updateSessionTime(user);
            } else {
                sessionService.setNewSession(new SessionTracking(user, timestamp));
            }

        }

        return loginAttempt;
    }

    @PostMapping(value="/logout")
    public @ResponseBody UserSessionState logoutUser() {
        service.logoutUser();
        return service.getSessionState();
    }

    @PostMapping
    public void registerUser(@RequestBody User user) {
        service.saveNewUser(user);
    }
}