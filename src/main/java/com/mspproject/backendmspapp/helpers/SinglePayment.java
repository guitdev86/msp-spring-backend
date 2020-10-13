package com.mspproject.backendmspapp.helpers;

import com.mspproject.backendmspapp.model.Payment;

import java.util.Optional;

public class SinglePayment {
    private Optional<Payment> payment;

    public SinglePayment(Optional<Payment> payment) {
        this.payment = payment;
    }

    public Optional<Payment> getPayment() {
        return payment;
    }
}
