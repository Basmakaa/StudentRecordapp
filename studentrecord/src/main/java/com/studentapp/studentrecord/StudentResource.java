package com.studentapp.studentrecord;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentResource {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> retrieveAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}") 
        public Student retrieveStudent(@PathVariable long id){
            Optional<Student> student = studentRepository.findById(id);

            if (student.isEmpty())
                throw new StudentNotFoundException(id);
            
            return student.get();

        }

    @DeleteMapping("/students/{id}") 
    public void deleteStudent(@PathVariable long id){
        studentRepository.deleteById(id);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedstudent = studentRepository.save(student);

        return new ResponseEntity<>(savedstudent, HttpStatus.CREATED);

    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student newstudent, @PathVariable long id){
        Optional<Student> o_student = studentRepository.findById(id);

            if (o_student.isEmpty())
                throw new StudentNotFoundException(id);
            
            newstudent.setId(id);
            studentRepository.save(newstudent);
            return ResponseEntity.noContent().build(); 
    }



    

    
}
