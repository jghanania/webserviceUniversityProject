package edu.fra.uas.userservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.fra.uas.userservice.model.User;
import edu.fra.uas.userservice.service.UserService;

// Controller für die Verwaltung der User
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * Konstruktor für den UserController.
     *
     * @param userService Service für Benutzeroperationen
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Nutzer erstellen im JSON-Format, return erstellt den User
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user.getName(), user.getEmail());
    }

    // User nach ID abrufen
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    // User Liste abrufen
    @GetMapping
    public List<User> listUsers() {
        return userService.listUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deletUser(id); // Implementiere die Löschlogik im Service
        return ResponseEntity.noContent().build(); // Rückgabe eines Status 204
    }
}
