package edu.fra.uas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.fra.uas.model.User;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    // Get the service address from application.properties
    @Value("${userServiceUrl}")
    private String userServiceUrl;

    /**
     * Fetch all users from the external user service.
     *
     * @return List of users.
     */
    public List<User> getAllUsers() {
        // Send a GET request to the external user service
        User[] users = restTemplate.getForObject(userServiceUrl, User[].class);
        return Arrays.asList(users); // Convert the array to a List
    }

    /**
     * Fetch a single user by ID from the external user service.
     *
     * @param userId The ID of the user to fetch.
     * @return The user with the given ID.
     */
    public User getUserById(Long userId) {
        // Build the URL for the user by ID
        String url = String.format("%s/%d", userServiceUrl, userId);
        return restTemplate.getForObject(url, User.class);
    }

    /**
     * Add a new user by sending the request to the external user service.
     *
     * @param user The user to add.
     * @return The added user.
     */
    public User addUser(User user) {
        // Send a POST request to the external user service
        return restTemplate.postForObject(userServiceUrl, user, User.class);
    }

    /**
     * Delete a user by ID by sending the request to the external user service.
     *
     * @param userId The ID of the user to delete.
     */
    public void deleteUser(Long userId) {
        // Build the URL for the user by ID
        String url = String.format("%s/%d", userServiceUrl, userId);
        // Send a DELETE request to the external user service
        restTemplate.delete(url);
    }

    public void validateUserExists(Long userId) {
        try {
            // Call UserService to check if the user exists
            getUserById(userId);
        } catch (Exception e) {
            throw new IllegalArgumentException("User with ID " + userId + " does not exist.");
        }
    }
}
