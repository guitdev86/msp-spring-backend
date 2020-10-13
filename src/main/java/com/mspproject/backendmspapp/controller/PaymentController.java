package com.mspproject.backendmspapp.controller;

import com.mspproject.backendmspapp.helpers.Payments;
import com.mspproject.backendmspapp.helpers.SinglePayment;
import com.mspproject.backendmspapp.model.Payment;
import com.mspproject.backendmspapp.service.PaymentService;
import com.mspproject.backendmspapp.service.SessionService;
import com.mspproject.backendmspapp.service.UserRegistrationAndLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(path="/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    SessionService sessionService;

    @Autowired
    UserRegistrationAndLoginService loginService;

    String username;
    boolean sessionExpired;
    boolean isConnected;

    @CrossOrigin(origins = "http://localhost:8081")

    @GetMapping(path="/")
    public @ResponseBody ResponseEntity<Payments> getAllPayments() {
        username = loginService.getSessionState().getCurrentUser();
        sessionExpired = sessionService.isSessionExpired(username);
        isConnected = loginService.getSessionState().isConnected();

        if(isConnected && !sessionExpired) {
            sessionService.updateSessionTime(username);

            Iterable<Payment> payments = paymentService.getAllPayments();
            Payments formattedJson = new Payments(payments);
            return new ResponseEntity<>(formattedJson, HttpStatus.OK);
        } else {
            loginService.logoutUser();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<SinglePayment> getPaymentById(@PathVariable int id) {
        username = loginService.getSessionState().getCurrentUser();
        sessionExpired = sessionService.isSessionExpired(username);
        isConnected = loginService.getSessionState().isConnected();

        if(isConnected && !sessionExpired) {
            sessionService.updateSessionTime(username);

            Optional<Payment> payment = paymentService.getPaymentById(id);
            SinglePayment formattedJson = new SinglePayment(payment);
            return new ResponseEntity<>(formattedJson, HttpStatus.OK);
        } else {
            loginService.logoutUser();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path="/")
    public @ResponseBody ResponseEntity<Payment> addNewPayment(@RequestBody Payment payment){
        username = loginService.getSessionState().getCurrentUser();
        sessionExpired = sessionService.isSessionExpired(username);
        isConnected = loginService.getSessionState().isConnected();

        if(isConnected && !sessionExpired) {
            sessionService.updateSessionTime(username);
            paymentService.savePayment(payment);
            return new ResponseEntity<>(payment, HttpStatus.CREATED);
        } else {
            loginService.logoutUser();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(path="/{id}")
    public @ResponseBody ResponseEntity<Payment> updatePayment(@RequestBody Payment payment, @PathVariable int id) {
        username = loginService.getSessionState().getCurrentUser();
        sessionExpired = sessionService.isSessionExpired(username);
        isConnected = loginService.getSessionState().isConnected();


        if(isConnected && !sessionExpired) {
            sessionService.updateSessionTime(username);
            Payment paymentToUpdate = paymentService.getPaymentById(id).get();

            paymentToUpdate.setDate(payment.getDate());
            paymentToUpdate.setPaymentAmount(payment.getPaymentAmount());
            paymentToUpdate.setStudentId(payment.getStudentId());

            paymentService.savePayment(paymentToUpdate);
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } else {
            loginService.logoutUser();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody ResponseEntity<?> deletePayment(@PathVariable int id) {
        username = loginService.getSessionState().getCurrentUser();
        sessionExpired = sessionService.isSessionExpired(username);
        isConnected = loginService.getSessionState().isConnected();

        if(isConnected && !sessionExpired) {
            sessionService.updateSessionTime(username);
            paymentService.deletePayment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            loginService.logoutUser();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}