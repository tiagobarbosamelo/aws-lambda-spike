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
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.hp.lambda.utils.UploadFileS3;

import java.io.IOException;
import java.util.List;

/**
 * Lambda class to deal with AWS Kinesis Stream events
 */
public class LambdaKinesis implements RequestHandler<KinesisEvent, Object> {

    String bucketName = "tiago-melo-hello-world-bucket";
    String fileName = "MyLambdaLog.txt";

    /**
     * Handle AWS Kinesis stream event.
     *
     * @param input   Kinesis stream event.
     * @param context The Lambda execution environment context object.
     *
     * @return Sample message
     */
    public String handleRequest(KinesisEvent input, Context context) {

        List<KinesisEvent.KinesisEventRecord> records = input.getRecords();

        StringBuilder sb = new StringBuilder();
        String data = "";

        for (KinesisEvent.KinesisEventRecord record : records) {
            data = new String(record.getKinesis().getData().array());
            sb.append(data);
        }

        context.getLogger().log("Input: " + sb.toString());

        try {
            new UploadFileS3().uploadFileToS3Bucket(bucketName, fileName, data);
        } catch (IOException ioE) {
            ioE.printStackTrace();
        }

        return "Kinesis Stream event";
    }
}