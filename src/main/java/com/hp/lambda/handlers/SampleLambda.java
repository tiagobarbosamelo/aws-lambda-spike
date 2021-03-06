/**
 * (C) Copyright 2013-2017 HP Development Company, L.P.
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

import java.util.Random;

/**
 * Lambda SampleLambda World class,
 */
public class SampleLambda implements RequestHandler<MyPojo, String> {

    /**
     * Handler sample events.
     *
     * @param myPojo  It receives a JSON that represent MyPojo class
     * @param context The Lambda execution environment context object.
     *
     * @return SampleLambda world message
     */
    public String handleRequest(MyPojo myPojo, Context context) {
        LambdaLogger logger = context.getLogger();
        String message = pojoInfo(myPojo);
        logger.log(message);

        if (myPojo.getRandomFailure()) {
            Random r = new Random();
            boolean fail = r.nextBoolean();
            if (fail) {
                System.out.println("Forced fail on retry");
                throw new NullPointerException("Random failure");
            }
        }

        try {
            Thread.sleep(myPojo.getTimeout());
        } catch (Exception ex) {
            myPojo.setTimeout(1);
            System.out.println(ex.getStackTrace());
        }

        return message;
    }

    private String pojoInfo(MyPojo myPojo) {
        return "MyPojo = timeout = " + myPojo.getTimeout() + " -  should throw exception = " + myPojo
                .getThrowException() + " - random failure" + myPojo.getRandomFailure();
    }
}
