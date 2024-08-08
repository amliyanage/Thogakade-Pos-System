package com.example.backend.dao.custom;

import com.example.backend.dao.SuperDAO;
import com.example.backend.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends SuperDAO {
    boolean save(Order entity) throws SQLException;
    List<Order> getAll() throws SQLException;
}