/**
 * (C) Copyright 2013-2017 HP Development Company, L.P.
 * Confidential computer software. Valid license from HP required for possession, use or copying.
 * Consistent with FAR 12.211 and 12.212, Commercial Computer Software,
 * Computer Software Documentation, and Technical Data for Commercial Items are licensed
 * to the U.S. Government under vendor's standard commercial license.
 */
package com.hp.lambda.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.hp.lambda.pojo.MyPojo;
import org.apache.log4j.Logger;

/**
 * Lambda class based on SampleLambda class. Used to show Log4j on Lambda environment.
 */
public class LambdaLog4j implements RequestHandler<MyPojo, String> {

    private static final Logger LOGGER = Logger.getLogger(LambdaLog4j.class);

    /**
     * Handler sample events. Logs SampleLambda wordl message and JSON paramters that represents MyPojo class.
     *
     * @param input   It receives a JSON that represent MyPojo class
     * @param context The Lambda execution environment context object.
     *
     * @return SampleLambda world message
     */
    public String handleRequest(MyPojo input, Context context) {
        String message = "Sample Lambda!";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Debug is enabled");
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Info is enabled");
        }


        LOGGER.info(message);
        return message;
    }
}
