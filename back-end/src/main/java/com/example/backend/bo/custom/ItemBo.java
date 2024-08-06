package com.example.backend.bo.custom;

import com.example.backend.bo.SuperBO;
import com.example.backend.dto.ItemDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo extends SuperBO {
    boolean addItem(ItemDto itemDto) throws SQLException;
    ItemDto searchItem(String id) throws SQLException;

    boolean updateItem(ItemDto itemDto) throws SQLException;

    boolean deleteItem(String i) throws SQLException;

    List<ItemDto> getAllItems() throws SQLException;
}
