package com.asus.smartwatchcontroller;

import com.asus.smartwatchcontroller.Exceptions.InvalidMessageFieldException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Monect on 25/11/2016.
 */


public class Phone implements HLContinuesMessage {
    private static final long serialVersionUID = -2897403449283368385L;
    private List<MessageAttributeInfo> AttrInfos;
    private final String FIELD_NAME;
    private final String FIELD_NAME_LENGTH;
    private final String FIELD_NUMBER;
    private final String FIELD_NUMBER_LENGTH;
    private String name;
    private int nameLength;
    private String number;
    private int numberLength;
    private Map<String, Object> valueMap;

    public Phone() {
        this.FIELD_NUMBER_LENGTH = "numberLength";
        this.FIELD_NUMBER = "number";
        this.FIELD_NAME_LENGTH = "nameLength";
        this.FIELD_NAME = "name";
        this.numberLength = 0;
        this.nameLength = 0;
        this.number = "";
        this.name = "";
    }

    @Override
    public List<MessageAttributeInfo> getAttributeInfos() {
        if (this.AttrInfos == null) {
            (this.AttrInfos = new ArrayList<MessageAttributeInfo>()).add(new MessageAttributeInfo(AttrType.UTF8_LENGTH, "numberLength", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UTF8, "number", 0));
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
        this.valueMap.put("numberLength", this.numberLength);
        this.valueMap.put("number", this.number);
        this.valueMap.put("nameLength", this.nameLength);
        this.valueMap.put("name", this.name);
        return this.valueMap;
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    @Override
    public HLPackageConstants.TYPE_MESSAGE getType() {
        return HLPackageConstants.TYPE_MESSAGE.PHONE;
    }

    @Override
    public void setAttributes(final Map<String, Object> map) throws InvalidMessageFieldException {
        this.setNumber((String) map.get("number"));
        this.setName((String) map.get("name"));
    }

    public void setName(final String name) {
        this.nameLength = DataFormatConverter.StringToUTF8ByteArray(name).length;
        this.name = name;
    }

    public void setNumber(final String number) {
        this.numberLength = DataFormatConverter.StringToUTF8ByteArray(number).length;
        this.number = number;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Phone{");
        sb.append("numberLength=").append(this.numberLength);
        sb.append(", nameLength=").append(this.nameLength);
        sb.append(", number='").append(this.number).append('\'');
        sb.append(", name='").append(this.name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
