package com.asus.smartwatchcontroller;

import com.asus.smartwatchcontroller.Exceptions.InvalidMessageFieldException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Monect on 25/11/2016.
 */

public class Mail implements HLContinuesMessage {
    private static final long serialVersionUID = -3095755433626060091L;
    private List<MessageAttributeInfo> AttrInfos;
    private final String FIELD_SENDER;
    private final String FIELD_SENDER_LENGTH;
    private final String FIELD_TITLE;
    private final String FIELD_TITLE_LENGTH;
    private String name;
    private int nameLength;
    private String title;
    private int titleLength;
    private Map<String, Object> valueMap;

    public Mail() {
        this.FIELD_TITLE_LENGTH = "titleLength";
        this.FIELD_TITLE = "title";
        this.FIELD_SENDER_LENGTH = "nameLength";
        this.FIELD_SENDER = "name";
        this.titleLength = 0;
        this.nameLength = 0;
        this.title = "";
        this.name = "";
    }

    @Override
    public List<MessageAttributeInfo> getAttributeInfos() {
        if (this.AttrInfos == null) {
            (this.AttrInfos = new ArrayList<MessageAttributeInfo>()).add(new MessageAttributeInfo(AttrType.UTF8_LENGTH, "titleLength", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UTF8, "title", 0));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UTF8_LENGTH, "nameLength", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UTF8, "name", 0));
        }
        return this.AttrInfos;
    }

    @Override
    public Map<String, Object> getAttributes() {
        if (this.valueMap == null) {
            this.valueMap = new HashMap<String, Object>();
        }
        this.valueMap.put("titleLength", this.titleLength);
        this.valueMap.put("title", this.title);
        this.valueMap.put("nameLength", this.nameLength);
        this.valueMap.put("name", this.name);
        return this.valueMap;
    }

    public String getSender() {
        return this.name;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public HLPackageConstants.TYPE_MESSAGE getType() {
        return HLPackageConstants.TYPE_MESSAGE.MAIL;
    }

    @Override
    public void setAttributes(final Map<String, Object> map) throws InvalidMessageFieldException {
        this.setTitle((String) map.get("title"));
        this.setSender((String) map.get("name"));
    }

    public void setSender(final String name) {
        this.nameLength = DataFormatConverter.StringToUTF8ByteArray(name).length;
        this.name = name;
    }

    public void setTitle(final String s) {
        byte[] array = DataFormatConverter.StringToUTF8ByteArray(s);
        String string = s;
        if (s.length() > 80) {
            string = s.substring(0, 80) + "...";
            array = DataFormatConverter.StringToUTF8ByteArray(string);
        }
        this.titleLength = array.length;
        this.title = string;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Mail{");
        sb.append("titleLength=").append(this.titleLength);
        sb.append(", nameLength=").append(this.nameLength);
        sb.append(", title='").append(this.title).append('\'');
        sb.append(", name='").append(this.name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

