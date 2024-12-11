package com.scd.School_Management_System_spring.repositories;

import com.scd.School_Management_System_spring.entities.User;
import com.scd.School_Management_System_spring.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByRole(UserRole userRole);
    Optional<User> findFirstByEmail(String email);
}
