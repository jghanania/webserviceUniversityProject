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

    /**
     * CommandLineRunner runs after the application starts.
     * It initializes the database with some default users.
     * 
     * @param userService The service responsible for user operations.
     * @return A lambda function that creates initial users in the database.
    */
    @Bean
    public CommandLineRunner initData(UserService userService) {
        return args -> {
            // Creates initial users in the database
            userService.createUser("Jean", "Jean@example.com");
            userService.createUser("Emre", "Emre@example.com");
            userService.createUser("Daniel", "Daniel@example.com");
            userService.createUser("Lehmann", "Lehmann@example.com");

            // Logging information to confirm initialization
            System.out.println("Initial users created in the database!");
        };
    }
}
