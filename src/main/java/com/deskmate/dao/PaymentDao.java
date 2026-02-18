package com.deskmate.dao;

import com.deskmate.model.Payment;
import java.sql.Connection;

public interface PaymentDao {

    long insertPayment(Connection conn, Payment payment);
}
