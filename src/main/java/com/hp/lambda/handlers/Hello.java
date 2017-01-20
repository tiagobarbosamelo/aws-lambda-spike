/**
 * (C) Copyright 2013-2016 HP Development Company, L.P.
 * Confidential computer software. Valid license from HP required for possession, use or copying.
 * Consistent with FAR 12.211 and 12.212, Commercial Computer Software,
 * Computer Software Documentation, and Technical Data for Commercial Items are licensed
 * to the U.S. Government under vendor's standard commercial license.
 */
package com.hp.lambda.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.hp.lambda.pojo.MyPojo;

/**
 * Lambda Hello World class,
 *
 */
public class Hello implements RequestHandler<MyPojo, String> {

    /**
     * Handler sample events.
     *
     * @param input It receives a JSON that represent MyPojo class
     * @param context The Lambda execution environment context object.
     *
     * @return Hello world message
     */
    public String handleRequest(MyPojo input, Context context) {
        LambdaLogger logger = context.getLogger();
        String message =
                "Hello world Lambda! First name = " + input.getFirstName() + "last name = " + input.getLastName();
        logger.log(message);
        return message;
    }
}
