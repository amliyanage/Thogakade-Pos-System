package com.example.backend.bo.custom;

import com.example.backend.bo.CustomerBo;
import com.example.backend.dao.CustomerDao;
import com.example.backend.dao.custom.CustomDaoImpl;
import com.example.backend.dto.CustomerDto;
import com.example.backend.entity.Customer;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerBoImpl implements CustomerBo {

    CustomerDao customerDao = new CustomDaoImpl();

    @Override
    public boolean addCustomer(CustomerDto customerDto) throws IOException, SQLException {
        return customerDao.saveCustomer(
                new Customer(
                        customerDto.getId(),
                        customerDto.getName(),
                        customerDto.getAddress(),
                        customerDto.getSalary()
                )
        );

    }

    @Override
    public CustomerDto searchCustomer(int id) throws IOException, SQLException {
        Customer customer = customerDao.searchCustomer(id);
        if (customer != null) {
            System.out.println(customer+"=============================== bo");
            return new CustomerDto(customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary());
        } else {
            return null;
        }
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException {
        return customerDao.updateCustomer(
                new Customer(
                        customerDto.getId(),
                        customerDto.getName(),
                        customerDto.getAddress(),
                        customerDto.getSalary()
                )
        );
    }

    @Override
    public boolean deleteCustomer(int id) throws SQLException {
        return customerDao.deleteCustomer(id);
    }
}
