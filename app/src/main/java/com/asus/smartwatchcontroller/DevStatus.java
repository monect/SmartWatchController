package com.asus.smartwatchcontroller;

import com.asus.smartwatchcontroller.Exceptions.InvalidMessageFieldException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Monect on 25/11/2016.
 */


public class DevStatus implements HLImmediateResponseMessage {
    public static final int BATTERY_CHARGE_FULL = 7;
    public static final int BATTERY_CHARGE_HIGH = 6;
    public static final int BATTERY_CHARGE_LOW = 4;
    public static final int BATTERY_CHARGE_MEDIUM = 5;
    public static final int BATTERY_ERROR = -1;
    public static final int BATTERY_FULL = 3;
    public static final int BATTERY_HIGH = 2;
    public static final int BATTERY_LOW = 0;
    public static final int BATTERY_MEDIUM = 1;
    private static final long serialVersionUID = -4610300533483543689L;
    private List<MessageAttributeInfo> AttrInfos;
    private final String FIELD_BATTERY;
    private int battery;
    private Map<String, Object> valueMap;

    public DevStatus() {
        this.FIELD_BATTERY = "battery";
        this.battery = -1;
    }

    @Override
    public List<MessageAttributeInfo> getAttributeInfos() {
        if (this.AttrInfos == null) {
            (this.AttrInfos = new ArrayList<MessageAttributeInfo>()).add(new MessageAttributeInfo(AttrType.SIGNED_INT, "battery", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.RESERVED, "NON", 8));
        }
        return this.AttrInfos;
    }

    @Override
    public Map<String, Object> getAttributes() {
        if (this.valueMap == null) {
            this.valueMap = new HashMap<String, Object>();
        }
        this.valueMap.put("battery", this.battery);
        return this.valueMap;
    }

    public int getBattery() {
        return this.battery;
    }

    @Override
    public HLPackageConstants.TYPE_MESSAGE getType() {
        return HLPackageConstants.TYPE_MESSAGE.DEV_STATUS;
    }

    @Override
    public void setAttributes(final Map<String, Object> map) throws InvalidMessageFieldException {
        this.setBattery((Integer) map.get("battery"));
    }

    public void setBattery(final int battery) throws InvalidMessageFieldException {
        if (battery > 7 || battery < 0) {
            throw new InvalidMessageFieldException("battery", battery);
        }
        this.battery = battery;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DevStatus{");
        String s = "";
        switch (this.battery) {
            case 0: {
                s = "BATTERY_LOW";
                break;
            }
            case 1: {
                s = "BATTERY_MEDIUM";
                break;
            }
            case 2: {
                s = "BATTERY_HIGH";
                break;
            }
            case 3: {
                s = "BATTERY_FULL";
                break;
            }
            case 4: {
                s = "BATTERY_CHARGE_LOW";
                break;
            }
            case 5: {
                s = "BATTERY_CHARGE_MEDIUM";
                break;
            }
            case 6: {
                s = "BATTERY_CHARGE_HIGH";
                break;
            }
            case 7: {
                s = "BATTERY_CHARGE_FULL";
                break;
            }
        }
        sb.append("battery=").append(s);
        sb.append('}');
        return sb.toString();
    }
}
