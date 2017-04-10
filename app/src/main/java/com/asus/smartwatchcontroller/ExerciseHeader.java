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


public class ExerciseHeader implements HLSeparateMessage {
    private static final long serialVersionUID = 745485433222904800L;
    private List<MessageAttributeInfo> AttrInfos;
    private final int EXERCISE_COUNT_MAX;
    private final int EXERCISE_COUNT_MIN;
    private final String FIELD_EXERCISE_COUNT;
    private final String FIELD_TIMEDAY;
    private final String FIELD_TIMEMONTH;
    private final String FIELD_TIMEYEAR;
    private final int OFFSET_DAY;
    private final int OFFSET_YEAR;
    private int exerciseCount;
    private Date timestamp;
    private Map<String, Object> valueMap;

    public ExerciseHeader() {
        this.EXERCISE_COUNT_MIN = 0;
        this.EXERCISE_COUNT_MAX = 4;
        this.FIELD_TIMEDAY = "time_day";
        this.FIELD_TIMEMONTH = "time_month";
        this.FIELD_TIMEYEAR = "time_year";
        this.FIELD_EXERCISE_COUNT = "exerciseCount";
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
        return this.valueMap;
    }

    public int getExerciseCount() {
        return this.exerciseCount;
    }

    @Override
    public HLSeparateMessage getNextMessage() {
        return new ExerciseBody();
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
    }

    public void setExerciseCount(final int exerciseCount) throws InvalidMessageFieldException {
        if (exerciseCount < 0 || exerciseCount > 4) {
            throw new InvalidMessageFieldException("exerciseCount", exerciseCount);
        }
        this.exerciseCount = exerciseCount;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ExeciseHeader{");
        sb.append("timestamp=").append(this.timestamp);
        sb.append(", exerciseCount=").append(this.exerciseCount);
        sb.append('}');
        return sb.toString();
    }
}
