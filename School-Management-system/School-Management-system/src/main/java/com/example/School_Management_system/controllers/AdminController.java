package com.example.School_Management_system.controllers;

import com.example.School_Management_system.dto.StudentDto;
import com.example.School_Management_system.services.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {


    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
@PostMapping("/student")
    public ResponseEntity<?> addStudent(@RequestBody StudentDto studentDto)
    {
       StudentDto createdStudentDto= adminService.postStudent(studentDto);
       if(createdStudentDto ==null)
       {
           return new ResponseEntity<>("Something went Wrong", HttpStatus.BAD_REQUEST);
       }
       return ResponseEntity.status(HttpStatus.CREATED).body(createdStudentDto);
    }

}
