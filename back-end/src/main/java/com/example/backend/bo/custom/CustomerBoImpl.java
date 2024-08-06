package com.example.backend.bo.custom;

import com.example.backend.dao.DAOFactory;
import com.example.backend.dao.custom.CustomerDao;
import com.example.backend.dto.CustomerDto;
import com.example.backend.entity.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {

    CustomerDao customerDao = (CustomerDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public boolean addCustomer(CustomerDto customerDto) throws IOException, SQLException {
        return customerDao.save(
                new Customer(
                        customerDto.getId(),
                        customerDto.getName(),
                        customerDto.getAddress(),
                        customerDto.getSalary()
                )
        );

    }

    @Override
    public CustomerDto searchCustomer(String id) throws IOException, SQLException {
        Customer customer = customerDao.getData(id);
        if (customer != null) {
            System.out.println(customer+"=============================== bo");
            return new CustomerDto(customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary());
        } else {
            return null;
        }
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException {
        return customerDao.update(
                new Customer(
                        customerDto.getId(),
                        customerDto.getName(),
                        customerDto.getAddress(),
                        customerDto.getSalary()
                )
        );
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return customerDao.delete(id);
    }

    @Override
    public List<CustomerDto> getAllCustomers() throws SQLException {
        List<Customer> customerList = customerDao.getAll();

        if (customerList != null) {
            return customerList.stream().map(customer -> new CustomerDto(customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary())).toList();
        } else {
            return null;
        }

    }
}
