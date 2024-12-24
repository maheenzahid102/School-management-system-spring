package com.example.School_Management_system.repositories;

import com.example.School_Management_system.entities.User;
import com.example.School_Management_system.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    //Optional<User> findByEmail(String email);
    Optional<User> findByRole(UserRole userRole);
    Optional<User> findFirstByEmail(String email);
}

