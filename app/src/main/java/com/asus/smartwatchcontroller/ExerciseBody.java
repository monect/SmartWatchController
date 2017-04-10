package com.asus.smartwatchcontroller;

import com.asus.smartwatchcontroller.Exceptions.InvalidMessageFieldException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Monect on 25/11/2016.
 */


public class ExerciseBody implements HLSeparateMessage {
    public static final int MODE_BIKING = 2;
    public static final int MODE_HIKING = 3;
    public static final int MODE_RUNNING = 1;
    public static final int MODE_WALKING = 0;
    private static final long serialVersionUID = -1391770059668619943L;
    private List<MessageAttributeInfo> AttrInfos;
    private final String FIELD_EXERCISE_CAL;
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
    private Map<String, Object> valueMap;

    public ExerciseBody() {
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
    }

    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final ExerciseBody exerciseBody = (ExerciseBody) o;
            if (this.exercise_cal != exerciseBody.exercise_cal) {
                return false;
            }
            if (this.exercise_distance != exerciseBody.exercise_distance) {
                return false;
            }
            if (this.exercise_time != exerciseBody.exercise_time) {
                return false;
            }
            if (this.heart_average != exerciseBody.heart_average) {
                return false;
            }
            if (this.heart_highest != exerciseBody.heart_highest) {
                return false;
            }
            if (this.heart_lowest != exerciseBody.heart_lowest) {
                return false;
            }
            if (this.heart_zone1 != exerciseBody.heart_zone1) {
                return false;
            }
            if (this.heart_zone2 != exerciseBody.heart_zone2) {
                return false;
            }
            if (this.heart_zone3 != exerciseBody.heart_zone3) {
                return false;
            }
            if (this.heart_zone4 != exerciseBody.heart_zone4) {
                return false;
            }
            if (this.mode != exerciseBody.mode) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<MessageAttributeInfo> getAttributeInfos() {
        if (this.AttrInfos == null) {
            (this.AttrInfos = new ArrayList<MessageAttributeInfo>()).add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "mode", 8));
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

    @Override
    public HLSeparateMessage getNextMessage() {
        return this;
    }

    @Override
    public HLPackageConstants.TYPE_MESSAGE getType() {
        return HLPackageConstants.TYPE_MESSAGE.EXERCISE_DATA;
    }

    @Override
    public int hashCode() {
        return (((((((((this.mode * 31 + this.heart_average) * 31 + this.heart_lowest) * 31 + this.heart_highest) * 31 + this.heart_zone1) * 31 + this.heart_zone2) * 31 + this.heart_zone3) * 31 + this.heart_zone4) * 31 + this.exercise_time) * 31 + this.exercise_distance) * 31 + this.exercise_cal;
    }

    @Override
    public void setAttributes(final Map<String, Object> map) throws InvalidMessageFieldException {
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ExeciseBody{");
        sb.append("mode=").append(this.mode);
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
