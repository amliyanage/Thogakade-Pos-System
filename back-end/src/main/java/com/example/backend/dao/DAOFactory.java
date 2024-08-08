package com.example.backend.dao;

import com.example.backend.dao.custom.impl.CustomDaoImpl;
import com.example.backend.dao.custom.impl.ItemDaoImpl;
import com.example.backend.dao.custom.impl.OrderDaoImpl;
import com.example.backend.dao.custom.impl.OrderDetailDaoImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : daoFactory;
    }

    public enum DAOType {
        CUSTOMER, ITEM, Order , OrderDetail
    }

    public SuperDAO getDAO(DAOType daoType) {
        return switch (daoType) {
            case CUSTOMER -> new CustomDaoImpl();
            case ITEM -> new ItemDaoImpl();
            case Order -> new OrderDaoImpl();
            case OrderDetail -> new OrderDetailDaoImpl();
        };
    }
}
