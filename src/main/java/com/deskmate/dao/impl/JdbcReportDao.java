package com.deskmate.dao.impl;

import com.deskmate.dao.ReportDao;
import com.deskmate.utils.DbConnectionFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

public class JdbcReportDao implements ReportDao {

    @Override
    public BigDecimal getTotalRevenueByDate(LocalDate date) {

        String sql = """
                SELECT COALESCE(SUM(p.amount), 0)
                FROM payments p
                JOIN bookings b ON p.booking_id = b.booking_id
                WHERE DATE(p.paid_at) = ?
                  AND p.status = 'SUCCESS'
                """;

        try (Connection conn = DbConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(date));

            ResultSet rs = ps.executeQuery();
            rs.next();

            return rs.getBigDecimal(1);

        } catch (SQLException e) {
            throw new RuntimeException("Revenue report failed", e);
        }
    }

    @Override
    public long getTotalBookingsByDate(LocalDate date) {

        String sql = """
                SELECT COUNT(*)
                FROM bookings
                WHERE DATE(slot_start) = ?
                """;

        try (Connection conn = DbConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(date));

            ResultSet rs = ps.executeQuery();
            rs.next();

            return rs.getLong(1);

        } catch (SQLException e) {
            throw new RuntimeException("Booking count report failed", e);
        }
    }

    @Override
    public long getActiveDeskCount() {

        String sql = """
                SELECT COUNT(*)
                FROM desks
                WHERE active = true
                """;

        try (Connection conn = DbConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            rs.next();

            return rs.getLong(1);

        } catch (SQLException e) {
            throw new RuntimeException("Active desk report failed", e);
        }
    }
}
