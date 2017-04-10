package com.asus.smartwatchcontroller;

import java.io.Serializable;

/**
 * Created by Monect on 25/11/2016.
 */

public class MessageAttributeInfo
        implements Serializable {
    public static final String NO_SUCH_FIELD = "NON";
    private static final long serialVersionUID = -7655713672152364550L;
    private int bitCount;
    private String fieldName;
    private HLBluetoothMessage.AttrType type;

    public MessageAttributeInfo(HLBluetoothMessage.AttrType paramAttrType, String paramString, int paramInt) {
        this.type = paramAttrType;
        this.fieldName = paramString;
        this.bitCount = paramInt;
    }

    public int getBitCount() {
        return this.bitCount;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public HLBluetoothMessage.AttrType getType() {
        return this.type;
    }

    public String toString() {
        StringBuffer localStringBuffer = new StringBuffer("messageAttribute{");
        localStringBuffer.append("type=").append(this.type);
        localStringBuffer.append(", fieldName='").append(this.fieldName).append('\'');
        localStringBuffer.append(", bitCount=").append(this.bitCount);
        localStringBuffer.append('}');
        return localStringBuffer.toString();
    }
}
