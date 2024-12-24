package com.scd.School_Management_System_spring.sevices.admin;

import com.scd.School_Management_System_spring.entities.User;
import com.scd.School_Management_System_spring.enums.UserRole;
import com.scd.School_Management_System_spring.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl {

    private final UserRepository userrepository;

    public AdminServiceImpl(UserRepository userrepository) {
        this.userrepository = userrepository;
    }

    @PostConstruct
    public void createAdminAccount() {
        // Use the instance of the repository
        Optional<User> adminAccount = userrepository.findByRole(UserRole.admin);
        if (adminAccount == null) {
            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setName("Admin");
            admin.setRole(UserRole.admin); // Updated enum constant usage
            // Correct syntax for encoding the password
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            admin.setPassword(passwordEncoder.encode("admin123")); // Set password correctly
            userrepository.save(admin); // Save the new admin
        }
    }
}
