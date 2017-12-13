package com.littlejohnny.commons.database.jdbc.connection.connectionFactories;

import com.littlejohnny.commons.database.jdbc.exceptions.DbException;

import java.sql.Connection;

public interface ConnectionFactory {
    Connection newConnection(String url, String login, String password) throws DbException;

    void close() throws DbException;
}
