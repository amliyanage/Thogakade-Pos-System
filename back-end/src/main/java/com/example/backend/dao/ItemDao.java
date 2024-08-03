package com.example.backend.dao;

import com.example.backend.dto.ItemDto;
import com.example.backend.entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao {
    boolean addItem(Item item) throws SQLException;

    ItemDto searchItem(int id) throws SQLException;

    boolean updateItem(Item item) throws SQLException;

    boolean deleteItem(int id) throws SQLException;

    List<Item> getAllItems() throws SQLException;
}
