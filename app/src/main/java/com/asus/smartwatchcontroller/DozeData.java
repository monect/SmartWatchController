package com.asus.smartwatchcontroller;

import com.asus.smartwatchcontroller.Exceptions.InvalidMessageFieldException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Monect on 25/11/2016.
 */


public class DozeData implements HLImmediateResponseMessage {
    public static final int MODE_ALARM = 2;
    public static final int MODE_HISTORY = 3;
    public static final int MODE_OFF = 0;
    public static final int MODE_ON = 1;
    private static final long serialVersionUID = 6721154748559394977L;
    private List<MessageAttributeInfo> AttrInfos;
    private final String FIELD_DOZE_CURRENT;
    private final String FIELD_DOZE_MODE;
    private final String FIELD_DOZE_TOTAL;
    private final String FIELD_TIMEDAY;
    private final String FIELD_TIMEMONTH;
    private final String FIELD_TIMEYEAR;
    private final int OFFSET_DAY;
    private final int OFFSET_YEAR;
    private int doze_current;
    private int doze_mode;
    private int doze_total;
    private Date timestamp;
    private Map<String, Object> valueMap;

    public DozeData() {
        this.FIELD_TIMEDAY = "time_day";
        this.FIELD_TIMEMONTH = "time_month";
        this.FIELD_TIMEYEAR = "time_year";
        this.FIELD_DOZE_CURRENT = "doze_current";
        this.FIELD_DOZE_TOTAL = "doze_total";
        this.FIELD_DOZE_MODE = "doze_mode";
        this.OFFSET_YEAR = 2014;
        this.OFFSET_DAY = 1;
    }

    private void setTimestamp(final int n, final int n2, final int n3) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Time_Day(n)) {
            throw new InvalidMessageFieldException("time_day", n);
        }
        if (AttributeRange.isInvalid_Time_Month(n2)) {
            throw new InvalidMessageFieldException("time_month", n2);
        }
        if (AttributeRange.isInvalid_Time_Year(n3)) {
            throw new InvalidMessageFieldException("time_year", n3);
        }
        final Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(0L);
        instance.set(n3 + 2014, n2, n + 1);
        this.timestamp = instance.getTime();
    }

    @Override
    public List<MessageAttributeInfo> getAttributeInfos() {
        if (this.AttrInfos == null) {
            (this.AttrInfos = new ArrayList<MessageAttributeInfo>()).add(new MessageAttributeInfo(AttrType.RESERVED, "NON", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "time_day", 5));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "time_month", 4));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "time_year", 6));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "doze_current", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "doze_total", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "doze_mode", 8));
        }
        return this.AttrInfos;
    }

    @Override
    public Map<String, Object> getAttributes() {
        if (this.valueMap == null) {
            this.valueMap = new HashMap<String, Object>();
        }
        final Calendar instance = Calendar.getInstance();
        instance.setTime(this.timestamp);
        final int value = instance.get(5);
        final int value2 = instance.get(2);
        final int value3 = instance.get(1);
        this.valueMap.put("time_day", value - 1);
        this.valueMap.put("time_month", value2);
        this.valueMap.put("time_year", value3 - 2014);
        this.valueMap.put("doze_current", this.doze_current);
        this.valueMap.put("doze_total", this.doze_total);
        this.valueMap.put("doze_mode", this.doze_mode);
        return this.valueMap;
    }

    public int getDoze_current() {
        return this.doze_current;
    }

    public int getDoze_mode() {
        return this.doze_mode;
    }

    public int getDoze_total() {
        return this.doze_total;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    @Override
    public HLPackageConstants.TYPE_MESSAGE getType() {
        return HLPackageConstants.TYPE_MESSAGE.DOZE_DATA;
    }

    @Override
    public void setAttributes(final Map<String, Object> map) throws InvalidMessageFieldException {
        this.setTimestamp((Integer) map.get("time_day"), (Integer) map.get("time_month"), (Integer) map.get("time_year"));
        this.setDoze_current((Integer) map.get("doze_current"));
        this.setDoze_total((Integer) map.get("doze_total"));
        this.setDoze_mode((Integer) map.get("doze_mode"));
    }

    public void setDoze_current(final int doze_current) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Unsigned_Byte(doze_current)) {
            throw new InvalidMessageFieldException("doze_current", doze_current);
        }
        this.doze_current = doze_current;
    }

    public void setDoze_mode(final int doze_mode) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Range(doze_mode, 0, 4)) {
            throw new InvalidMessageFieldException("doze_mode", doze_mode);
        }
        this.doze_mode = doze_mode;
    }

    public void setDoze_total(final int doze_total) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Unsigned_Byte(doze_total)) {
            throw new InvalidMessageFieldException("doze_total", doze_total);
        }
        this.doze_total = doze_total;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DozeData{");
        sb.append("timestamp=").append(this.timestamp);
        sb.append(", doze_current=").append(this.doze_current);
        sb.append(", doze_total=").append(this.doze_total);
        sb.append(", doze_mode=").append(this.doze_mode);
        sb.append('}');
        return sb.toString();
    }
}
