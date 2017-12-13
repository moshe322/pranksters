package com.littlejohnny.commons.database.jdbc.DAO;

import com.littlejohnny.commons.database.jdbc.utills.Enricher;
import com.littlejohnny.commons.database.jdbc.utills.JdbcUtils;
import com.littlejohnny.commons.database.jdbc.connection.TransactionManagerImpl;
import com.littlejohnny.commons.database.jdbc.exceptions.DbException;

import java.sql.*;
import java.util.List;

public abstract class AbstractDAO<T> {

    //private static final Logger logger = Logger.getLogger(LoggerUtils.getCurrentClassName());

    private Connection getConnection() throws DbException {
        Connection connection = TransactionManagerImpl.getConnection();
        if(connection == null) {
            throw new IllegalStateException("Can`t create connection");
        }
        return connection;
    }

    protected Connection getSerializableConnection() throws DbException {
        Connection connection = getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DbException("Can`t set properties to connection", e);
        }
        return connection;
    }


    protected List<T> selectAll(String sql, Enricher<T> enricher) throws DbException {
        Connection connection = getSerializableConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        List<T> result;
        try {
            //logger.debug("Trying execute sql: " + sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            result = enricher.enrichAll(resultSet);
            //logger.debug("Result of execution: " + result.toString());
        } catch(SQLException e) {
            //logger.warn("Can`t execute sql: " + sql, e);
            throw new DbException("Can`t execute sql" + sql, e);
        } finally {
            JdbcUtils.closeResSetQuietly(resultSet);
            JdbcUtils.closeStatementQuietly(statement);
        }
        return result;
    }

    protected T selectById(int id, String sql, Enricher<T> enricher) throws DbException {
        Connection connection = getSerializableConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        T result = null;
        try {
            //logger.debug("Trying execute sql: " + sql + " with id " + id);
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            result = enricher.enrichOne(resultSet);
            //logger.debug("Result of execution: " + result.toString());
        } catch (SQLException e) {
            //logger.warn("Can`t execute sql: " + sql + " with id " + id, e);
            throw new DbException("Can`t execute sql" + sql, e);
        } finally {
            JdbcUtils.closeResSetQuietly(resultSet);
            JdbcUtils.closeStatementQuietly(statement);
        }
        return result;
    }


    protected int deleteById(int id, String sql) throws DbException {
        Connection connection = getSerializableConnection();
        PreparedStatement statement = null;
        int result = 0;
        try{
            //logger.debug("Trying execute sql: " + sql + " with id " + id);
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            result = statement.executeUpdate();
            //logger.debug("Result of execution: " + result);
        } catch (SQLException e) {
            //logger.warn("Can`t execute sql: " + sql + " with id " + id, e);
            throw new DbException("Can`t execute sql" + sql, e);
        } finally {
            JdbcUtils.closeStatementQuietly(statement);
        }
        return result;
    }


}
