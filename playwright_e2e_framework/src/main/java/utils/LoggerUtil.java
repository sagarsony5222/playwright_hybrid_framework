package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {
    private static final ThreadLocal<Logger> loggerThread = new ThreadLocal<>();

    public static Logger getLogger(Class<?> clazz) {
        if (loggerThread.get() == null) {
            loggerThread.set(LogManager.getLogger(clazz));
        }
        return loggerThread.get();
    }

    public static void removeLogger() {
        loggerThread.remove();
    }
}
