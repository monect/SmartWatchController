package com.asus.smartwatchcontroller.Exceptions;

/**
 * Created by Monect on 25/11/2016.
 */

public class PackageParserException
        extends MessageBuildException
{
    public PackageParserException(String paramString)
    {
        super(paramString);
    }

    public PackageParserException(String paramString, Exception paramException)
    {
        super(paramString, paramException);
    }
}