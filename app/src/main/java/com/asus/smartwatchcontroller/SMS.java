package com.asus.smartwatchcontroller;

import com.asus.smartwatchcontroller.Exceptions.InvalidMessageFieldException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Monect on 25/11/2016.
 */


public class SMS implements HLContinuesMessage {
    private static final long serialVersionUID = 2286607776459077491L;
    private List<MessageAttributeInfo> AttrInfos;
    private final String FIELD_NAME;
    private final String FIELD_NAME_LENGTH;
    private final String FIELD_NUMBER;
    private final String FIELD_NUMBER_LENGTH;
    private final String FIELD_TEXT;
    private final String FIELD_TEXT_LENGTH;
    private String name;
    private int nameLength;
    private String number;
    private int numberLength;
    private String text;
    private int textLength;
    private Map<String, Object> valueMap;

    public SMS() {
        this.FIELD_TEXT_LENGTH = "textLength";
        this.FIELD_TEXT = "text";
        this.FIELD_NUMBER_LENGTH = "numberLength";
        this.FIELD_NUMBER = "number";
        this.FIELD_NAME_LENGTH = "nameLength";
        this.FIELD_NAME = "name";
        this.textLength = 0;
        this.numberLength = 0;
        this.nameLength = 0;
        this.text = "";
        this.name = "";
        this.number = "";
    }

    @Override
    public List<MessageAttributeInfo> getAttributeInfos() {
        if (this.AttrInfos == null) {
            (this.AttrInfos = new ArrayList<MessageAttributeInfo>()).add(new MessageAttributeInfo(AttrType.UTF8_LENGTH, "numberLength", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UTF8, "number", 0));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UTF8_LENGTH, "nameLength", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UTF8, "name", 0));
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
        this.valueMap.put("nameLength", this.nameLength);
        this.valueMap.put("name", this.name);
        this.valueMap.put("numberLength", this.numberLength);
        this.valueMap.put("number", this.number);
        return this.valueMap;
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public HLPackageConstants.TYPE_MESSAGE getType() {
        return HLPackageConstants.TYPE_MESSAGE.SMS;
    }

    @Override
    public void setAttributes(final Map<String, Object> map) throws InvalidMessageFieldException {
        this.setText((String) map.get("text"));
        this.setName((String) map.get("name"));
        this.setNumber((String) map.get("number"));
    }

    public void setName(final String name) {
        this.nameLength = DataFormatConverter.StringToUTF8ByteArray(name).length;
        this.name = name;
    }

    public void setNumber(final String number) {
        this.numberLength = DataFormatConverter.StringToUTF8ByteArray(number).length;
        this.number = number;
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
        final StringBuffer sb = new StringBuffer("SMS{");
        sb.append("textLength=").append(this.textLength);
        sb.append(", numberLength=").append(this.numberLength);
        sb.append(", nameLength=").append(this.nameLength);
        sb.append(", text='").append(this.text).append('\'');
        sb.append(", name='").append(this.name).append('\'');
        sb.append(", number='").append(this.number).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
