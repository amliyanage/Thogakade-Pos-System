package com.example.backend.dao.custom;


import com.example.backend.dao.SuperDAO;
import com.example.backend.entity.OrderDetails;

import java.sql.SQLException;

public interface OrderDetailDao extends SuperDAO {
    boolean save(OrderDetails entity) throws SQLException;
}