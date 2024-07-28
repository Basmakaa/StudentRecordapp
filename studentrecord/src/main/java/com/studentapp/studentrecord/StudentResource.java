package com.studentapp.studentrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentResource {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> retrieveAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> retrieveStudent(@PathVariable long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty()) {
            throw new StudentNotFoundException(id);
        }

        return ResponseEntity.ok(student.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty()) {
            throw new StudentNotFoundException(id);
        }

        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        if (student.getId() != null) {
            throw new RuntimeException("New student cannot have an ID");
        }
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student newStudent, @PathVariable long id) {
        Optional<Student> o_student = studentRepository.findById(id);

        if (o_student.isEmpty()) {
            throw new StudentNotFoundException(id);
        }

        newStudent.setId(id);
        Student updatedStudent = studentRepository.save(newStudent);
        return ResponseEntity.ok(updatedStudent);
    }
}
