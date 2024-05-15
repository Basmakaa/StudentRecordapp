package com.studentapp.studentrecord;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(long id) {
        //TODO Auto-generated constructor stub
        super("Student id " + id + " Not found ");
    }

}
