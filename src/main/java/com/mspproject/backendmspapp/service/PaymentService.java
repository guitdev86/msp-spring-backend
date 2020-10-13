package com.mspproject.backendmspapp.service;

import com.mspproject.backendmspapp.helpers.TotalPaymentsWrapper;
import com.mspproject.backendmspapp.model.Payment;
import com.mspproject.backendmspapp.helpers.TotalPayment;
import com.mspproject.backendmspapp.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public Iterable<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(int id) {
        return paymentRepository.findById(id);
    }

    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    public void deletePayment(int id) {
        paymentRepository.deleteById(id);
    }

    public TotalPaymentsWrapper getTotalPayments(int id) {
        BigDecimal totalPayments = paymentRepository.getTotalPayments(id);

        if(totalPayments == null) {
            totalPayments = new BigDecimal(0);
        }

        double amount = (totalPayments).doubleValue();

        return new TotalPaymentsWrapper(new TotalPayment(id, amount));
    };
}
