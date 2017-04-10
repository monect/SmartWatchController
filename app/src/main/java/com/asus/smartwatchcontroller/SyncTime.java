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


public class SyncTime implements HLImmediateResponseMessage {
    private static final long serialVersionUID = 754709545773322519L;
    private List<MessageAttributeInfo> AttrInfos;
    private final String FIELD_TIMEDAY;
    private final String FIELD_TIMEHOUR;
    private final String FIELD_TIMEMINUTE;
    private final String FIELD_TIMEMONTH;
    private final String FIELD_TIMESECOND;
    private final String FIELD_TIMEYEAR;
    private final int OFFSET_DAY;
    private final int OFFSET_YEAR;
    private Date timestamp;
    private Map<String, Object> valueMap;

    public SyncTime() {
        this.FIELD_TIMESECOND = "time_second";
        this.FIELD_TIMEMINUTE = "time_minute";
        this.FIELD_TIMEHOUR = "time_hour";
        this.FIELD_TIMEDAY = "time_day";
        this.FIELD_TIMEMONTH = "time_month";
        this.FIELD_TIMEYEAR = "time_year";
        this.OFFSET_YEAR = 2014;
        this.OFFSET_DAY = 1;
    }

    private void setTimestamp(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Time_Second(n)) {
            throw new InvalidMessageFieldException("time_second", n);
        }
        if (AttributeRange.isInvalid_Time_Minute(n2)) {
            throw new InvalidMessageFieldException("time_minute", n2);
        }
        if (AttributeRange.isInvalid_Time_Hour(n3)) {
            throw new InvalidMessageFieldException("time_hour", n3);
        }
        if (AttributeRange.isInvalid_Time_Day(n4)) {
            throw new InvalidMessageFieldException("time_day", n4);
        }
        if (AttributeRange.isInvalid_Time_Month(n5)) {
            throw new InvalidMessageFieldException("time_month", n5);
        }
        if (AttributeRange.isInvalid_Time_Year(n6)) {
            throw new InvalidMessageFieldException("time_year", n6);
        }
        final Calendar instance = Calendar.getInstance();
        instance.set(n6 + 2014, n5, n4 + 1, n3, n2, n);
        instance.set(14, 0);
        this.timestamp = instance.getTime();
    }

    @Override
    public List<MessageAttributeInfo> getAttributeInfos() {
        if (this.AttrInfos == null) {
            (this.AttrInfos = new ArrayList<MessageAttributeInfo>()).add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "time_second", 6));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "time_minute", 6));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "time_hour", 5));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "time_day", 5));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "time_month", 4));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "time_year", 6));
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
        final int value = instance.get(13);
        final int value2 = instance.get(12);
        final int value3 = instance.get(11);
        final int value4 = instance.get(5);
        final int value5 = instance.get(2);
        final int value6 = instance.get(1);
        this.valueMap.put("time_second", value);
        this.valueMap.put("time_minute", value2);
        this.valueMap.put("time_hour", value3);
        this.valueMap.put("time_day", value4 - 1);
        this.valueMap.put("time_month", value5);
        this.valueMap.put("time_year", value6 - 2014);
        return this.valueMap;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    @Override
    public HLPackageConstants.TYPE_MESSAGE getType() {
        return HLPackageConstants.TYPE_MESSAGE.SYNC_TIME;
    }

    @Override
    public void setAttributes(final Map<String, Object> map) throws InvalidMessageFieldException {
        this.setTimestamp((Integer) map.get("time_second"), (Integer) map.get("time_minute"), (Integer) map.get("time_hour"), (Integer) map.get("time_day"), (Integer) map.get("time_month"), (Integer) map.get("time_year"));
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SyncTime{");
        sb.append("timestamp=").append(this.timestamp);
        sb.append('}');
        return sb.toString();
    }
}
