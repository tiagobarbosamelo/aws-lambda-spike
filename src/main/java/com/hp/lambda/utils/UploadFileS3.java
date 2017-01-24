/**
 * (C) Copyright 2013-2016 HP Development Company, L.P.
 * Confidential computer software. Valid license from HP required for possession, use or copying.
 * Consistent with FAR 12.211 and 12.212, Commercial Computer Software,
 * Computer Software Documentation, and Technical Data for Commercial Items are licensed
 * to the U.S. Government under vendor's standard commercial license.
 */
package com.hp.lambda.utils;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.*;

/**
 * Class responsible to upload txt files to AWS S3 bucket.
 */
public class UploadFileS3 {

    /**
     * Upload a file into a AWS S3 bucket.
     *
     * @param s3BucketName AWS S3 Bucket name
     * @param fileName file name
     * @param fileContent file content
     * @throws IOException if something goes wrong.
     */
    public void uploadFileToS3Bucket(String s3BucketName, String fileName, String fileContent) throws IOException {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(loadCredentialsFromEnvironment())
                .withRegion(Regions.US_WEST_2)
                .build();

        s3Client.putObject(new PutObjectRequest(s3BucketName, fileName, createSampleFile(fileName,fileContent)));
    }

    /**
     * Creates a temporary file with text data to demonstrate uploading a file
     * to Amazon S3
     *
     * @return A newly created temporary file with text data.
     *
     * @throws IOException
     */
    private static File createSampleFile(String fileName, String fileContent) throws IOException {
        File file = File.createTempFile(fileName, ".txt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write(fileContent);
        writer.close();

        return file;
    }
    /**
     * It loads AWS access key id and secret access key from AWS environment in order
     * to have access to AWS S3 Bucket. Then it creates objects needed to make a AWS S3 upload.
     *
     * @return AWSStaticCredentialsProvider that contains information about AWS key access.
     */
    private AWSStaticCredentialsProvider loadCredentialsFromEnvironment(){
        String accessKey = System.getenv("aws_access_key_id");
        String secretKey = System.getenv("aws_secret_access_key");

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        return new AWSStaticCredentialsProvider(awsCreds);
    }
}
