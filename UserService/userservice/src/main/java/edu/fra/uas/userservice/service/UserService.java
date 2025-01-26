package edu.fra.uas.userservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.fra.uas.userservice.model.User;
import edu.fra.uas.userservice.model.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

// Konstruktor 
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Benutzer erstellen
    public User createUser(String name, String email) {
        User user = new User(name, email);
        return userRepository.save(user);
    }

    // Benutzer nach ID abrufen
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Benutzer mit ID " + id + " nicht gefunden"));
    }

    // Liste aller Benutzer abrufen
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    // Löschen User Id
    public void deletUser(Long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }else{
            throw new IllegalArgumentException("User mit der ID" +id + "not found.");
        }
    }

}

