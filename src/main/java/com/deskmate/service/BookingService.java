package com.deskmate.service;

import com.deskmate.constants.BookingStatus;
import com.deskmate.constants.PaymentStatus;
import com.deskmate.dao.BookingDao;
import com.deskmate.dao.PaymentDao;
import com.deskmate.model.Payment;
import com.deskmate.utils.DbConnectionFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDateTime;

public class BookingService {

    private final BookingDao bookingDao;
    private final PaymentDao paymentDao;

    public BookingService(BookingDao bookingDao,
                          PaymentDao paymentDao) {
        this.bookingDao = bookingDao;
        this.paymentDao = paymentDao;
    }

    public void createBooking(long deskId,
                              String phone,
                              LocalDateTime start,
                              LocalDateTime end,
                              BigDecimal amount,
                              Payment payment) {

        try (Connection conn = DbConnectionFactory.getConnection()) {

            conn.setAutoCommit(false);

            long bookingId = bookingDao.insertBooking(
                    conn,
                    deskId,
                    phone,
                    start,
                    end,
                    amount,
                    BookingStatus.CREATED
            );

            Payment paymentWithBooking =
                    new Payment(
                            0,
                            bookingId,
                            payment.getMode(),
                            payment.getAmount(),
                            payment.getStatus(),
                            LocalDateTime.now()
                    );

            paymentDao.insertPayment(conn, paymentWithBooking);

            if (payment.getStatus() == PaymentStatus.SUCCESS) {
                bookingDao.updateStatus(
                        conn,
                        bookingId,
                        BookingStatus.PAID
                );
            }

            conn.commit();

        } catch (Exception e) {
            throw new RuntimeException("Booking transaction failed", e);
        }
    }
}
