package com.littlejohnny.commons.database.jdbc.utills;

import com.littlejohnny.commons.database.dataObjects.Game;
import com.littlejohnny.commons.database.jdbc.exceptions.DbException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameEnricher extends Enricher<Game> {

    @Override
    public Game enrichOne(ResultSet resultSet) throws DbException {
        Game game = null;
        try {
            if(resultSet.next()) {
                game = new Game(resultSet.getInt("game_id"), resultSet.getString("game_name"), resultSet.getString("game_shortDescription"), resultSet.getString("game_fullDescription"), resultSet.getString("game_ico"), StringParser.stringToMap(resultSet.getString("images")), resultSet.getFloat("rate"));
            }
        } catch (SQLException e) {
            throw new DbException("Can`t enrich game", e);
        }
        return game;
    }
}
