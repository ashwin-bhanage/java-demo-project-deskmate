package com.deskmate.dao;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ReportDao {

    BigDecimal getTotalRevenueByDate(LocalDate date);

    long getTotalBookingsByDate(LocalDate date);

    long getActiveDeskCount();
}
