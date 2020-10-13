package com.mspproject.backendmspapp.controller;

import com.mspproject.backendmspapp.helpers.SingleLesson;
import com.mspproject.backendmspapp.model.Lesson;
import com.mspproject.backendmspapp.helpers.Lessons;
import com.mspproject.backendmspapp.model.Payment;
import com.mspproject.backendmspapp.service.LessonService;
import com.mspproject.backendmspapp.service.SessionService;
import com.mspproject.backendmspapp.service.UserRegistrationAndLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(path="/lessons")
public class LessonController {

    @Autowired
    LessonService lessonService;

    @Autowired
    SessionService sessionService;

    @Autowired
    UserRegistrationAndLoginService loginService;

    String username;
    boolean sessionExpired;
    boolean isConnected;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping(path="/")
    public ResponseEntity<Lessons> getAllLessons() {

        username = loginService.getSessionState().getCurrentUser();
        sessionExpired = sessionService.isSessionExpired(username);
        isConnected = loginService.getSessionState().isConnected();

        if(isConnected && !sessionExpired) {
            sessionService.updateSessionTime(username);
            Iterable<Lesson> lessons = lessonService.getAllLessons();
            Lessons formattedJson = new Lessons(lessons);

            return new ResponseEntity<Lessons>(formattedJson, HttpStatus.OK);
        } else {
            loginService.logoutUser();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<SingleLesson> getLessonById(@PathVariable int id) {

        username = loginService.getSessionState().getCurrentUser();
        sessionExpired = sessionService.isSessionExpired(username);
        isConnected = loginService.getSessionState().isConnected();

        if(isConnected && !sessionExpired) {
            sessionService.updateSessionTime(username);

            Optional<Lesson> lesson = lessonService.getLessonById(id);
            SingleLesson formattedJson = new SingleLesson(lesson);
            return new ResponseEntity<SingleLesson>(formattedJson, HttpStatus.OK);
        } else {
            loginService.logoutUser();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path="/")
    public @ResponseBody ResponseEntity<Lesson> saveLesson(@RequestBody Lesson lesson) {

        username = loginService.getSessionState().getCurrentUser();
        sessionExpired = sessionService.isSessionExpired(username);
        isConnected = loginService.getSessionState().isConnected();

        if(isConnected && !sessionExpired) {
            sessionService.updateSessionTime(username);
            lessonService.saveLesson(lesson);
            return new ResponseEntity<>(lesson, HttpStatus.CREATED);
        } else {
            loginService.logoutUser();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(path="/{id}")
    public @ResponseBody ResponseEntity<Lesson> updateLesson(@RequestBody Lesson lesson, @PathVariable int id) {
        username = loginService.getSessionState().getCurrentUser();
        sessionExpired = sessionService.isSessionExpired(username);
        isConnected = loginService.getSessionState().isConnected();


        if(isConnected && !sessionExpired) {
            sessionService.updateSessionTime(username);
            Optional<Lesson> lessonToUpdate = lessonService.getLessonById(id);

            if (lessonToUpdate.isPresent()) {
                Lesson updatedLesson = lessonToUpdate.get();
                updatedLesson.setDate(lesson.getDate());
                updatedLesson.setLessonLength(lesson.getLessonLength());
                updatedLesson.setStudentId(lesson.getStudentId());
                lessonService.saveLesson(updatedLesson);
            }
            return new ResponseEntity<>(lesson, HttpStatus.OK);
        } else {
            loginService.logoutUser();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody ResponseEntity<?> deleteLesson(@PathVariable int id) {
        username = loginService.getSessionState().getCurrentUser();
        sessionExpired = sessionService.isSessionExpired(username);
        isConnected = loginService.getSessionState().isConnected();

        if(isConnected && !sessionExpired) {
            sessionService.updateSessionTime(username);
            lessonService.deleteLesson(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            loginService.logoutUser();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
