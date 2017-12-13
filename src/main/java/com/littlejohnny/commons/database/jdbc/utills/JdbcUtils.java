package com.littlejohnny.commons.database.jdbc.utills;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {

    //private static final Logger logger = Logger.getLogger(JdbcUtils.class);

    public static void closeStatementQuietly(Statement statement) {
        try {
            //logger.debug("Try to close statement ...");
            statement.close();
            //logger.debug("Statement is closed!");
        } catch (SQLException e) {
            //logger.warn("Can`t close statement", e);
        }

    }

    public static void closeResSetQuietly(ResultSet resultSet) {
        try {
            //logger.debug("Try to close result set ...");
            resultSet.close();
            //logger.debug("Result set is closed!");
        } catch (SQLException e) {
            //logger.warn("Can`t close result set", e);
        }
    }

    public static void rollbackQuietly(Connection connection) {
        try {
            //logger.debug("Try to rollback ...");
            connection.rollback();
            //logger.debug("Rollback is successful!");
        } catch (SQLException e) {
            //logger.warn("Can`t close result set", e);
        }
    }

    public static void closeConnectionQuietly(Connection connection) {
        try {
            //logger.debug("Try to close connection ...");
            connection.close();
            //logger.debug("Connection is closed!");
        } catch (SQLException e) {
            //logger.warn("Can`t close connection", e);
        }
    }
}
