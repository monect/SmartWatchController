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

public class CommonData implements HLImmediateResponseMessage {
    private static final long serialVersionUID = -8547743849143414510L;
    private List<MessageAttributeInfo> AttrInfos;
    private final String FIELD_FLAG_HEART_AVERAGE;
    private final String FIELD_FLAG_HEART_HIGHEST;
    private final String FIELD_FLAG_HEART_LOWEST;
    private final String FIELD_FLAG_HEART_NORMAL;
    private final String FIELD_FLAG_HEART_REALTIME;
    private final String FIELD_FLAG_TOTAL_CALS;
    private final String FIELD_FLAG_TOTAL_DISTANCE;
    private final String FIELD_FLAG_TOTAL_STEPS;
    private final String FIELD_HEART_AVERAGE;
    private final String FIELD_HEART_HIGHEST;
    private final String FIELD_HEART_LOWEST;
    private final String FIELD_HEART_NORMAL;
    private final String FIELD_HEART_REALTIME;
    public final int FIELD_INVALID;
    private final String FIELD_TIMEDAY;
    private final String FIELD_TIMEMONTH;
    private final String FIELD_TIMEYEAR;
    private final String FIELD_TOTAL_CALS;
    private final String FIELD_TOTAL_DISTANCE;
    private final String FIELD_TOTAL_STEPS;
    private final int FLAG_COUNT;
    private final int INDEX_FLAG_HEART_AVERAGE;
    private final int INDEX_FLAG_HEART_HIGHEST;
    private final int INDEX_FLAG_HEART_LOWEST;
    private final int INDEX_FLAG_HEART_NORMAL;
    private final int INDEX_FLAG_HEART_REALTIME;
    private final int INDEX_FLAG_TOTAL_CALS;
    private final int INDEX_FLAG_TOTAL_DISTANCE;
    private final int INDEX_FLAG_TOTAL_STEPS;
    private final int OFFSET_DAY;
    private final int OFFSET_YEAR;
    private boolean[] flags;
    private int heart_average;
    private int heart_highest;
    private int heart_lowest;
    private int heart_normal;
    private int heart_realtime;
    private Date timestamp;
    private int total_cals;
    private int total_distance;
    private int total_steps;
    private Map<String, Object> valueMap;

    public CommonData() {
        this.FIELD_INVALID = 0;
        this.FIELD_TIMEDAY = "time_day";
        this.FIELD_TIMEMONTH = "time_month";
        this.FIELD_TIMEYEAR = "time_year";
        this.FIELD_HEART_NORMAL = "heart_normal";
        this.FIELD_HEART_AVERAGE = "heart_average";
        this.FIELD_HEART_LOWEST = "heart_lowest";
        this.FIELD_HEART_HIGHEST = "heart_highest";
        this.FIELD_TOTAL_STEPS = "total_steps";
        this.FIELD_TOTAL_CALS = "total_cals";
        this.FIELD_TOTAL_DISTANCE = "total_distance";
        this.FIELD_HEART_REALTIME = "heart_realtime";
        this.FIELD_FLAG_HEART_NORMAL = "flag_heart_normal";
        this.FIELD_FLAG_HEART_AVERAGE = "flag_heart_average";
        this.FIELD_FLAG_HEART_LOWEST = "flag_heart_lowest";
        this.FIELD_FLAG_HEART_HIGHEST = "flag_heart_highest";
        this.FIELD_FLAG_TOTAL_STEPS = "flag_total_steps";
        this.FIELD_FLAG_TOTAL_CALS = "flag_total_cals";
        this.FIELD_FLAG_TOTAL_DISTANCE = "flag_total_distance";
        this.FIELD_FLAG_HEART_REALTIME = "flag_heart_realtime";
        this.OFFSET_YEAR = 2014;
        this.OFFSET_DAY = 1;
        this.FLAG_COUNT = 8;
        this.INDEX_FLAG_HEART_NORMAL = 0;
        this.INDEX_FLAG_HEART_AVERAGE = 1;
        this.INDEX_FLAG_HEART_LOWEST = 2;
        this.INDEX_FLAG_HEART_HIGHEST = 3;
        this.INDEX_FLAG_TOTAL_STEPS = 4;
        this.INDEX_FLAG_TOTAL_CALS = 5;
        this.INDEX_FLAG_TOTAL_DISTANCE = 6;
        this.INDEX_FLAG_HEART_REALTIME = 7;
        this.flags = new boolean[8];
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
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "heart_normal", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "heart_average", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "heart_lowest", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "heart_highest", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "total_steps", 16));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "total_cals", 16));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "total_distance", 16));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.FLAG, "flag_heart_normal", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.FLAG, "flag_heart_average", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.FLAG, "flag_heart_lowest", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.FLAG, "flag_heart_highest", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.FLAG, "flag_total_steps", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.FLAG, "flag_total_cals", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.FLAG, "flag_total_distance", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.FLAG, "flag_heart_realtime", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "heart_realtime", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.RESERVED, "NON", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.RESERVED, "NON", 8));
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
        this.valueMap.put("heart_normal", this.heart_normal);
        this.valueMap.put("heart_average", this.heart_average);
        this.valueMap.put("heart_lowest", this.heart_lowest);
        this.valueMap.put("heart_highest", this.heart_highest);
        this.valueMap.put("total_steps", this.total_steps);
        this.valueMap.put("total_cals", this.total_cals);
        this.valueMap.put("total_distance", this.total_distance);
        this.valueMap.put("flag_heart_normal", this.flags[0]);
        this.valueMap.put("flag_heart_average", this.flags[1]);
        this.valueMap.put("flag_heart_lowest", this.flags[2]);
        this.valueMap.put("flag_heart_highest", this.flags[3]);
        this.valueMap.put("flag_total_steps", this.flags[4]);
        this.valueMap.put("flag_total_cals", this.flags[5]);
        this.valueMap.put("flag_total_distance", this.flags[6]);
        this.valueMap.put("flag_heart_realtime", this.flags[7]);
        this.valueMap.put("heart_realtime", this.heart_realtime);
        return this.valueMap;
    }

    public int getHeart_average() {
        if (this.flags[1]) {
            return this.heart_average;
        }
        return 0;
    }

    public int getHeart_highest() {
        if (this.flags[3]) {
            return this.heart_highest;
        }
        return 0;
    }

    public int getHeart_lowest() {
        if (this.flags[2]) {
            return this.heart_lowest;
        }
        return 0;
    }

    public int getHeart_normal() {
        int heart_normal = 0;
        if (this.flags[0]) {
            heart_normal = this.heart_normal;
        }
        return heart_normal;
    }

    public int getHeart_realtime() {
        if (this.flags[7]) {
            return this.heart_realtime;
        }
        return 0;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public int getTotal_cals() {
        if (this.flags[5]) {
            return this.total_cals;
        }
        return 0;
    }

    public int getTotal_distance() {
        if (this.flags[6]) {
            return this.total_distance;
        }
        return 0;
    }

    public int getTotal_steps() {
        if (this.flags[4]) {
            return this.total_steps;
        }
        return 0;
    }

    @Override
    public HLPackageConstants.TYPE_MESSAGE getType() {
        return HLPackageConstants.TYPE_MESSAGE.COMMON_DATA;
    }

    @Override
    public void setAttributes(final Map<String, Object> map) throws InvalidMessageFieldException {
        this.setTimestamp((Integer) map.get("time_day"), (Integer) map.get("time_month"), (Integer) map.get("time_year"));
        this.setHeart_average((Integer) map.get("heart_average"));
        this.setHeart_normal((Integer) map.get("heart_normal"));
        this.setHeart_highest((Integer) map.get("heart_highest"));
        this.setHeart_lowest((Integer) map.get("heart_lowest"));
        this.setTotal_cals((Integer) map.get("total_cals"));
        this.setTotal_distance((Integer) map.get("total_distance"));
        this.setTotal_steps((Integer) map.get("total_steps"));
        this.setFlag(1, (Boolean) (Object) map.get("flag_heart_average"));
        this.setFlag(0, (Boolean) (Object) map.get("flag_heart_normal"));
        this.setFlag(3, (Boolean) (Object) map.get("flag_heart_highest"));
        this.setFlag(2, (Boolean) (Object) map.get("flag_heart_lowest"));
        this.setFlag(5, (Boolean) (Object) map.get("flag_total_cals"));
        this.setFlag(6, (Boolean) (Object) map.get("flag_total_distance"));
        this.setFlag(4, (Boolean) (Object) map.get("flag_total_steps"));
        this.setFlag(7, (Boolean) (Object) map.get("flag_heart_realtime"));
        this.setHeart_realtime((Integer) map.get("heart_realtime"));
    }

    public void setFlag(final int n, final boolean b) {
        this.flags[n] = b;
    }

    public void setHeart_average(final int heart_average) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Heart(heart_average)) {
            throw new InvalidMessageFieldException("heart_average", heart_average);
        }
        this.heart_average = heart_average;
        this.flags[1] = true;
    }

    public void setHeart_highest(final int heart_highest) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Heart(heart_highest)) {
            throw new InvalidMessageFieldException("heart_highest", heart_highest);
        }
        this.heart_highest = heart_highest;
        this.flags[3] = true;
    }

    public void setHeart_lowest(final int heart_lowest) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Heart(heart_lowest)) {
            throw new InvalidMessageFieldException("heart_lowest", heart_lowest);
        }
        this.heart_lowest = heart_lowest;
        this.flags[2] = true;
    }

    public void setHeart_normal(final int heart_normal) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Heart(heart_normal)) {
            throw new InvalidMessageFieldException("heart_normal", heart_normal);
        }
        this.heart_normal = heart_normal;
        this.flags[0] = true;
    }

    public void setHeart_realtime(final int heart_realtime) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Heart(heart_realtime)) {
            throw new InvalidMessageFieldException("heart_highest", heart_realtime);
        }
        this.heart_realtime = heart_realtime;
        this.flags[7] = true;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setTotal_cals(final int total_cals) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Unsigned_Short(total_cals)) {
            throw new InvalidMessageFieldException("total_cals", total_cals);
        }
        this.total_cals = total_cals;
        this.flags[5] = true;
    }

    public void setTotal_distance(final int total_distance) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Unsigned_Short(total_distance)) {
            throw new InvalidMessageFieldException("total_distance", total_distance);
        }
        this.total_distance = total_distance;
        this.flags[6] = true;
    }

    public void setTotal_steps(final int total_steps) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Unsigned_Short(total_steps)) {
            throw new InvalidMessageFieldException("total_steps", total_steps);
        }
        this.total_steps = total_steps;
        this.flags[4] = true;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CommonData{");
        sb.append("timestamp=").append(this.timestamp);
        sb.append(", heart_normal=").append(this.heart_normal);
        sb.append(", heart_average=").append(this.heart_average);
        sb.append(", heart_lowest=").append(this.heart_lowest);
        sb.append(", heart_highest=").append(this.heart_highest);
        sb.append(", total_steps=").append(this.total_steps);
        sb.append(", total_cals=").append(this.total_cals);
        sb.append(", total_distance=").append(this.total_distance);
        sb.append(", flags=");
        if (this.flags == null) {
            sb.append("null");
        } else {
            sb.append('[');
            for (int i = 0; i < this.flags.length; ++i) {
                String s;
                if (i == 0) {
                    s = "";
                } else {
                    s = ", ";
                }
                sb.append(s).append((new String[]{"HEART_NORMAL", "HEART_AVERAGE", "HEART_LOWEST", "HEART_HIGHEST", "TOTAL_STEPS", "TOTAL_CALS", "TOTAL_DISTANCE", "HEART_REALTIME"})[i]).append(":").append(this.flags[i]);
            }
            sb.append(']');
        }
        sb.append(", heart_realtime=").append(this.heart_realtime);
        sb.append('}');
        return sb.toString();
    }
}
