package com.mspproject.backendmspapp.helpers;

import java.util.Optional;

public class TotalPaymentsWrapper {
    private TotalPayment totalPayments;

    public TotalPaymentsWrapper(TotalPayment totalPayments) {
        this.totalPayments = totalPayments;
    }

    public TotalPayment getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(TotalPayment totalPayments) {
        this.totalPayments = totalPayments;
    }
}
