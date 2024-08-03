package com.example.backend.bo;

import com.example.backend.dto.CustomerDto;
import jakarta.json.bind.Jsonb;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBo {
    boolean addCustomer(CustomerDto customerDto) throws IOException, SQLException;
    CustomerDto searchCustomer(int id) throws IOException, SQLException;

    boolean updateCustomer(CustomerDto customerDto) throws SQLException;

    boolean deleteCustomer(int id) throws SQLException;

    List<CustomerDto> getAllCustomers() throws SQLException;
}
