package com.mspproject.backendmspapp.helpers;

public class TotalPayment {
    private int student_id;
    double total_payments;

    public TotalPayment(int student_id, double total_payments) {
        this.student_id = student_id;
        this.total_payments = total_payments;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public double getTotal_payments() {
        return total_payments;
    }

    public void setTotal_payments(double total_payments) {
        this.total_payments = total_payments;
    }
}
