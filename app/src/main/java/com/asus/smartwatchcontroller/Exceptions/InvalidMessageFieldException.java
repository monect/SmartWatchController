package com.asus.smartwatchcontroller.Exceptions;

/**
 * Created by Monect on 25/11/2016.
 */

public class InvalidMessageFieldException
        extends MessageBuildException {
    public InvalidMessageFieldException(String paramString) {
        super(paramString);
    }

    public InvalidMessageFieldException(String paramString, float paramFloat) {
        super(buildInfo(paramString, paramFloat));
    }

    public InvalidMessageFieldException(String paramString, int paramInt) {
        super(buildInfo(paramString, paramInt));
    }

    private static String buildInfo(String paramString, float paramFloat) {
        StringBuilder localStringBuilder = new StringBuilder("field : ");
        localStringBuilder.append(paramString).append(" value : ").append(paramFloat);
        return localStringBuilder.toString();
    }

    private static String buildInfo(String paramString, int paramInt) {
        StringBuilder localStringBuilder = new StringBuilder("field : ");
        localStringBuilder.append(paramString).append(" value : ").append(paramInt);
        return localStringBuilder.toString();
    }
}
