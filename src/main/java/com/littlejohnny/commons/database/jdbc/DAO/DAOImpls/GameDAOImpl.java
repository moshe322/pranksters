package com.littlejohnny.commons.database.jdbc.DAO.DAOImpls;

import com.littlejohnny.commons.database.dataObjects.Game;
import com.littlejohnny.commons.database.jdbc.DAO.AbstractDAO;
import com.littlejohnny.commons.database.jdbc.DAO.GameDAO;
import com.littlejohnny.commons.database.jdbc.exceptions.DbException;
import com.littlejohnny.commons.database.jdbc.utills.GameEnricher;
import com.littlejohnny.commons.database.jdbc.utills.JdbcUtils;
import com.littlejohnny.commons.database.jdbc.utills.StringParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class GameDAOImpl extends AbstractDAO<Game> implements GameDAO {

    private static final String TABLE_NAME = "games";
    private static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;
    private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE game_id = ?;";
    private static final String UPDATE_BY_ID = "UPDATE " + TABLE_NAME + " SET game_name = ?, game_shortDescription = ?, game_fullDescription = ?, game_ico = ?, images = ?, rate = ? WHERE game_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM " + TABLE_NAME +" WHERE game_id = ?";
    private static final String CREATE = "INSERT INTO " + TABLE_NAME + " (game_name, game_shortDescription, game_fullDescription, game_ico, images, rate) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String CREATE_WITH_ID = "INSERT INTO " + TABLE_NAME + " (game_id, game_name, game_shortDescription, game_fullDescription, game_ico, images, rate) VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final GameEnricher enricher = new GameEnricher();

    @Override
    public List<Game> selectAll() throws DbException {
        return selectAll(SELECT_ALL, enricher);
    }

    @Override
    public Game selectById(int id) throws DbException {
        return selectById(id, SELECT_BY_ID, enricher);
    }

    @Override
    public int updateById(int id, Game object) throws DbException {
        Connection connection = getSerializableConnection();
        PreparedStatement statement = null;
        int result;
        try {
            statement = connection.prepareStatement(UPDATE_BY_ID);
            statement.setString(1, object.getName());
            statement.setString(2, object.getShortDescription());
            statement.setString(3, object.getFullDescription());
            statement.setString(4, object.getIco());
            statement.setString(5, StringParser.mapToString(object.getImages()));
            statement.setFloat(6, object.getRate());
            statement.setInt(7, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Can`t update game: " + object.toString() + " with id: " + id + " using " + UPDATE_BY_ID + " sql", e);
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
    public void create(Game object) throws DbException {
        Connection connection = getSerializableConnection();
        PreparedStatement statement = null;
        try {
            if(object.getId() == 0) {
                statement = connection.prepareStatement(CREATE);
                statement.setString(1, object.getName());
                statement.setString(2, object.getShortDescription());
                statement.setString(3, object.getFullDescription());
                statement.setString(4, object.getIco());
                statement.setString(5, StringParser.mapToString(object.getImages()));
                statement.setFloat(6, object.getRate());
            } else {
                statement = connection.prepareStatement(CREATE_WITH_ID);
                statement.setInt(1, object.getId());
                statement.setString(2, object.getName());
                statement.setString(3, object.getShortDescription());
                statement.setString(4, object.getFullDescription());
                statement.setString(5, object.getIco());
                statement.setString(6, StringParser.mapToString(object.getImages()));
                statement.setFloat(7, object.getRate());
            }
            statement.execute();
        } catch (SQLException e) {
            throw new DbException("Can`t create game: " + object.toString(), e);
        } finally {
            JdbcUtils.closeStatementQuietly(statement);
        }
    }

}
