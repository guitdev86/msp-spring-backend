package com.mspproject.backendmspapp.repository;

import com.mspproject.backendmspapp.model.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
    @Query(value="SELECT SUM(payment_amount) AS total_payments " +
            "FROM payments " +
            "WHERE student_id=?1", nativeQuery = true)
    BigDecimal getTotalPayments(int id);
}
