package com.asus.smartwatchcontroller;

import com.asus.smartwatchcontroller.Exceptions.InvalidMessageFieldException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Monect on 25/11/2016.
 */


public class Request implements HLMediateResponseMessage
{
    public static final int CODE_NULL = -1;
    public static final int CODE_REJECT = 0;
    public static final int CODE_REQUEST = 1;
    private static final long serialVersionUID = 2175203900752322544L;
    private final String FIELD_CODE;
    private int code;
    private HLPackageConstants.TYPE_MESSAGE type;
    private Map<String, Object> valueMap;

    public Request(final HLPackageConstants.TYPE_MESSAGE type) {
        this.FIELD_CODE = "code";
        this.code = 0;
        this.type = type;
    }

    @Override
    public List<MessageAttributeInfo> getAttributeInfos() {
        final ArrayList<MessageAttributeInfo> list = new ArrayList<MessageAttributeInfo>();
        list.add(new MessageAttributeInfo(HLBluetoothMessage.AttrType.NULLABLE, "code", 8));
        return list;
    }

    @Override
    public Map<String, Object> getAttributes() {
        if (this.valueMap == null) {
            this.valueMap = new HashMap<String, Object>();
        }
        if (this.code == -1) {
            this.valueMap.put("code", new NullObject());
        }
        else {
            this.valueMap.put("code", this.code);
        }
        return this.valueMap;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public HLPackageConstants.TYPE_MESSAGE getType() {
        return this.type;
    }

    public void reset(final HLPackageConstants.TYPE_MESSAGE type) {
        this.type = type;
    }

    @Override
    public void setAttributes(final Map<String, Object> map) throws InvalidMessageFieldException {
        if (map.get("code") instanceof NullObject) {
            this.setCode(-1);
            return;
        }
        this.setCode((Integer) map.get("code"));
    }

    public void setCode(final int code) throws InvalidMessageFieldException {
        if (code == 1 || code == -1 || code == 0) {
            this.code = code;
            return;
        }
        throw new InvalidMessageFieldException("Wrong value for code : " + code);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Request{");
        String s = "";
        switch (this.code) {
            case -1: {
                s = "CODE_NULL";
                break;
            }
            case 1: {
                s = "CODE_REQUEST";
                break;
            }
            case 0: {
                s = "CODE_REJECT";
                break;
            }
        }
        sb.append("code=").append(s);
        sb.append(", type=").append(this.type);
        sb.append('}');
        return sb.toString();
    }
}
