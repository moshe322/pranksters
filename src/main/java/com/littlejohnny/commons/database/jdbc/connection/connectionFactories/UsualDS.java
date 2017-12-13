package com.littlejohnny.commons.database.jdbc.connection.connectionFactories;

import com.littlejohnny.commons.database.jdbc.exceptions.DbException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UsualDS implements ConnectionFactory {
    private Connection connection;

    @Override
    public Connection newConnection(String url, String login, String password) {
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void close() throws DbException {
        try {
            connection.close();
        } catch (SQLException e) {
            //NOP
        }
    }
}
