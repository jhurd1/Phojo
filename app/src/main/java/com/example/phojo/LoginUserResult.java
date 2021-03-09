package com.example.phojo;

public class LoginUserResult extends OperationResult {

    private User user;

    public LoginUserResult(boolean error, String message) {
        super(error, message);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
