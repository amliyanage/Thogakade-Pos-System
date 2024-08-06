package com.example.backend.dao.custom;

import com.example.backend.dto.ItemDto;
import com.example.backend.entity.Item;
import com.example.backend.utill.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao extends CrudUtil<Item> {

}
