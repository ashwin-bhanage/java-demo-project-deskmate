package com.deskmate.dao;

import com.deskmate.constants.BookingStatus;
import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDateTime;

public interface BookingDao {

    long insertBooking(Connection conn,
                       long deskId,
                       String phone,
                       LocalDateTime start,
                       LocalDateTime end,
                       BigDecimal total,
                       BookingStatus status);

    void updateStatus(Connection conn,
                      long bookingId,
                      BookingStatus status);
}
