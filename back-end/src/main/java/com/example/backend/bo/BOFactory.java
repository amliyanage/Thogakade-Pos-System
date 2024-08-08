package com.example.backend.bo;

import com.example.backend.bo.custom.CustomerBoImpl;
import com.example.backend.bo.custom.ItemBoImpl;
import com.example.backend.bo.custom.OrderBoImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory() {
        return (boFactory == null) ?
                boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER, ITEM, Order
    }

    public SuperBO getBO(BOTypes boTypes) {
        return switch (boTypes) {
            case CUSTOMER -> new CustomerBoImpl();
            case ITEM -> new ItemBoImpl();
            case Order -> new OrderBoImpl();
        };
    }
}
