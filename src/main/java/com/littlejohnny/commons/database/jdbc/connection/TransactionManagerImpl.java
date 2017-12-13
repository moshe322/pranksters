package com.littlejohnny.commons.database.jdbc.connection;

import com.littlejohnny.commons.database.jdbc.utills.JdbcUtils;
import com.littlejohnny.commons.database.jdbc.connection.connectionFactories.ConnectionFactory;
import com.littlejohnny.commons.database.jdbc.connection.connectionFactories.ConnectionFactoryFactory;
import com.littlejohnny.commons.database.jdbc.connection.connectionFactories.DsTypes;
import com.littlejohnny.commons.database.jdbc.exceptions.DbException;

import java.sql.Connection;
import java.util.concurrent.Callable;

public class TransactionManagerImpl implements TransactionManager {
    private static final String DB_NAME = "pranksters_db";
    private static final String URL = "jdbc:mysql://localhost:3306/"+DB_NAME;
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    //private static final Logger logger = Logger.getLogger(LoggerUtils.getCurrentClassName());

    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();
    private static final ConnectionFactory connectionFactory = ConnectionFactoryFactory.newConnectionFactory(DRIVER, DsTypes.C3P0);

    @Override
    public <T> T doInTransaction(Callable<T> unitOfWork) throws Exception {
        //logger.debug("Starting new transaction ...");
        Connection connection = connectionFactory.newConnection(URL, LOGIN, PASSWORD);
        //logger.debug("Mapping connection to flow ...");
        connectionHolder.set(connection);
        try {
            //logger.debug("Try to execute SQL ...");
            T result = unitOfWork.call();
            //logger.debug("Try to commit ...");
            connection.commit();
            //logger.debug("Transaction is commited");
            return result;
        } catch (Exception e) {
            //logger.warn("Can`t complete transaction. Rollback changes", e);
            connection.rollback();
            throw e;
        } finally {
            JdbcUtils.closeConnectionQuietly(connection);
            //logger.debug("Discard mapping connection to flow ...");
            connectionHolder.remove();
        }
    }

    public static Connection getConnection() throws DbException {
        Connection connection;
        if((connection = connectionHolder.get()) != null) {
            return connection;
        }
        return connectionFactory.newConnection(URL, LOGIN, PASSWORD);
    }

    public static String getDbName() {
        return DB_NAME;
    }
}


