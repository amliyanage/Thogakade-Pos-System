package com.example.backend.dao;

import com.example.backend.entity.Customer;

import java.sql.SQLException;

public interface CustomerDao {
    boolean saveCustomer(Customer customer) throws SQLException;
    Customer searchCustomer(int id) throws SQLException;

    boolean updateCustomer(Customer customer) throws SQLException;
}
