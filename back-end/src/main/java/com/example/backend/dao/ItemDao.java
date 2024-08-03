package com.example.backend.dao;

import com.example.backend.dto.ItemDto;
import com.example.backend.entity.Item;

import java.sql.SQLException;

public interface ItemDao {
    boolean addItem(Item item) throws SQLException;

    ItemDto searchItem(int id) throws SQLException;
}
