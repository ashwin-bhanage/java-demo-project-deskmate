package com.deskmate.controller;

import com.deskmate.service.ReportService;
import com.deskmate.utils.InputUtil;

import java.time.LocalDate;

public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    public void menu() {

        while (true) {

            System.out.println("\n--- Reports ---");
            System.out.println("1. Daily Revenue");
            System.out.println("2. Daily Booking Count");
            System.out.println("3. Active Desk Count");
            System.out.println("0. Back");

            int c = InputUtil.readInt("Choose: ");

            switch (c) {

                case 1 -> {
                    LocalDate date = LocalDate.parse(
                            InputUtil.readString("Date (yyyy-MM-dd): "));
                    System.out.println("Revenue: ₹" +
                            reportService.dailyRevenue(date));
                }

                case 2 -> {
                    LocalDate date = LocalDate.parse(
                            InputUtil.readString("Date (yyyy-MM-dd): "));
                    System.out.println("Bookings: " +
                            reportService.dailyBookings(date));
                }

                case 3 -> {
                    System.out.println("Active Desks: " +
                            reportService.activeDeskCount());
                }

                case 0 -> { return; }

                default -> System.out.println("Invalid option.");
            }
        }
    }
}
