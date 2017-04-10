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


public class SpecialExerciseData implements HLImmediateResponseMessage {
    private static final long serialVersionUID = -1391770059668619943L;
    private List<MessageAttributeInfo> AttrInfos;
    private final String FIELD_EXERCISE_CAL;
    private final String FIELD_EXERCISE_COUNT;
    private final String FIELD_EXERCISE_DISTANCE;
    private final String FIELD_EXERCISE_TIME;
    private final String FIELD_HEART_AVERAGE;
    private final String FIELD_HEART_HIGHEST;
    private final String FIELD_HEART_LOWEST;
    private final String FIELD_HEART_ZONE1;
    private final String FIELD_HEART_ZONE2;
    private final String FIELD_HEART_ZONE3;
    private final String FIELD_HEART_ZONE4;
    private final String FIELD_MODE;
    private final String FIELD_TIMEDAY;
    private final String FIELD_TIMEMONTH;
    private final String FIELD_TIMEYEAR;
    private final int OFFSET_DAY;
    private final int OFFSET_YEAR;
    private int exerciseCount;
    private int exercise_cal;
    private int exercise_distance;
    private int exercise_time;
    private int heart_average;
    private int heart_highest;
    private int heart_lowest;
    private int heart_zone1;
    private int heart_zone2;
    private int heart_zone3;
    private int heart_zone4;
    private int mode;
    private Date timestamp;
    private Map<String, Object> valueMap;

    public SpecialExerciseData() {
        this.FIELD_TIMEDAY = "time_day";
        this.FIELD_TIMEMONTH = "time_month";
        this.FIELD_TIMEYEAR = "time_year";
        this.FIELD_EXERCISE_COUNT = "exerciseCount";
        this.FIELD_MODE = "mode";
        this.FIELD_HEART_AVERAGE = "heart_average";
        this.FIELD_HEART_LOWEST = "heart_lowest";
        this.FIELD_HEART_HIGHEST = "heart_highest";
        this.FIELD_HEART_ZONE1 = "heart_zone1";
        this.FIELD_HEART_ZONE2 = "heart_zone2";
        this.FIELD_HEART_ZONE3 = "heart_zone3";
        this.FIELD_HEART_ZONE4 = "heart_zone4";
        this.FIELD_EXERCISE_TIME = "exercise_time";
        this.FIELD_EXERCISE_DISTANCE = "exercise_distance";
        this.FIELD_EXERCISE_CAL = "exercise_cal";
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
        instance.set(n3 + 2014, n2, n + 1);
        instance.set(14, 0);
        instance.set(10, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        this.setTimestamp(instance.getTime());
    }

    @Override
    public List<MessageAttributeInfo> getAttributeInfos() {
        if (this.AttrInfos == null) {
            (this.AttrInfos = new ArrayList<MessageAttributeInfo>()).add(new MessageAttributeInfo(AttrType.RESERVED, "NON", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "time_day", 5));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "time_month", 4));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "time_year", 6));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "exerciseCount", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.RESERVED, "NON", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "mode", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "heart_average", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "heart_lowest", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "heart_highest", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "heart_zone1", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "heart_zone2", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "heart_zone3", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "heart_zone4", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "exercise_time", 16));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "exercise_distance", 16));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "exercise_cal", 16));
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
        this.valueMap.put("exerciseCount", this.exerciseCount);
        this.valueMap.put("mode", this.mode);
        this.valueMap.put("heart_average", this.heart_average);
        this.valueMap.put("heart_lowest", this.heart_lowest);
        this.valueMap.put("heart_highest", this.heart_highest);
        this.valueMap.put("heart_zone1", this.heart_zone1);
        this.valueMap.put("heart_zone2", this.heart_zone2);
        this.valueMap.put("heart_zone3", this.heart_zone3);
        this.valueMap.put("heart_zone4", this.heart_zone4);
        this.valueMap.put("exercise_time", this.exercise_time);
        this.valueMap.put("exercise_distance", this.exercise_distance);
        this.valueMap.put("exercise_cal", this.exercise_cal);
        return this.valueMap;
    }

    public int getExecise_cal() {
        return this.exercise_cal;
    }

    public int getExerciseCount() {
        return this.exerciseCount;
    }

    public int getExercise_distance() {
        return this.exercise_distance;
    }

    public int getExercise_time() {
        return this.exercise_time;
    }

    public int getHeart_average() {
        return this.heart_average;
    }

    public int getHeart_highest() {
        return this.heart_highest;
    }

    public int getHeart_lowest() {
        return this.heart_lowest;
    }

    public int getHeart_zone1() {
        return this.heart_zone1;
    }

    public int getHeart_zone2() {
        return this.heart_zone2;
    }

    public int getHeart_zone3() {
        return this.heart_zone3;
    }

    public int getHeart_zone4() {
        return this.heart_zone4;
    }

    public int getHeart_zone5() {
        return 100 - this.heart_zone1 - this.heart_zone2 - this.heart_zone3 - this.heart_zone4;
    }

    public int getMode() {
        return this.mode;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    @Override
    public HLPackageConstants.TYPE_MESSAGE getType() {
        return HLPackageConstants.TYPE_MESSAGE.EXERCISE_DATA;
    }

    @Override
    public void setAttributes(final Map<String, Object> map) throws InvalidMessageFieldException {
        this.setTimestamp((Integer) map.get("time_day"), (Integer) map.get("time_month"), (Integer) map.get("time_year"));
        this.setExerciseCount((Integer) map.get("exerciseCount"));
        this.setMode((Integer) map.get("mode"));
        this.setHeart_average((Integer) map.get("heart_average"));
        this.setHeart_lowest((Integer) map.get("heart_lowest"));
        this.setHeart_highest((Integer) map.get("heart_highest"));
        this.setHeart_zone1((Integer) map.get("heart_zone1"));
        this.setHeart_zone2((Integer) map.get("heart_zone2"));
        this.setHeart_zone3((Integer) map.get("heart_zone3"));
        this.setHeart_zone4((Integer) map.get("heart_zone4"));
        this.setExercise_time((Integer) map.get("exercise_time"));
        this.setExercise_distance((Integer) map.get("exercise_distance"));
        this.setExecise_cal((Integer) map.get("exercise_cal"));
    }

    public void setExecise_cal(final int exercise_cal) {
        this.exercise_cal = exercise_cal;
    }

    public void setExerciseCount(final int exerciseCount) throws InvalidMessageFieldException {
        if (exerciseCount != 1) {
            throw new InvalidMessageFieldException("exerciseCount", exerciseCount);
        }
        this.exerciseCount = exerciseCount;
    }

    public void setExercise_distance(final int exercise_distance) {
        this.exercise_distance = exercise_distance;
    }

    public void setExercise_time(final int exercise_time) {
        this.exercise_time = exercise_time;
    }

    public void setHeart_average(final int heart_average) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Heart(heart_average)) {
            throw new InvalidMessageFieldException("heart_average", heart_average);
        }
        this.heart_average = heart_average;
    }

    public void setHeart_highest(final int heart_highest) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Heart(heart_highest)) {
            throw new InvalidMessageFieldException("heart_highest", heart_highest);
        }
        this.heart_highest = heart_highest;
    }

    public void setHeart_lowest(final int heart_lowest) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Heart(heart_lowest)) {
            throw new InvalidMessageFieldException("heart_lowest", heart_lowest);
        }
        this.heart_lowest = heart_lowest;
    }

    public void setHeart_zone1(final int heart_zone1) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Persent(heart_zone1)) {
            throw new InvalidMessageFieldException("heart_zone1", heart_zone1);
        }
        this.heart_zone1 = heart_zone1;
    }

    public void setHeart_zone2(final int heart_zone2) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Persent(heart_zone2)) {
            throw new InvalidMessageFieldException("heart_zone2", heart_zone2);
        }
        this.heart_zone2 = heart_zone2;
    }

    public void setHeart_zone3(final int heart_zone3) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Persent(heart_zone3)) {
            throw new InvalidMessageFieldException("heart_zone3", heart_zone3);
        }
        this.heart_zone3 = heart_zone3;
    }

    public void setHeart_zone4(final int heart_zone4) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Persent(heart_zone4)) {
            throw new InvalidMessageFieldException("heart_zone4", heart_zone4);
        }
        this.heart_zone4 = heart_zone4;
    }

    public void setMode(final int mode) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Range(mode, 0, 3)) {
            throw new InvalidMessageFieldException("mode", mode);
        }
        this.mode = mode;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SpecialExerciseData{");
        sb.append("timestamp=").append(this.timestamp);
        sb.append(", exerciseCount=").append(this.exerciseCount);
        sb.append(", mode=").append(this.mode);
        sb.append(", heart_average=").append(this.heart_average);
        sb.append(", heart_lowest=").append(this.heart_lowest);
        sb.append(", heart_highest=").append(this.heart_highest);
        sb.append(", heart_zone1=").append(this.heart_zone1);
        sb.append(", heart_zone2=").append(this.heart_zone2);
        sb.append(", heart_zone3=").append(this.heart_zone3);
        sb.append(", heart_zone4=").append(this.heart_zone4);
        sb.append(", exercise_time=").append(this.exercise_time);
        sb.append(", exercise_distance=").append(this.exercise_distance);
        sb.append(", exercise_cal=").append(this.exercise_cal);
        sb.append('}');
        return sb.toString();
    }
}
