package com.example.backend.dao.custom;

import com.example.backend.bo.CustomerBo;
import com.example.backend.dao.CustomerDao;
import com.example.backend.db.ConnectionManager;
import com.example.backend.entity.Customer;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomDaoImpl implements CustomerDao {

    private Connection connection;
    PreparedStatement pstm;

    @Override
    public boolean saveCustomer(Customer customer) throws SQLException {
        connection = ConnectionManager.getInstance().getConnection();

        pstm = connection.prepareStatement("INSERT INTO customer VALUES (?,?,?,?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;

    }

    @Override
    public Customer searchCustomer(int id) throws SQLException {
        connection = ConnectionManager.getInstance().getConnection();

        pstm = connection.prepareStatement("SELECT * FROM customer WHERE cust_id=?");
        pstm.setInt(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }
        return null;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        connection = ConnectionManager.getInstance().getConnection();

        pstm = connection.prepareStatement("UPDATE customer SET cust_name = ? , cust_address = ? , cust_salary = ? WHERE cust_id=?");
        pstm.setString(1, customer.getName());
        pstm.setString(2, customer.getAddress());
        pstm.setDouble(3, customer.getSalary());
        pstm.setString(4, customer.getId());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean deleteCustomer(int id) throws SQLException {
        connection = ConnectionManager.getInstance().getConnection();

        pstm = connection.prepareStatement("DELETE FROM customer WHERE cust_id=?");
        pstm.setInt(1, id);

        return pstm.executeUpdate() > 0;
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        connection = ConnectionManager.getInstance().getConnection();

        pstm = connection.prepareStatement("SELECT * FROM customer");
        ResultSet resultSet = pstm.executeQuery();

        List<Customer> customerList = new ArrayList<>();

        while (resultSet.next()) {
            customerList.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }

        return customerList;
    }


}
