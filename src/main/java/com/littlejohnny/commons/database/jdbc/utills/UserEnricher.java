package com.littlejohnny.commons.database.jdbc.utills;

import com.littlejohnny.commons.database.dataObjects.User;
import com.littlejohnny.commons.database.jdbc.exceptions.DbException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserEnricher extends Enricher<User> {

    //private static final Logger logger = Logger.getLogger(LoggerUtils.getCurrentClassName());

    @Override
    public User enrichOne(ResultSet resultSet) throws DbException {
        User user = null;
        try {
            //logger.debug("Try enrich user ...");
            if(resultSet.next()) {
                user = new User(resultSet.getInt("user_id"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("name"), resultSet.getString("surname"), resultSet.getDate("birthday"), resultSet.getString("country"), resultSet.getString("sity"));
            }
            //logger.debug("User is enriched successfully! " + quiz.toString());
        } catch (SQLException e) {
            //logger.warn("Can`t enrich user!", e);
            throw new DbException("Can`t enrich user", e);
        }
        return user;
    }

}
