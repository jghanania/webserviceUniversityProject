package edu.fra.uas.userservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.fra.uas.userservice.service.UserService;

@SpringBootApplication
public class UserserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserserviceApplication.class, args);
    }

    // CommandLineRunner für initiale Benutzerdaten
    @Bean
    public CommandLineRunner initData(UserService userService) {
        return args -> {
            // Beispielbenutzer hinzufügen
            userService.createUser("Jean", "Jean@example.com");
            userService.createUser("Emre", "Emre@example.com");
            userService.createUser("Daniel", "Daniel@example.com");
            userService.createUser("Lehmann", "Lehmann@example.com");

            System.out.println("Initial users created in the database!");
        };
    }
}
