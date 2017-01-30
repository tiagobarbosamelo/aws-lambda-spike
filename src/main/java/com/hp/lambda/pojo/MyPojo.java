/**
 * (C) Copyright 2013-2017 HP Development Company, L.P.
 * Confidential computer software. Valid license from HP required for possession, use or copying.
 * Consistent with FAR 12.211 and 12.212, Commercial Computer Software,
 * Computer Software Documentation, and Technical Data for Commercial Items are licensed
 * to the U.S. Government under vendor's standard commercial license.
 */
package com.hp.lambda.pojo;

/**
 * Custom POJO as input data of a Lambda function
 */
public class MyPojo {

    /**
     * Forced timeout of handler method.
     */
    private Integer timeout;

    /**
     * Forced exception on handler method.
     */
    private Boolean throwException;

    /**
     * Random failure on handler method. This is done with purpose of retry tests.
     */
    private Boolean randomFailure;

    /**
     * Getter for timeout field
     *
     * @return timeout in milliseconds
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * Setter for timeout field
     *
     * @param timeout timeout in milliseconds
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    /**
     * Getter for throwException field
     *
     * @return true if lambda function that deals with MyPojo should throw a exception. False otherwise.
     */
    public Boolean getThrowException() {
        return throwException;
    }

    /**
     * Setter for throwException field
     *
     * @param throwException boolean value of throwException field
     */
    public void setThrowException(Boolean throwException) {
        this.throwException = throwException;
    }

    /**
     * Getter for randomFailure field
     *
     * @return True if handler method should throws failure in a random way. False otherwise.
     */
    public Boolean getRandomFailure() {
        return randomFailure;
    }

    /**
     * Setter for randomFailure field.
     *
     * @param randomFailure True if handler method should throws failure in a random way. False otherwise.
     */
    public void setRandomFailure(Boolean randomFailure) {
        this.randomFailure = randomFailure;
    }
}
