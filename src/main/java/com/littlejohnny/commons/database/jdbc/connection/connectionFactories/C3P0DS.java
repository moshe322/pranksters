package com.littlejohnny.commons.database.jdbc.connection.connectionFactories;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class C3P0DS implements ConnectionFactory {
    private ComboPooledDataSource cpds;
    //private static final Logger logger = Logger.getLogger(LoggerUtils.getCurrentClassName());

    {
        cpds = new ComboPooledDataSource(false);
        cpds.setMinPoolSize(1);
        cpds.setAcquireIncrement(1);
        cpds.setMaxPoolSize(10);
        //logger.debug("Params of C3P0 connection pool: MinPoolSize" + cpds.getMinPoolSize() + ", AcquireIncrement " + cpds.getAcquireIncrement() + ", MaxPoolSize " + cpds.getMaxPoolSize());
    }

    @Override
    public Connection newConnection(String url, String login, String password)  {
        //logger.info("Creating new C3P0 connection ...");
        Connection connection = null;
        cpds.setJdbcUrl(url);
        cpds.setUser(login);
        cpds.setPassword(password);
        //logger.debug("Connection params: JdbcUrl " + url + ", Login " + login + ", Password " + password);


        try {
            //logger.debug("Try to create new C3P0 connection ...");
            connection = cpds.getConnection();
            //logger.debug("New C3P0 connection created succesfully! " + connection.toString());
        } catch (SQLException e) {
            //logger.warn("Creation of new C3P0 connection is failed!", e);
        }

        return connection;
    }

    @Override
    public void close() {
        //logger.info("Closing C3P0 factory");
        cpds.close();
    }

}
