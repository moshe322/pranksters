package com.littlejohnny.commons.database.jdbc.utills;

import com.littlejohnny.commons.database.jdbc.exceptions.DbException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Enricher<T> {

    public abstract T enrichOne(ResultSet resultSet) throws DbException;

    public List<T> enrichAll(ResultSet resultSet) throws DbException {
        List<T> list = new ArrayList<>();
        try {
            while (!resultSet.isLast()) {
                list.add(enrichOne(resultSet));
            }
        } catch (SQLException e) {
            throw new DbException("Can`t enrich ", e);
        }
        return list;
    }

}
