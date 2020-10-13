package com.mspproject.backendmspapp.helpers;

public class UserSessionState {
    private boolean isConnected;
    private String currentUser;

    public UserSessionState() {
        this.isConnected = false;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
