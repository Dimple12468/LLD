package model;

public class User {
    public String username;
    public boolean isLoggedIn;

    public User(String username) {
        this.username = username;
        this.isLoggedIn = false;
    }

    public void login() {
        this.isLoggedIn = true;
        System.out.println("Welcome " + username);
    }

    public void logout() {
        this.isLoggedIn = false;
        System.out.println("Logged out successfully.");
    }
}
