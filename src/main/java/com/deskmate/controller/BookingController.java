package com.deskmate.controller;

import com.deskmate.service.BookingService;

public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void menu() {
        System.out.println("Booking feature ready.");
    }
}
