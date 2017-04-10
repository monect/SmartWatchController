package com.asus.smartwatchcontroller.Exceptions;

/**
 * Created by Monect on 25/11/2016.
 */

public class MessageBuildException
        extends Exception {
    public MessageBuildException(String paramString) {
        super(paramString);
    }

    public MessageBuildException(String paramString, Exception paramException) {
        super(paramString, paramException);
    }
}
