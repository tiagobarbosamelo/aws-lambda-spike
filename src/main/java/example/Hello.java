/**
 * (C) Copyright 2013-2016 HP Development Company, L.P.
 * Confidential computer software. Valid license from HP required for possession, use or copying.
 * Consistent with FAR 12.211 and 12.212, Commercial Computer Software,
 * Computer Software Documentation, and Technical Data for Commercial Items are licensed
 * to the U.S. Government under vendor's standard commercial license.
 */
package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Hello implements RequestHandler<MyPojo, String> {

    public String handleRequest(MyPojo input, Context context) {
        LambdaLogger logger = context.getLogger();
        String message =
                "Hello world Lambda! First name = " + input.getFirstName() + "last name = " + input.getLastName();
        logger.log(message);
        return message;
    }
}
