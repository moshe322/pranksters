package com.littlejohnny.commons.database.jdbc.connection.connectionFactories;

import org.apache.log4j.Logger;

public class ConnectionFactoryFactory {
    private static boolean initialized;
    //private static final Logger logger = Logger.getLogger(LoggerUtils.getCurrentClassName());

    private static void initDriver(String driver){
        if(!initialized) {
            try {
                //logger.debug("Try to init JDBC driver ...");
                Class.forName(driver);
                initialized = true;
            } catch (ClassNotFoundException e) {
                //logger.warn("Can`t initialize JDBC driver", e);
            }
        }
    }

    public static ConnectionFactory newConnectionFactory(String driver, DsTypes dsType) {
        ConnectionFactory connectionFactory;
        initDriver(driver);
        switch (dsType){
            case C3P0:
                //logger.info("Try to create new C3P0 connection pool");
                connectionFactory = new C3P0DS();
                break;
            case PROXOL:
                //logger.info("Try to create new Proxol connection pool");
                connectionFactory = new ProxolDS();
                break;
            case DBCP:
                //logger.info("Try to create new DBCP connection pool");
                connectionFactory = new DbcpDS();
                break;
            case USUAL:
                //logger.info("Try to create new USUAL connection pool");
                connectionFactory = new UsualDS();
                break;
            default :
                //logger.info("Try to create new USUAL connection pool");
                connectionFactory = new UsualDS();
                break;

        }
        return connectionFactory;
    }

    private ConnectionFactoryFactory() {
    }
}
