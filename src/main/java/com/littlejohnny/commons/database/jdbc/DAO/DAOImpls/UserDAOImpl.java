package com.littlejohnny.commons.database.jdbc.DAO.DAOImpls;

import com.littlejohnny.commons.database.dataObjects.User;
import com.littlejohnny.commons.database.jdbc.DAO.AbstractDAO;
import com.littlejohnny.commons.database.jdbc.DAO.UserDAO;
import com.littlejohnny.commons.database.jdbc.exceptions.DbException;
import com.littlejohnny.commons.database.jdbc.utills.JdbcUtils;
import com.littlejohnny.commons.database.jdbc.utills.UserEnricher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {


    private static final String TABLE_NAME = "users";
    private static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;
    private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE user_id = ?;";
    private static final String SELECT_BY_EMAIL = "SELECT * FROM " + TABLE_NAME + " WHERE email = ?;";
    private static final String UPDATE_BY_ID = "UPDATE " + TABLE_NAME + " SET email = ?, password = ?, name = ?, surname = ?, birthday = ?, country = ?, sity = ? WHERE user_id = ?";
    private static final String UPDATE_BY_EMAIL = "UPDATE " + TABLE_NAME + " SET email = ?, password = ?, name = ?, surname = ?, birthday = ?, country = ?, sity = ? WHERE email = ?";
    private static final String DELETE_BY_ID = "DELETE FROM " + TABLE_NAME +" WHERE user_id = ?";
    private static final String DELETE_BY_EMAIL = "DELETE FROM " + TABLE_NAME +" WHERE email = ?";
    private static final String CREATE = "INSERT INTO " + TABLE_NAME + " (email, password, name, surname, birthday, country, sity) VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final UserEnricher enricher = new UserEnricher();


    @Override
    public List<User> selectAll() throws DbException {
        return selectAll(SELECT_ALL, new UserEnricher());
    }

    @Override
    public User selectById(int id) throws DbException {
        return selectById(id, SELECT_BY_ID, new UserEnricher());
    }

    @Override
    public User selectByEmail(String email) throws DbException {
        Connection connection = getSerializableConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User result = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_EMAIL);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            result = enricher.enrichOne(resultSet);
        } catch (SQLException e) {
            throw new DbException("Can`t execute sql", e);
        } finally {
            JdbcUtils.closeResSetQuietly(resultSet);
            JdbcUtils.closeStatementQuietly(statement);
        }
        return result;
    }

    @Override
    public int updateById(int id, User object) throws DbException {
        Connection connection = getSerializableConnection();
        PreparedStatement statement = null;
        int result;
        try {
            statement = connection.prepareStatement(UPDATE_BY_ID);
            statement.setString(1, object.getEmail());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getName());
            statement.setString(4, object.getSurname());
            statement.setDate(5, object.getBirthday());
            statement.setString(6, object.getCountry());
            statement.setString(7, object.getSity());
            statement.setInt(8, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Can`t update user: " + object.toString() + " with id: " + id + " using " + UPDATE_BY_ID + " sql", e);
        } finally {
            JdbcUtils.closeStatementQuietly(statement);
        }
        return result;
    }

    @Override
    public int updateByEmail(String email, User object) throws DbException {
        Connection connection = getSerializableConnection();
        PreparedStatement statement = null;
        int result;
        try {
            statement = connection.prepareStatement(UPDATE_BY_EMAIL);
            statement.setString(1, object.getEmail());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getName());
            statement.setString(4, object.getSurname());
            statement.setDate(5, object.getBirthday());
            statement.setString(6, object.getCountry());
            statement.setString(7, object.getSity());
            statement.setString(8, email);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Can`t update user: " + object.toString() + " with id: " + email + " using " + UPDATE_BY_EMAIL + " sql", e);
        } finally {
            JdbcUtils.closeStatementQuietly(statement);
        }
        return result;
    }

    @Override
    public int deleteById(int id) throws DbException {
        return deleteById(id, DELETE_BY_ID);
    }

    @Override
    public int deleteByEmail(String email) throws DbException {
        Connection connection = getSerializableConnection();
        PreparedStatement statement = null;
        int result;
        try{
            statement = connection.prepareStatement(DELETE_BY_EMAIL);
            statement.setString(1, email);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Can`t execute sql" + DELETE_BY_EMAIL, e);
        } finally {
            JdbcUtils.closeStatementQuietly(statement);
        }
        return result;
    }

    @Override
    public void create(User object) throws DbException {
        Connection connection = getSerializableConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CREATE);
            statement.setString(1, object.getEmail());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getName());
            statement.setString(4, object.getSurname());
            statement.setDate(5, object.getBirthday());
            statement.setString(6, object.getCountry());
            statement.setString(7, object.getSity());
            statement.execute();
        } catch (SQLException e) {
            throw new DbException("Can`t create user: " + object.toString(), e);
        } finally {
            JdbcUtils.closeStatementQuietly(statement);
        }
    }
}
