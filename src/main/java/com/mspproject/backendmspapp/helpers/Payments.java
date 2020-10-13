package com.mspproject.backendmspapp.helpers;

import com.mspproject.backendmspapp.model.Payment;

public class Payments {
    private Iterable<Payment> payments;

    public Payments(Iterable<Payment> payments) {
        this.payments = payments;
    }

    public Iterable<Payment> getPayments() {
        return payments;
    }
}
