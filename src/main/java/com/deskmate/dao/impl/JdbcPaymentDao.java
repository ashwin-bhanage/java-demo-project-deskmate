package com.deskmate.dao.impl;

import com.deskmate.dao.PaymentDao;
import com.deskmate.model.Payment;

import java.sql.*;

public class JdbcPaymentDao implements PaymentDao {

    @Override
    public long insertPayment(Connection conn, Payment payment) {

        String sql = """
                INSERT INTO payments
                (booking_id, mode, amount, status, paid_at)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (PreparedStatement ps =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, payment.getBookingId());
            ps.setString(2, payment.getMode().name());
            ps.setBigDecimal(3, payment.getAmount());
            ps.setString(4, payment.getStatus().name());
            ps.setTimestamp(5, Timestamp.valueOf(payment.getPaidAt()));

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                return rs.getLong(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Insert payment failed", e);
        }
    }
}
