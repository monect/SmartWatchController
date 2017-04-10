package com.asus.smartwatchcontroller;

import com.asus.smartwatchcontroller.Exceptions.InvalidMessageFieldException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Monect on 25/11/2016.
 */

public interface HLBluetoothMessage extends Serializable {
    List<MessageAttributeInfo> getAttributeInfos();

    Map<String, Object> getAttributes();

    HLPackageConstants.TYPE_MESSAGE getType();

    void setAttributes(final Map<String, Object> p0) throws InvalidMessageFieldException;

    public enum AttrType {
        FLAG,
        NULLABLE,
        RESERVED,
        SIGNED_INT,
        UNSIGNED_INT,
        UTF8,
        UTF8_LENGTH;
    }
}
