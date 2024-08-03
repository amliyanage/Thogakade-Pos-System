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

    @Override
    public boolean updateItem(Item item) throws SQLException {

        connection = ConnectionManager.getInstance().getConnection();

        preparedStatement = connection.prepareStatement("UPDATE item SET item_name = ? , item_qty = ? , item_price = ? WHERE item_id=?");
        preparedStatement.setString(1, item.getName());
        preparedStatement.setInt(2, item.getQty());
        preparedStatement.setDouble(3, item.getPrice());
        preparedStatement.setInt(4, item.getId());

        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean deleteItem(int id) throws SQLException {

        connection = ConnectionManager.getInstance().getConnection();

        preparedStatement = connection.prepareStatement("DELETE FROM item WHERE item_id=?");
        preparedStatement.setInt(1, id);

        return preparedStatement.executeUpdate() > 0;
    }
}
