package com.deskmate.service;

import com.deskmate.dao.ReportDao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReportService {

    private final ReportDao reportDao;

    public ReportService(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    public BigDecimal dailyRevenue(LocalDate date) {
        return reportDao.getTotalRevenueByDate(date);
    }

    public long dailyBookings(LocalDate date) {
        return reportDao.getTotalBookingsByDate(date);
    }

    public long activeDeskCount() {
        return reportDao.getActiveDeskCount();
    }
}
