package com.deskmate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.deskmate.config.AppConfig;
import com.deskmate.constants.Role;
import com.deskmate.exceptions.DatabaseOperationException;
import com.deskmate.exceptions.DoubleBookingException;
import com.deskmate.exceptions.EntityNotFoundException;
import com.deskmate.exceptions.ValidationException;
import com.deskmate.utils.InputUtil;

public class App {

    private static final Logger log = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        AppConfig config = new AppConfig();

        log.info("DeskMate D.B.P.S. started");

        String username = InputUtil.readString("Username: ");

        System.out.println("Role: 1) ADMIN  2) AGENT");
        int roleChoice = InputUtil.readInt("Choose: ");

        Role role = (roleChoice == 1) ? Role.ADMIN : Role.AGENT;

        while (true) {
            try {
                System.out.println("\n=== DeskMate D.B.P.S. (" + role + ") ===");

                if (role == Role.ADMIN) {
                    System.out.println("1. Desks (Admin)");
                }

                System.out.println("2. Bookings");
                System.out.println("3. Reports");	
                System.out.println("0. Exit");

                int choice = InputUtil.readInt("Choose: ");

                switch (choice) {

                    case 1 -> {
                        if (role != Role.ADMIN) {
                            System.out.println("Access denied.");
                        } else {
                            config.deskController().menu();
                        }
                    }

                    case 2 -> config.bookingController().menu();
//
//                    case 3 -> config.reportController().menu();
//
                    case 0 -> {
                        log.info("DeskMate stopped by user={}", username);
                        System.out.println("Bye!");
                        return;
                    }

                    default -> System.out.println("Invalid option.");
                }

            } catch (ValidationException |
                     EntityNotFoundException |
                     DoubleBookingException e) {

                log.warn("User error: {}", e.getMessage());
                System.out.println("ERROR: " + e.getMessage());

            } catch (DatabaseOperationException e) {

                log.error("Database error", e);
                System.out.println("ERROR: Database operation failed. Please retry.");

            } catch (Exception e) {

                log.error("Unexpected error", e);
                System.out.println("ERROR: Unexpected failure.");
            }
        }
    }
}
