package com.example.backend.bo.custom;

import com.example.backend.bo.SuperBO;
import com.example.backend.dto.CustomerDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBo extends SuperBO {
    boolean addCustomer(CustomerDto customerDto) throws IOException, SQLException;
    CustomerDto searchCustomer(String id) throws IOException, SQLException;

    boolean updateCustomer(CustomerDto customerDto) throws SQLException;

    boolean deleteCustomer(String id) throws SQLException;

    List<CustomerDto> getAllCustomers() throws SQLException;
}
