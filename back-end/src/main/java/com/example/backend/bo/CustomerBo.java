package com.example.backend.bo;

import com.example.backend.dto.CustomerDto;
import jakarta.json.bind.Jsonb;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.sql.SQLException;

public interface CustomerBo {
    boolean addCustomer(CustomerDto customerDto) throws IOException, SQLException;
}
