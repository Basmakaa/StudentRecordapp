package com.studentapp.studentrecord;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    private Long id; 
    private String name; 
    private LocalDate dateofbirth; 

    public Student() {
    }

    public Student(Long id, String name, LocalDate dateofbirth) {
        this.id = id;
        this.name = name;
        this.dateofbirth = dateofbirth;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateofbirth;
    }

    public void setDateOfBirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }
    
} 
