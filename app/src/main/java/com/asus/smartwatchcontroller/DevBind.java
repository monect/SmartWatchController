package com.asus.smartwatchcontroller;

import com.asus.smartwatchcontroller.Exceptions.InvalidMessageFieldException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Monect on 25/11/2016.
 */


public class DevBind implements HLMediateResponseMessage {
    private static final int BIND_ACCEPT = 1;
    private static final long serialVersionUID = -6376695985340724256L;
    private List<MessageAttributeInfo> AttrInfos;
    private final String FIELD_BIND;
    private final String FIELD_MAC_1;
    private final String FIELD_MAC_2;
    private final String FIELD_MAC_3;
    private final String FIELD_MAC_4;
    private final String FIELD_MAC_5;
    private final String FIELD_MAC_6;
    private String MAC;
    private final int bind;
    private int mac_1;
    private int mac_2;
    private int mac_3;
    private int mac_4;
    private int mac_5;
    private int mac_6;
    private Map<String, Object> valueMap;

    public DevBind() {
        this.FIELD_BIND = "bind";
        this.FIELD_MAC_1 = "mac_1";
        this.FIELD_MAC_2 = "mac_2";
        this.FIELD_MAC_3 = "mac_3";
        this.FIELD_MAC_4 = "mac_4";
        this.FIELD_MAC_5 = "mac_5";
        this.FIELD_MAC_6 = "mac_6";
        this.bind = 1;
    }

    private void setMac_1(final int mac_1) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Unsigned_Short(mac_1)) {
            throw new InvalidMessageFieldException("mac_1", mac_1);
        }
        this.mac_1 = mac_1;
    }

    private void setMac_2(final int mac_2) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Unsigned_Short(mac_2)) {
            throw new InvalidMessageFieldException("mac_2", mac_2);
        }
        this.mac_2 = mac_2;
    }

    private void setMac_3(final int mac_3) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Unsigned_Short(mac_3)) {
            throw new InvalidMessageFieldException("mac_3", mac_3);
        }
        this.mac_3 = mac_3;
    }

    private void setMac_4(final int mac_4) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Unsigned_Short(mac_4)) {
            throw new InvalidMessageFieldException("mac_4", mac_4);
        }
        this.mac_4 = mac_4;
    }

    private void setMac_5(final int mac_5) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Unsigned_Short(mac_5)) {
            throw new InvalidMessageFieldException("mac_5", mac_5);
        }
        this.mac_5 = mac_5;
    }

    private void setMac_6(final int mac_6) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Unsigned_Short(mac_6)) {
            throw new InvalidMessageFieldException("mac_6", mac_6);
        }
        this.mac_6 = mac_6;
    }

    @Override
    public List<MessageAttributeInfo> getAttributeInfos() {
        if (this.AttrInfos == null) {
            (this.AttrInfos = new ArrayList<MessageAttributeInfo>()).add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "bind", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "mac_1", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "mac_2", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "mac_3", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "mac_4", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "mac_5", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "mac_6", 8));
        }
        return this.AttrInfos;
    }

    @Override
    public Map<String, Object> getAttributes() {
        if (this.valueMap == null) {
            this.valueMap = new HashMap<String, Object>();
        }
        this.valueMap.put("bind", 1);
        this.valueMap.put("mac_1", this.mac_1);
        this.valueMap.put("mac_2", this.mac_2);
        this.valueMap.put("mac_3", this.mac_3);
        this.valueMap.put("mac_4", this.mac_4);
        this.valueMap.put("mac_5", this.mac_5);
        this.valueMap.put("mac_6", this.mac_6);
        return this.valueMap;
    }

    public String getMAC() {
        return this.MAC;
    }

    public int getMac_1() {
        return this.mac_1;
    }

    public int getMac_2() {
        return this.mac_2;
    }

    public int getMac_3() {
        return this.mac_3;
    }

    public int getMac_4() {
        return this.mac_4;
    }

    public int getMac_5() {
        return this.mac_5;
    }

    public int getMac_6() {
        return this.mac_6;
    }

    @Override
    public HLPackageConstants.TYPE_MESSAGE getType() {
        return HLPackageConstants.TYPE_MESSAGE.DEV_BIND;
    }

    @Override
    public void setAttributes(final Map<String, Object> map) throws InvalidMessageFieldException {
        this.setMac_1((Integer) map.get("mac_1"));
        this.setMac_2((Integer) map.get("mac_2"));
        this.setMac_3((Integer) map.get("mac_3"));
        this.setMac_4((Integer) map.get("mac_4"));
        this.setMac_5((Integer) map.get("mac_5"));
        this.setMac_6((Integer) map.get("mac_6"));
        this.MAC = this.mac_1 + ":" + this.mac_2 + ":" + this.mac_3 + ":" + this.mac_4 + ":" + this.mac_5 + ":" + this.mac_6;
    }

    public void setMAC(final String mac) throws InvalidMessageFieldException {
        final String[] split = mac.split(":");
        if (split.length != 6) {
            throw new InvalidMessageFieldException("wrong MAC format : " + mac);
        }
        this.MAC = mac;
        this.setMac_1(Integer.parseInt(split[0], 16));
        this.setMac_2(Integer.parseInt(split[1], 16));
        this.setMac_3(Integer.parseInt(split[2], 16));
        this.setMac_4(Integer.parseInt(split[3], 16));
        this.setMac_5(Integer.parseInt(split[4], 16));
        this.setMac_6(Integer.parseInt(split[5], 16));
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DevBind{");
        sb.append("MAC='").append(this.MAC).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
