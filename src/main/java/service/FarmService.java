
package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;

@Service
public interface StudentService {
    
    Student saveStudent(Student student);// Creating object for saving student details and class
    
 
    
}