package com.deskmate.dao.impl;

import com.deskmate.constants.BookingStatus;
import com.deskmate.dao.BookingDao;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;

public class JdbcBookingDao implements BookingDao {

    @Override
    public long insertBooking(Connection conn,
                              long deskId,
                              String phone,
                              LocalDateTime start,
                              LocalDateTime end,
                              BigDecimal total,
                              BookingStatus status) {

        String sql = """
                INSERT INTO bookings
                (desk_id, customer_phone, slot_start, slot_end, total_amount, status)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement ps =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, deskId);
            ps.setString(2, phone);
            ps.setTimestamp(3, Timestamp.valueOf(start));
            ps.setTimestamp(4, Timestamp.valueOf(end));
            ps.setBigDecimal(5, total);
            ps.setString(6, status.name());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                return rs.getLong(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Insert booking failed", e);
        }
    }

    @Override
    public void updateStatus(Connection conn,
                             long bookingId,
                             BookingStatus status) {

        String sql = "UPDATE bookings SET status = ? WHERE booking_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status.name());
            ps.setLong(2, bookingId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Update booking status failed", e);
        }
    }
}
