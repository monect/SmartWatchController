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


public class GpsData implements HLMediateResponseMessage {
    private static final long serialVersionUID = 8366524812202653828L;
    private List<MessageAttributeInfo> AttrInfos;
    private final float DECIMAL_SCALE;
    private final String FIELD_ALTITUDE;
    private final String FIELD_FIXQUALITY;
    private final String FIELD_LATITUDE;
    private final String FIELD_LONGITUDE;
    private final String FIELD_MODE;
    private final String FIELD_SATELITECOUNT;
    private final String FIELD_SPEED;
    private final String FIELD_TIMEDAY;
    private final String FIELD_TIMEHOUR;
    private final String FIELD_TIMEMINUTE;
    private final String FIELD_TIMEMONTH;
    private final String FIELD_TIMESECOND;
    private final String FIELD_TIMEYEAR;
    private final float GPS_SCALE;
    private final int OFFSET_DAY;
    private final int OFFSET_YEAR;
    private float altitude;
    private int fixQuality;
    private float latitude;
    private float longitude;
    private int mode;
    private int satelliteCount;
    private int speed;
    private Date timestamp;
    private Map<String, Object> valueMap;

    public GpsData() {
        this.FIELD_TIMESECOND = "time_second";
        this.FIELD_TIMEMINUTE = "time_minute";
        this.FIELD_TIMEHOUR = "time_hour";
        this.FIELD_TIMEDAY = "time_day";
        this.FIELD_TIMEMONTH = "time_month";
        this.FIELD_TIMEYEAR = "time_year";
        this.FIELD_LATITUDE = "latitude";
        this.FIELD_LONGITUDE = "longitude";
        this.FIELD_ALTITUDE = "altitude";
        this.FIELD_SATELITECOUNT = "satelliteCount";
        this.FIELD_FIXQUALITY = "fixQuality";
        this.FIELD_MODE = "mode";
        this.FIELD_SPEED = "speed";
        this.GPS_SCALE = 3600000.0f;
        this.DECIMAL_SCALE = 10.0f;
        this.OFFSET_YEAR = 2014;
        this.OFFSET_DAY = 1;
    }

    private void setAltitude(final int n) {
        this.altitude = n / 10.0f;
    }

    private void setLatitude(final int n) throws InvalidMessageFieldException {
        this.setLongitude(n / 3600000.0f);
    }

    private void setLongitude(final int n) throws InvalidMessageFieldException {
        this.setLatitude(n / 3600000.0f);
    }

    private void setSpeed(final int speed) {
        this.speed = speed;
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

    public float getAltitude() {
        return this.altitude;
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
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.SIGNED_INT, "latitude", 32));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.SIGNED_INT, "longitude", 32));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.SIGNED_INT, "altitude", 24));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "satelliteCount", 4));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "fixQuality", 2));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "mode", 2));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "speed", 16));
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
        this.valueMap.put("latitude", (int) (this.longitude * 3600000.0f));
        this.valueMap.put("longitude", (int) (this.latitude * 3600000.0f));
        this.valueMap.put("altitude", (int) (this.altitude * 10.0f));
        this.valueMap.put("satelliteCount", this.satelliteCount);
        this.valueMap.put("fixQuality", this.fixQuality);
        this.valueMap.put("mode", this.mode);
        this.valueMap.put("speed", this.speed);
        return this.valueMap;
    }

    public int getFixQuality() {
        return this.fixQuality;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public float getLongitude() {
        return this.longitude;
    }

    public int getMode() {
        return this.mode;
    }

    public int getSatelliteCount() {
        return this.satelliteCount;
    }

    public int getSpeed() {
        return this.speed;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    @Override
    public HLPackageConstants.TYPE_MESSAGE getType() {
        return HLPackageConstants.TYPE_MESSAGE.GPS;
    }

    public void setAltitude(final float altitude) {
        this.altitude = altitude;
    }

    @Override
    public void setAttributes(final Map<String, Object> map) throws InvalidMessageFieldException {
        this.setTimestamp((Integer) map.get("time_second"), (Integer) map.get("time_minute"), (Integer) map.get("time_hour"), (Integer) map.get("time_day"), (Integer) map.get("time_month"), (Integer) map.get("time_year"));
        this.setLatitude((Float) map.get("latitude"));
        this.setLongitude((Float) map.get("longitude"));
        this.setAltitude((Float) map.get("altitude"));
        this.setFixQuality((Integer) map.get("fixQuality"));
        this.setSatelliteCount((Integer) map.get("satelliteCount"));
        this.setMode((Integer) map.get("mode"));
        this.setSpeed((Float) map.get("speed"));
    }

    public void setFixQuality(final int fixQuality) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_GPS_FixQuality(fixQuality)) {
            throw new InvalidMessageFieldException("fixQuality", fixQuality);
        }
        this.fixQuality = fixQuality;
    }

    public void setLatitude(final float latitude) throws InvalidMessageFieldException {
        this.latitude = latitude;
        if (AttributeRange.isInvalid_GPS_Latitude(this.latitude)) {
            throw new InvalidMessageFieldException("longitude", this.latitude);
        }
    }

    public void setLongitude(final float longitude) throws InvalidMessageFieldException {
        this.longitude = longitude;
        if (AttributeRange.isInvalid_GPS_Longitude(this.longitude)) {
            throw new InvalidMessageFieldException("latitude", this.longitude);
        }
    }

    public void setMode(final int mode) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_GPS_Mode(mode)) {
            throw new InvalidMessageFieldException("mode", mode);
        }
        this.mode = mode;
    }

    public void setSatelliteCount(final int satelliteCount) {
        this.satelliteCount = satelliteCount;
    }

    public void setSpeed(final float n) {
        this.speed = (int) (10.0f * n);
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GpsData{");
        sb.append("timestamp=").append(this.timestamp);
        sb.append(", longitude=").append(this.longitude);
        sb.append(", latitude=").append(this.latitude);
        sb.append(", altitude=").append(this.altitude);
        sb.append(", satelliteCount=").append(this.satelliteCount);
        sb.append(", fixQuality=").append(this.fixQuality);
        sb.append(", mode=").append(this.mode);
        sb.append(", speed=").append(this.speed);
        sb.append('}');
        return sb.toString();
    }
}
