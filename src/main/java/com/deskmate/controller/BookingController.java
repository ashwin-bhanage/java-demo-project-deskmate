package com.deskmate.controller;

import com.deskmate.constants.PaymentMode;
import com.deskmate.constants.PaymentStatus;
import com.deskmate.model.Payment;
import com.deskmate.service.BookingService;
import com.deskmate.utils.DateUtil;
import com.deskmate.utils.InputUtil;
import com.deskmate.utils.MoneyUtil;
import com.deskmate.utils.ValidationUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingController {

	private final BookingService bookingService;

	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	public void menu() {

		System.out.println("\n--- Create Booking ---");

		long deskId = Long.parseLong(InputUtil.readString("Desk ID: "));

		String phone = ValidationUtil.normalizePhone(InputUtil.readString("Customer Phone (10 digits): "));

		LocalDateTime start = DateUtil.parseDateTime(InputUtil.readString("Slot Start (yyyy-MM-dd HH:mm): "));

		LocalDateTime end = DateUtil.parseDateTime(InputUtil.readString("Slot End (yyyy-MM-dd HH:mm): "));

		BigDecimal amount = MoneyUtil.parse(InputUtil.readString("Total Amount: "));

		System.out.println("Payment Mode: 1) CASH 2) CARD 3) UPI");
		int modeChoice = Integer.parseInt(InputUtil.readString("Choose: "));

		PaymentMode mode = switch (modeChoice) {
		case 1 -> PaymentMode.CASH;
		case 2 -> PaymentMode.CARD;
		case 3 -> PaymentMode.UPI;
		default -> throw new RuntimeException("Invalid payment mode");
		};

		System.out.println("Payment Status: 1) SUCCESS 2) FAILED");
		int statusChoice = Integer.parseInt(InputUtil.readString("Choose: "));

		PaymentStatus status = (statusChoice == 1) ? PaymentStatus.SUCCESS : PaymentStatus.FAILED;

		Payment payment = new Payment(0, 0, mode, amount, status, null);

		bookingService.createBooking(deskId, phone, start, end, amount, payment);

		System.out.println("Booking processed.");
	}
}
