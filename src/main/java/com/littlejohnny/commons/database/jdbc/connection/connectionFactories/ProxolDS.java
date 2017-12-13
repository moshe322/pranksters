package com.littlejohnny.commons.database.jdbc.connection.connectionFactories;

import com.littlejohnny.commons.database.jdbc.exceptions.DbException;

import java.sql.Connection;

public class ProxolDS implements ConnectionFactory {
    @Override
    public Connection newConnection(String url, String login, String password) {
        return null;
    }

    @Override
    public void close() throws DbException {
    }
}
