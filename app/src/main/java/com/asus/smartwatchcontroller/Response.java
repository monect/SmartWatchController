package com.asus.smartwatchcontroller;

import com.asus.smartwatchcontroller.Exceptions.InvalidMessageFieldException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Monect on 25/11/2016.
 */

public class Response implements HLImmediateResponseMessage {
    public static final int CODE_ACCEPT = -1;
    public static final int CODE_REJECT = 0;
    private static final long serialVersionUID = -5396333339197739052L;
    private List<MessageAttributeInfo> Attrs;
    private final String FIELD_CODE;
    private int code;
    private HLPackageConstants.TYPE_MESSAGE type;
    private Map<String, Object> valueMap;

    public Response(final HLPackageConstants.TYPE_MESSAGE type) {
        this.FIELD_CODE = "code";
        this.code = 2;
        this.type = type;
    }

    @Override
    public List<MessageAttributeInfo> getAttributeInfos() {
        if (this.Attrs == null) {
            (this.Attrs = new ArrayList<MessageAttributeInfo>()).add(new MessageAttributeInfo(AttrType.NULLABLE, "code", 8));
        }
        return this.Attrs;
    }

    @Override
    public Map<String, Object> getAttributes() {
        if (this.valueMap == null) {
            this.valueMap = new HashMap<String, Object>();
        }
        if (this.code == -1) {
            this.valueMap.put("code", new NullObject());
        } else {
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
        final Integer value = (Integer) map.get("code");
        if (value instanceof Integer) {
            if (value != 0) {
                throw new InvalidMessageFieldException("error code : " + value);
            }
            this.setCode(0);
        }
    }

    public void setCode(final int code) throws InvalidMessageFieldException {
        if (code == 0 || code == -1) {
            this.code = code;
            return;
        }
        throw new InvalidMessageFieldException("Wrong value for code : " + code);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Response{");
        String s = "";
        switch (this.code) {
            case 0: {
                s = "CODE_REJECT";
                break;
            }
            case -1: {
                s = "CODE_ACCEPT";
                break;
            }
        }
        sb.append("code=").append(s);
        sb.append('}');
        return sb.toString();
    }
}
