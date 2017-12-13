package database;

import com.littlejohnny.commons.database.jdbc.connection.TransactionManager;
import com.littlejohnny.commons.database.jdbc.utills.JdbcUtils;
import com.littlejohnny.commons.database.jdbc.connection.connectionFactories.ConnectionFactory;
import com.littlejohnny.commons.database.jdbc.connection.connectionFactories.ConnectionFactoryFactory;
import com.littlejohnny.commons.database.jdbc.connection.connectionFactories.DsTypes;
import com.littlejohnny.commons.database.jdbc.exceptions.DbException;

import java.sql.Connection;
import java.util.concurrent.Callable;

public class TransactionManagerTest implements TransactionManager {
    private static final String DB_NAME = "pranksters_db_test";
    private static final String URL = "jdbc:mysql://localhost:3306/"+DB_NAME;
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();
    private static final ConnectionFactory connectionFactory = ConnectionFactoryFactory.newConnectionFactory(DRIVER, DsTypes.C3P0);

    @Override
    public <T> T doInTransaction(Callable<T> unitOfWork) throws Exception {
        Connection connection = connectionFactory.newConnection(URL, LOGIN, PASSWORD);
        connectionHolder.set(connection);
        try {
            T result = unitOfWork.call();
            connection.commit();
            return result;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            JdbcUtils.closeConnectionQuietly(connection);
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
