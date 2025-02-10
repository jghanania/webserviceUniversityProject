package edu.fra.uas.model;

/**
 * Represents a User entity with basic user details such as ID, name, and email.
 * This class is used to store and manage user-related information.
 */
public class User {

    private Long id;       // Unique identifier for the user
    private String name;   // Name of the user
    private String email;  // Email address of the user

    // Constructors
    public User() {
    }

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // toString
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
