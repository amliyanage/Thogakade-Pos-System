package com.example.backend.dao.custom.impl;

import com.example.backend.dao.custom.OrderDetailDao;
import com.example.backend.entity.OrderDetails;
import com.example.backend.utill.SQLUtil;

import java.sql.SQLException;

public class OrderDetailDaoImpl  implements OrderDetailDao {
    public static String SAVE_ORDER_ITEM_DETAIL = "INSERT INTO order_item_detail (order_id, item_id, qty) VALUES(?,?,?)";

    @Override
    public boolean save(OrderDetails entity) throws SQLException {
        return SQLUtil.execute(SAVE_ORDER_ITEM_DETAIL,
                entity.getOrderId(),
                entity.getItemId(),
                entity.getQty()
        );
    }
}