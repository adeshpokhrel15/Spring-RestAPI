package com.restcontroller.example.restcontroller.rest;

import com.restcontroller.example.restcontroller.entity.Student;
import com.restcontroller.example.restcontroller.entity.StudentErrorResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudent;
    @PostConstruct
    public void loadData(){
     theStudent = new ArrayList<>();
        theStudent.add(new Student("Adesh", "Pokhrel"));
        theStudent.add(new Student("Pokhrel", "Adesh"));

    }

    // define endpoints for "/students"
    @GetMapping("/students")
    public List<Student> getStudents() {

        return theStudent;
    }

    // define endpoint or "/student/{studentId}" -return student at index

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        if(studentId >=theStudent.size() || studentId < 0){
            throw new StudentNotFoundException("Student Not Found - " + studentId);
        }

        return theStudent.get(studentId);
    }

    // add an exception handler using @ExceptionHandler
     @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

         StudentErrorResponse error = new StudentErrorResponse();
         error.setStatus(HttpStatus.NOT_FOUND.value());
         error.setMessage(exc.getMessage());
         error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
     }

     @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){

         StudentErrorResponse error = new StudentErrorResponse();
         error.setStatus(HttpStatus.BAD_REQUEST.value());
         error.setMessage(exc.getMessage());
         error.setTimeStamp(System.currentTimeMillis());
         return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
     }

}

