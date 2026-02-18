package com.deskmate;

import com.deskmate.utils.DbConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestMain {

    public static void main(String[] args) {

        System.out.println("Testing database connection...");

        try (Connection conn = DbConnectionFactory.getConnection()) {

            System.out.println("Connection established.");

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT desk_id, desk_code, name FROM desks")) {

                System.out.println("Query executed successfully.\n");

                while (rs.next()) {
                    long id = rs.getLong("desk_id");
                    String code = rs.getString("desk_code");
                    String name = rs.getString("name");

                    System.out.println(id + " | " + code + " | " + name);
                }

            }

        } catch (Exception e) {
            System.out.println("Database test failed.");
            e.printStackTrace();
        }
    }
}
