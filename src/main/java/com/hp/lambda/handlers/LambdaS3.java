/**
 * (C) Copyright 2013-2016 HP Development Company, L.P.
 * Confidential computer software. Valid license from HP required for possession, use or copying.
 * Consistent with FAR 12.211 and 12.212, Commercial Computer Software,
 * Computer Software Documentation, and Technical Data for Commercial Items are licensed
 * to the U.S. Government under vendor's standard commercial license.
 */
package com.hp.lambda.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.event.S3EventNotification;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Lambda class to deal with AWS S3 Bucket events
 */
public class LambdaS3 implements RequestHandler<S3EventNotification, String> {

    /**
     * Handle AWS Lambda event.
     *
     * @param input S3 event
     * @param context The Lambda execution environment context object.
     * @return String with some info about S3 bucket
     */

    public String handleRequest(S3EventNotification input, Context context) {
        List<S3EventNotification.S3EventNotificationRecord> records = input.getRecords();

        String separator = ",";
        StringBuilder sb = new StringBuilder();
        for (S3EventNotification.S3EventNotificationRecord record : records) {
            sb.append(record.getEventName())
                    .append(separator)
                    .append(record.getAwsRegion())
                    .append(separator)
                    .append(record.getEventSource())
                    .append(separator)
                    .append(record.getS3().getBucket().getArn())
                    .append(separator)
                    .append(record.getS3().getBucket().getName())
                    .append(separator)
                    .append(record.getS3().getBucket().getOwnerIdentity().getPrincipalId());
        }


        context.getLogger().log(sb.toString());
        return sb.toString();

    }
}