package com.heshan.framework.db.dynamic;

/**
 * @author <a href="mailto:heshan664754022@gmail.com">Frank</a>
 * @version V1.0
 * @description
 * @date 2016/5/30 17:27
 */
import org.apache.log4j.Logger;

public class LogUtils {
    protected static final Logger DEBUG_LOG = Logger.getLogger("debug");
    protected static final Logger ACCESS_LOG = Logger.getLogger("access");
    protected static final Logger SERVICE_API_LOG = Logger.getLogger("api");
    protected static final Logger SERVICE_LOG = Logger.getLogger("service");
    protected static final Logger PERF_LOG = Logger.getLogger("perf");
    protected static final Logger ERROR_LOG = Logger.getLogger("exception");
    protected static final Logger COM_ALARM_VALUE = Logger.getLogger("alarm");
    protected static final Logger SERVICE_RPC_LOG = Logger.getLogger("rpc");

    public LogUtils() {
    }

    public static void debugLog(String log) {
        if (DEBUG_LOG.isDebugEnabled()) {
            DEBUG_LOG.debug(log);
        }

    }

    public static void accessLog(String log) {
        ACCESS_LOG.info(log);
    }

    public static void serviceApiLog(String log) {
        SERVICE_API_LOG.info(log);
    }

    public static void serviceApiDebugLog(String log) {
        if (SERVICE_API_LOG.isDebugEnabled()) {
            SERVICE_API_LOG.debug(log);
        }

    }

    public static void serviceLog(String log) {
        SERVICE_LOG.info(log);
    }

    public static void serviceDebugLog(String log) {
        if (SERVICE_LOG.isDebugEnabled()) {
            SERVICE_LOG.debug(log);
        }

    }

    public static void perfLog(String log) {
        PERF_LOG.info(log);
    }

    public static void perfDebugLog(String log) {
        if (PERF_LOG.isDebugEnabled()) {
            PERF_LOG.debug(log);
        }

    }

    public static void error(String log, Throwable e) {
        ERROR_LOG.error(log, e);
    }

    public static void error(String log) {
        ERROR_LOG.error(log);
    }


    public static void comAlarmValue(String message) {
        COM_ALARM_VALUE.info(message);
    }

    public static void serviceRpcLog(String log) {
        SERVICE_RPC_LOG.info(log);
    }
}