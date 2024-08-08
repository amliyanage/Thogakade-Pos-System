package com.example.backend.bo.custom;

import com.example.backend.bo.SuperBO;
import com.example.backend.dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderBo extends SuperBO {
    boolean saveOrder(OrderDto dto) throws SQLException;
    List<OrderDto> getAllOrders() throws SQLException;
}