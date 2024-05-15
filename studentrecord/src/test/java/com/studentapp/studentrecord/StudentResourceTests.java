package com.studentapp.studentrecord;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration// Replace YourController with your actual controller class
@AutoConfigureMockMvc
@SpringBootTest
public class StudentResourceTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testDeleteMapping() throws Exception {
        // Perform the DELETE request and verify the response status
        mockMvc.perform(delete("/students/10004", 1) // Replace "/your-endpoint/{id}" with your actual DELETE endpoint
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // Adjust the expected status as needed
    }

    @Test
    public void testCreateStudent() throws Exception {
        // Create a sample student object
        Student student = new Student();
        student.setName("John Doe");
        student.setId((long) 10006);
        student.setDateOfBirth("March 23");

        // Mock the save method of studentRepository to return the student
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // Perform a POST request to /students
        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(student)));
    }
}