package com.example.backend.utill;

import com.example.backend.db.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public static <T> T execute(String sql, Object... params) throws SQLException {
        PreparedStatement statement = ConnectionManager.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            statement.setObject((i + 1), params[i]);
        }
        if (sql.startsWith("SELECT") || sql.startsWith("select")) {
            return (T) statement.executeQuery();
        } else {
            return (T) (Boolean) (statement.executeUpdate() > 0);
        }
    }
}
