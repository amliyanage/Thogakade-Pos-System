package com.example.backend.dao.custom.impl;

import com.example.backend.dao.custom.ItemDao;
import com.example.backend.db.ConnectionManager;
import com.example.backend.dto.ItemDto;
import com.example.backend.entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

    Connection connection;
    PreparedStatement preparedStatement;

    @Override
    public boolean save(Item item) throws SQLException {
        connection = ConnectionManager.getInstance().getConnection();

        preparedStatement = connection.prepareStatement("INSERT INTO item VALUES (?,?,?,?)");
        preparedStatement.setString(1, item.getId());
        preparedStatement.setString(2, item.getName());
        preparedStatement.setInt(3, item.getQty());
        preparedStatement.setDouble(4, item.getPrice());

        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public Item getData(String id) throws SQLException {
        connection = ConnectionManager.getInstance().getConnection();

        preparedStatement = connection.prepareStatement("SELECT * FROM item WHERE item_id=?");
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            );
        }

        return null;
    }

    @Override
    public boolean update(Item item) throws SQLException {
        connection = ConnectionManager.getInstance().getConnection();

        preparedStatement = connection.prepareStatement("UPDATE item SET item_name = ? , item_qty = ? , item_price = ? WHERE item_id=?");
        preparedStatement.setString(1, item.getName());
        preparedStatement.setInt(2, item.getQty());
        preparedStatement.setDouble(3, item.getPrice());
        preparedStatement.setString(4, item.getId());

        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        connection = ConnectionManager.getInstance().getConnection();

        preparedStatement = connection.prepareStatement("DELETE FROM item WHERE item_id=?");
        preparedStatement.setString(1, id);

        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public List<Item> getAll() throws SQLException {

        connection = ConnectionManager.getInstance().getConnection();

        preparedStatement = connection.prepareStatement("SELECT * FROM item");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Item> itemList = new ArrayList<>();

        while (resultSet.next()) {
            itemList.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            ));
        }

        return itemList;
    }
}
