package edu.fra.uas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

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
        String url = String.format("%s/%d", userServiceUrl, userId);
        try {
            return restTemplate.getForObject(url, User.class);
        } catch (HttpClientErrorException.NotFound e) {
            // If user is not found, return null so the controller can handle it
            return null;
        } catch (HttpServerErrorException e) {
            // If the user service returns a 500 error, log and return null
            System.err.println("User service returned 500 error for user ID: " + userId);
            return null;
        } catch (HttpClientErrorException e) {
            // Handle other 4xx errors
            throw new RuntimeException("Client error while fetching user: " + e.getMessage(), e);
        }
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
    public boolean deleteUser(Long userId) {
        String url = String.format("%s/%d", userServiceUrl, userId);
        try {
            restTemplate.delete(url);
            return true; // User deleted successfully
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return false; // User not found
            }
            throw e; // Re-throw other client errors
        } catch (HttpServerErrorException e) {
            // Log the error and return false for any 5xx error
            System.err.println("Error deleting user: " + e.getMessage());
            return false; // Assume user not found if the service fails
        }
    }

    public boolean validateUserExists(Long userId) {
        try {
            // Call UserService to check if the user exists
            User user = getUserById(userId);
            if (user == null) {
                return false;
            }
        } catch (ResponseStatusException e) {
            return false;
        }
        return true;
    }
}
