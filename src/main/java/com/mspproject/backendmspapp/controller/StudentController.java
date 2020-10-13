package com.mspproject.backendmspapp.controller;

import com.mspproject.backendmspapp.helpers.*;
import com.mspproject.backendmspapp.service.*;
import com.mspproject.backendmspapp.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(path="/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    LessonService lessonService;

    @Autowired
    SessionService sessionService;

    @Autowired
    UserRegistrationAndLoginService loginService;

    String username;
    boolean sessionExpired;
    boolean isConnected;



    @GetMapping(path="/")
    public @ResponseBody ResponseEntity<Students> getStudents() {

        username = loginService.getSessionState().getCurrentUser();
        sessionExpired = sessionService.isSessionExpired(username);
        isConnected = loginService.getSessionState().isConnected();

        if(isConnected && !sessionExpired) {
            sessionService.updateSessionTime(username);

            Iterable<Student> students = studentService.getAllStudents();
            Students studentsToReturn = new Students(students);
            return new ResponseEntity<>(studentsToReturn, HttpStatus.OK);
        } else {
            loginService.logoutUser();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<Student> getStudent(@PathVariable int id) {

        username = loginService.getSessionState().getCurrentUser();
        sessionExpired = sessionService.isSessionExpired(username);
        isConnected = loginService.getSessionState().isConnected();

        if(isConnected && !sessionExpired) {
            sessionService.updateSessionTime(username);

            Optional<Student> student = studentService.getStudentById(id);
            if (student.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            loginService.logoutUser();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping(path="/name/{name}&{surname}")
    public @ResponseBody ResponseEntity<StudentWrapper> getStudentByNameAndSurname(@PathVariable String name, @PathVariable String surname) {
        username = loginService.getSessionState().getCurrentUser();
        sessionExpired = sessionService.isSessionExpired(username);
        isConnected = loginService.getSessionState().isConnected();

        if(isConnected && !sessionExpired) {
            Optional<Student> student = studentService.getStudentByNameAndSurname(name, surname);
            if(student.isPresent()) {
                return new ResponseEntity<>(new StudentWrapper(student), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            loginService.logoutUser();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    @PostMapping(path="/")
    public @ResponseBody ResponseEntity<Student> addNewStudent(@RequestBody Student student) {
        username = loginService.getSessionState().getCurrentUser();
        sessionExpired = sessionService.isSessionExpired(username);
        isConnected = loginService.getSessionState().isConnected();

        if(isConnected && !sessionExpired) {
            sessionService.updateSessionTime(username);
            studentService.saveStudent(student);
            return new ResponseEntity<>(student, HttpStatus.CREATED);
        } else {
            loginService.logoutUser();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    @PutMapping(path="/{id}")
    public @ResponseBody ResponseEntity<Student> updateStudent(@RequestBody Student updatedStudent, @PathVariable int id) {
        username = loginService.getSessionState().getCurrentUser();
        sessionExpired = sessionService.isSessionExpired(username);
        isConnected = loginService.getSessionState().isConnected();

        if(isConnected && !sessionExpired) {
            sessionService.updateSessionTime(username);

            Student studentToUpdate = studentService.getStudentObjectById(id);

            studentToUpdate.setName(updatedStudent.getName());
            studentToUpdate.setSurname(updatedStudent.getSurname());
            studentToUpdate.setActive(updatedStudent.isActive());
            studentToUpdate.setPaymentRate(updatedStudent.getPayment_rate());

            studentService.saveStudent(studentToUpdate);
            return new ResponseEntity<>(studentToUpdate, HttpStatus.OK);
        } else {
            loginService.logoutUser();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    @DeleteMapping(path="/{id}")
    public @ResponseBody ResponseEntity<?> deleteStudent(@PathVariable int id) {
        username = loginService.getSessionState().getCurrentUser();
        sessionExpired = sessionService.isSessionExpired(username);
        isConnected = loginService.getSessionState().isConnected();

        if(isConnected && !sessionExpired) {
            sessionService.updateSessionTime(username);

            studentService.deleteStudent(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            loginService.logoutUser();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @CrossOrigin
    @GetMapping(path="/{id}/total-payments")
    public @ResponseBody ResponseEntity<TotalPaymentsWrapper> getTotalPayment(@PathVariable int id) {
        username = loginService.getSessionState().getCurrentUser();
        sessionExpired = sessionService.isSessionExpired(username);
        isConnected = loginService.getSessionState().isConnected();

        if(isConnected && !sessionExpired) {
            sessionService.updateSessionTime(username);
            TotalPaymentsWrapper response = paymentService.getTotalPayments(id);
            return new ResponseEntity<TotalPaymentsWrapper>(response, HttpStatus.OK);
        } else {
            loginService.logoutUser();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @CrossOrigin
    @GetMapping(path="/{id}/total-lessons")
    public @ResponseBody ResponseEntity<TotalLessonsWrapper> getTotalLessons(@PathVariable int id) {
        username = loginService.getSessionState().getCurrentUser();
        sessionExpired = sessionService.isSessionExpired(username);
        isConnected = loginService.getSessionState().isConnected();

        if(loginService.getSessionState().isConnected()) {
            sessionService.updateSessionTime(username);
            TotalLessonsWrapper response = lessonService.getTotalLessons(id);
            return new ResponseEntity<TotalLessonsWrapper>(response, HttpStatus.OK);

        } else {
            loginService.logoutUser();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    }
}