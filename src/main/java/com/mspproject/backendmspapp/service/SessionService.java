package com.mspproject.backendmspapp.service;

import com.mspproject.backendmspapp.model.SessionTracking;
import com.mspproject.backendmspapp.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    SessionRepository sessionRepository;

    public void setNewSession(SessionTracking session) {
        sessionRepository.save(session);
    }

    public Iterable<SessionTracking> getAllSessions() {
        return sessionRepository.findAll();
    }

    public void deleteSession(int id) {
        sessionRepository.deleteById(id);
    }

    public Optional<SessionTracking> getSessionByName(String username) {
        return sessionRepository.findByName(username);
    }

    public void saveSession(SessionTracking session) {
        sessionRepository.save(session);
    }


    public boolean isSessionExpired(String name) {
        Optional<SessionTracking> session = sessionRepository.findByName(name);
        if(session.isPresent()) {
            long sessionStart = session.get().getTimeStamp().getTime();
            long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
            long timeDifference = currentTime - sessionStart;
            System.out.println(currentTime);
            System.out.println(timeDifference);
            System.out.println(timeDifference > 1000000);
            return timeDifference > 1000000;
        }
        return true;
    }

    public void updateSessionTime(String username) {
        Optional<SessionTracking> session = sessionRepository.findByName(username);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        session.ifPresent(sessionTracking -> {
            sessionTracking.setTimeStamp(timestamp);
            sessionRepository.save(sessionTracking);
        });

    }

}
