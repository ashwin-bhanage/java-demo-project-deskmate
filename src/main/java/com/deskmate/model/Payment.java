package com.deskmate.model;

import com.deskmate.constants.PaymentMode;
import com.deskmate.constants.PaymentStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {

    private final long paymentId;
    private final long bookingId;
    private final PaymentMode mode;
    private final BigDecimal amount;
    private final PaymentStatus status;
    private final LocalDateTime paidAt;

    public Payment(long paymentId,
                   long bookingId,
                   PaymentMode mode,
                   BigDecimal amount,
                   PaymentStatus status,
                   LocalDateTime paidAt) {

        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.mode = mode;
        this.amount = amount;
        this.status = status;
        this.paidAt = paidAt;
    }

    public long getPaymentId() { return paymentId; }
    public long getBookingId() { return bookingId; }
    public PaymentMode getMode() { return mode; }
    public BigDecimal getAmount() { return amount; }
    public PaymentStatus getStatus() { return status; }
    public LocalDateTime getPaidAt() { return paidAt; }
}
