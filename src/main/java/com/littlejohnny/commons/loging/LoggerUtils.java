package com.littlejohnny.commons.loging;

public class LoggerUtils {
    public static String getCurrentClassName() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            return e.getStackTrace()[1].getClassName();
        }
    }

    private LoggerUtils() {}
}
