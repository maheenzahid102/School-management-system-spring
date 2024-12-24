package com.example.School_Management_system.services.admin;
import com.example.School_Management_system.dto.StudentDto;
import com.example.School_Management_system.entities.User;
import com.example.School_Management_system.enums.UserRole;
import com.example.School_Management_system.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AdminServiceImpl implements AdminService {
    private final UserRepository userrepository;

    public AdminServiceImpl(UserRepository userrepository) {
        this.userrepository = userrepository;
    }

    @PostConstruct
    public void createAdminAccount() {
        Optional<User> adminAccount = userrepository.findByRole(UserRole.admin);
        if (adminAccount.isEmpty()) {
            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setName("Admin");
            admin.setRole(UserRole.admin);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            admin.setPassword(passwordEncoder.encode("Admin123"));
            userrepository.save(admin);
            System.out.println("Admin account created!");
        } else {
            System.out.println("Admin account already exists.");
        }
    }

    @Override
    public StudentDto postStudent(StudentDto studentDto)
    {
        Optional<User> optionalUser= userrepository.findFirstByEmail(studentDto.getEmail());
        if(optionalUser.isEmpty())
        {
            User user=new User();
            BeanUtils.copyProperties(studentDto,user);
            user.setPassword(new BCryptPasswordEncoder().encode(studentDto.getPassword()));
            user.setRole(UserRole.student);
            User createdUser=userrepository.save(user);
            StudentDto createdStudentDto=new StudentDto();
            createdStudentDto.setId(createdUser.getId());
            createdStudentDto.setEmail(createdUser.getEmail());
            return  createdStudentDto;

        }
            return null;

    }

}
