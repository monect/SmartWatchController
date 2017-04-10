package com.asus.smartwatchcontroller;

import com.asus.smartwatchcontroller.Exceptions.InvalidMessageFieldException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Monect on 25/11/2016.
 */

public class Notification implements HLContinuesMessage {
    private static final long serialVersionUID = -1169875854425994815L;
    private List<MessageAttributeInfo> AttrInfos;
    private final String FIELD_NAME;
    private final String FIELD_NAME_LENGTH;
    private final String FIELD_TEXT;
    private final String FIELD_TEXT_LENGTH;
    private String appName;
    private int appNameLength;
    private String text;
    private int textLength;
    private Map<String, Object> valueMap;

    public Notification() {
        this.FIELD_TEXT_LENGTH = "textLength";
        this.FIELD_TEXT = "text";
        this.FIELD_NAME_LENGTH = "appNameLength";
        this.FIELD_NAME = "appName";
        this.textLength = 0;
        this.appNameLength = 0;
        this.text = "";
        this.appName = "";
    }

    public String getAppName() {
        return this.appName;
    }

    @Override
    public List<MessageAttributeInfo> getAttributeInfos() {
        if (this.AttrInfos == null) {
            (this.AttrInfos = new ArrayList<MessageAttributeInfo>()).add(new MessageAttributeInfo(AttrType.UTF8_LENGTH, "appNameLength", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UTF8, "appName", 0));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UTF8_LENGTH, "textLength", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UTF8, "text", 0));
        }
        return this.AttrInfos;
    }

    @Override
    public Map<String, Object> getAttributes() {
        if (this.valueMap == null) {
            this.valueMap = new HashMap<String, Object>();
        }
        this.valueMap.put("textLength", this.textLength);
        this.valueMap.put("text", this.text);
        this.valueMap.put("appNameLength", this.appNameLength);
        this.valueMap.put("appName", this.appName);
        return this.valueMap;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public HLPackageConstants.TYPE_MESSAGE getType() {
        return HLPackageConstants.TYPE_MESSAGE.NOTIFICATION;
    }

    public void setAppName(final String appName) {
        this.appNameLength = DataFormatConverter.StringToUTF8ByteArray(appName).length;
        this.appName = appName;
    }

    @Override
    public void setAttributes(final Map<String, Object> map) throws InvalidMessageFieldException {
        this.setText((String) map.get("text"));
        this.setAppName((String) map.get("appName"));
    }

    public void setText(final String s) {
        byte[] array = DataFormatConverter.StringToUTF8ByteArray(s);
        String string = s;
        if (s.length() > 80) {
            string = s.substring(0, 80) + "...";
            array = DataFormatConverter.StringToUTF8ByteArray(string);
        }
        this.textLength = array.length;
        this.text = string;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Notification{");
        sb.append("textLength=").append(this.textLength);
        sb.append(", appNameLength=").append(this.appNameLength);
        sb.append(", text='").append(this.text).append('\'');
        sb.append(", appName='").append(this.appName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
