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

public class CustomDaoImpl implements CustomerDao {

    private Connection connection;

    @Override
    public boolean saveCustomer(Customer customer) throws SQLException {
        connection = ConnectionManager.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("INSERT INTO customer VALUES (?,?,?,?)");
        pstm.setInt(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;

    }

    @Override
    public Customer searchCustomer(int id) throws SQLException {
        connection = ConnectionManager.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM customer WHERE cust_id=?");
        pstm.setInt(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return new Customer(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }
        return null;
    }

}
