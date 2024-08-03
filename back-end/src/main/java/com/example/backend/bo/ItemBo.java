package com.example.backend.bo;

import com.example.backend.dto.ItemDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo {
    boolean addItem(ItemDto itemDto) throws SQLException;
    ItemDto searchItem(int id) throws SQLException;

    boolean updateItem(ItemDto itemDto) throws SQLException;

    boolean deleteItem(int i) throws SQLException;

    List<ItemDto> getAllItems() throws SQLException;
}
