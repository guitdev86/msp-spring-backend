package com.mspproject.backendmspapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name="sessions")
public class SessionTracking {

    @Id
    @GeneratedValue
    private int id;
    private String loggedUser;
    private Timestamp timeStamp;

    public SessionTracking() {
    }

    public SessionTracking(String currentUser, Timestamp timeStamp) {
        this.loggedUser = currentUser;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
