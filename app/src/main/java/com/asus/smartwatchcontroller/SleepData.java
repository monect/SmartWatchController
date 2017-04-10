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


public class SleepData implements HLImmediateResponseMessage {
    private static final long serialVersionUID = -5495512986396847352L;
    private List<MessageAttributeInfo> AttrInfos;
    private final String FIELD_SLEEP_QUALITY;
    private final String FIELD_TIMEDAY;
    private final String FIELD_TIMEMONTH;
    private final String FIELD_TIMEYEAR;
    private final String FIELD_TIME_BED;
    private final String FIELD_TIME_LATENCY;
    private final String FIELD_TIME_SNOOZE;
    private final String FIELD_TIME_START_HOUR;
    private final String FIELD_TIME_START_MINUTE;
    private final String FIELD_TIME_WAKE;
    private final String FIELD_WAKE_TIMES;
    private final int OFFSET_DAY;
    private final int OFFSET_YEAR;
    private int sleep_quality;
    private int time_bed;
    private int time_latency;
    private int time_snooze;
    private int time_start_hour;
    private int time_start_minute;
    private int time_wake;
    private Date timestamp;
    private Map<String, Object> valueMap;
    private int wake_times;

    public SleepData() {
        this.FIELD_TIMEDAY = "time_day";
        this.FIELD_TIMEMONTH = "time_month";
        this.FIELD_TIMEYEAR = "time_year";
        this.FIELD_TIME_START_MINUTE = "time_start_minute";
        this.FIELD_TIME_START_HOUR = "time_start_hour";
        this.FIELD_TIME_BED = "time_bed";
        this.FIELD_TIME_LATENCY = "time_latency";
        this.FIELD_TIME_WAKE = "time_wake";
        this.FIELD_TIME_SNOOZE = "time_snooze";
        this.FIELD_WAKE_TIMES = "wake_times";
        this.FIELD_SLEEP_QUALITY = "sleep_quality";
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
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "time_start_minute", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "time_start_hour", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "time_bed", 16));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "time_latency", 16));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "time_wake", 16));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "time_snooze", 16));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "wake_times", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "sleep_quality", 8));
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
        this.valueMap.put("time_start_minute", this.time_start_minute);
        this.valueMap.put("time_start_hour", this.time_start_hour);
        this.valueMap.put("time_bed", this.time_bed);
        this.valueMap.put("time_latency", this.time_latency);
        this.valueMap.put("time_wake", this.time_wake);
        this.valueMap.put("time_snooze", this.time_snooze);
        this.valueMap.put("wake_times", this.wake_times);
        this.valueMap.put("sleep_quality", this.sleep_quality);
        return this.valueMap;
    }

    public int getSleep_quality() {
        return this.sleep_quality;
    }

    public int getTime_bed() {
        return this.time_bed;
    }

    public int getTime_latency() {
        return this.time_latency;
    }

    public int getTime_snooze() {
        return this.time_snooze;
    }

    public int getTime_start_hour() {
        return this.time_start_hour;
    }

    public int getTime_start_minute() {
        return this.time_start_minute;
    }

    public int getTime_wake() {
        return this.time_wake;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    @Override
    public HLPackageConstants.TYPE_MESSAGE getType() {
        return HLPackageConstants.TYPE_MESSAGE.SLEEP_DATA;
    }

    public int getWake_times() {
        return this.wake_times;
    }

    @Override
    public void setAttributes(final Map<String, Object> map) throws InvalidMessageFieldException {
        this.setTimestamp((Integer) map.get("time_day"), (Integer) map.get("time_month"), (Integer) map.get("time_year"));
        this.setTime_start_minute((Integer) map.get("time_start_minute"));
        this.setTime_start_hour((Integer) map.get("time_start_hour"));
        this.setTime_bed((Integer) map.get("time_bed"));
        this.setTime_latency((Integer) map.get("time_latency"));
        this.setTime_wake((Integer) map.get("time_wake"));
        this.setTime_snooze((Integer) map.get("time_snooze"));
        this.setWake_times((Integer) map.get("wake_times"));
        this.setSleep_quality((Integer) map.get("sleep_quality"));
    }

    public void setSleep_quality(final int sleep_quality) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Persent(sleep_quality)) {
            throw new InvalidMessageFieldException("sleep_quality", sleep_quality);
        }
        this.sleep_quality = sleep_quality;
    }

    public void setTime_bed(final int time_bed) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Unsigned_Short(time_bed)) {
            throw new InvalidMessageFieldException("time_bed", time_bed);
        }
        this.time_bed = time_bed;
    }

    public void setTime_latency(final int time_latency) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Unsigned_Short(time_latency)) {
            throw new InvalidMessageFieldException("time_latency", time_latency);
        }
        this.time_latency = time_latency;
    }

    public void setTime_snooze(final int time_snooze) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Unsigned_Short(time_snooze)) {
            throw new InvalidMessageFieldException("time_snooze", time_snooze);
        }
        this.time_snooze = time_snooze;
    }

    public void setTime_start_hour(final int time_start_hour) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Time_Hour(time_start_hour)) {
            throw new InvalidMessageFieldException("time_start_hour", time_start_hour);
        }
        this.time_start_hour = time_start_hour;
    }

    public void setTime_start_minute(final int time_start_minute) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Time_Minute(time_start_minute)) {
            throw new InvalidMessageFieldException("time_start_minute", time_start_minute);
        }
        this.time_start_minute = time_start_minute;
    }

    public void setTime_wake(final int time_wake) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Unsigned_Short(time_wake)) {
            throw new InvalidMessageFieldException("time_wake", time_wake);
        }
        this.time_wake = time_wake;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setWake_times(final int wake_times) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Unsigned_Byte(wake_times)) {
            throw new InvalidMessageFieldException("wake_times", wake_times);
        }
        this.wake_times = wake_times;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SleepData{");
        sb.append("timestamp=").append(this.timestamp);
        sb.append(", time_start=").append(this.time_start_hour).append(":").append(this.time_start_minute);
        sb.append(", time_bed=").append(this.time_bed);
        sb.append(", time_latency=").append(this.time_latency);
        sb.append(", time_wake=").append(this.time_wake);
        sb.append(", time_snooze=").append(this.time_snooze);
        sb.append(", wake_times=").append(this.wake_times);
        sb.append(", sleep_quality=").append(this.sleep_quality);
        sb.append('}');
        return sb.toString();
    }
}
