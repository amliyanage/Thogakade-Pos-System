package com.example.backend.dao.custom;

import com.example.backend.dao.ItemDao;
import com.example.backend.db.ConnectionManager;
import com.example.backend.dto.ItemDto;
import com.example.backend.entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDaoImpl implements ItemDao {

    Connection connection;
    PreparedStatement preparedStatement;

    @Override
    public boolean addItem(Item item) throws SQLException {

        connection = ConnectionManager.getInstance().getConnection();

        preparedStatement = connection.prepareStatement("INSERT INTO item VALUES (?,?,?,?)");
        preparedStatement.setInt(1, item.getId());
        preparedStatement.setString(2, item.getName());
        preparedStatement.setInt(3, item.getQty());
        preparedStatement.setDouble(4, item.getPrice());

        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public ItemDto searchItem(int id) throws SQLException {

        connection = ConnectionManager.getInstance().getConnection();

        preparedStatement = connection.prepareStatement("SELECT * FROM item WHERE item_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new ItemDto(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            );
        }

        return null;

    }
}
