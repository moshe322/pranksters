package com.littlejohnny.commons.database.jdbc.DAO;

import com.littlejohnny.commons.database.jdbc.exceptions.DbException;

import java.util.List;

public interface DAO<T> {

    List<T> selectAll() throws DbException;

    T selectById(int id) throws DbException;

    int updateById(int id, T object) throws DbException;

    int deleteById(int id) throws DbException;

    void create (T object) throws DbException;

}
