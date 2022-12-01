package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

// API Layer
// this will have all the resources for the API
@RestController // makes this class to serve endpoints
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    // this annotation is for dependency injection
    // here we are saying that studentService from above should be
    // autowired for the studentService parameter from below
    // so the studentService will be instantiated and injected in the constructor
    // now we have to say that the StudentService class is a class that has to be instantiated (it has to be a spring bean)
    // => we annotate it with Service (Component does the same thing but Service is more specific)
    @Autowired
    public StudentController(StudentService studentService) {
//        this.studentService = new StudentService();// we don't do this because we want to use dependency injection
        this.studentService = studentService;
    }

    @GetMapping // this is an endpoint
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    // @RequestBody -> we take the request body and mapping in the student parameter
    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }
}