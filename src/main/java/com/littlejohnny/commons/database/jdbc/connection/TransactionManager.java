package com.littlejohnny.commons.database.jdbc.connection;

import java.util.concurrent.Callable;

public interface TransactionManager {
    <T> T doInTransaction(Callable<T> unitOfWork) throws Exception;
}
