package com.littlejohnny.commons.database.jdbc.DAO;

import com.littlejohnny.commons.database.dataObjects.User;
import com.littlejohnny.commons.database.jdbc.exceptions.DbException;

public interface UserDAO extends DAO<User> {

    User selectByEmail(String email) throws DbException;

    int updateByEmail(String email, User object) throws DbException;

    int deleteByEmail(String email) throws DbException;
}
