package com.example.backend.dao.custom.impl;

import com.example.backend.dao.custom.OrderDao;
import com.example.backend.entity.Order;
import com.example.backend.utill.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    public static String SAVE_ORDER = "INSERT INTO `order` (id, date, customer_id, total, discount, sub_total, cash, balance) VALUES(?,?,?,?,?,?,?,?)";
    public static String GET_ALL_ORDERS = "SELECT * FROM `order`";

    @Override
    public boolean save(Order entity) throws SQLException {
        System.out.println(entity);
        return SQLUtil.execute(SAVE_ORDER,
                entity.getId(),
                entity.getDate(),
                entity.getCustomerId(),
                entity.getTotal(),
                entity.getDiscount(),
                entity.getSubTotal(),
                entity.getCash(),
                entity.getBalance()
        );
    }

    @Override
    public List<Order> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute(GET_ALL_ORDERS);
        List<Order> orderList = new ArrayList<>();
        while (rst.next()) {
            orderList.add(new Order(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getString(5),
                    rst.getDouble(6),
                    rst.getDouble(7),
                    rst.getDouble(8)
            ));
        }
        return orderList;
    }
}