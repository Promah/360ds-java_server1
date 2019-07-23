package com.onseo.courses.ds.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Logging {
    private static final Logger logger = LogManager.getRootLogger();

    public static Logger getLogger() {
        return logger;
    }
}
