package com.asus.smartwatchcontroller;

import com.asus.smartwatchcontroller.Exceptions.InvalidMessageFieldException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Monect on 25/11/2016.
 */


public class DevSetting implements HLImmediateResponseMessage {
    public static final int Backlight_Always = 3;
    public static final int Backlight_FiveSeconds = 0;
    public static final int Backlight_TenSeconds = 1;
    public static final int Backlight_ThirtySeconds = 2;
    public static final int Beep_Off = 0;
    public static final int Beep_On = 1;
    public static final int City_DST_Off = 1;
    public static final int City_DST_On = 0;
    public static final int Fatigue_Off = 0;
    public static final int Fatigue_On = 1;
    public static final int Gender_Female = 1;
    public static final int Gender_Male = 0;
    public static final int HeartTarget_NoTarget = 0;
    public static final int HeartTarget_ZoneFive = 5;
    public static final int HeartTarget_ZoneFour = 4;
    public static final int HeartTarget_ZoneOne = 1;
    public static final int HeartTarget_ZoneThree = 3;
    public static final int HeartTarget_ZoneTwo = 2;
    public static final int Notifications_All = 31;
    public static final int Notifications_Non = 0;
    public static final int Repeat_EveryWorkday = 2;
    public static final int Repeat_Everyday = 3;
    public static final int Repeat_Off = 0;
    public static final int Repeat_Once = 1;
    public static final int ScreenLock_FiveMinutes = 5;
    public static final int ScreenLock_OneMinutes = 3;
    public static final int ScreenLock_TenMinutes = 6;
    public static final int ScreenLock_TenSeconds = 1;
    public static final int ScreenLock_ThirtySeconds = 2;
    public static final int ScreenLock_ThreeMinutes = 4;
    public static final int ScreenLock_off = 0;
    public static final int TimeFormat_Twelve = 0;
    public static final int TimeFormat_TwentyFour = 1;
    public static final int Unit_Imperial = 1;
    public static final int Unit_Metric = 0;
    public static final int Vibration_Off = 0;
    public static final int Vibration_On = 1;
    public static final int WatchFace_ClockWithHands = 3;
    public static final int WatchFace_Information = 2;
    public static final int WatchFace_Normal = 0;
    public static final int WatchFace_TwoZones = 1;
    private static final long serialVersionUID = -8096268487259609370L;
    private int Age;
    private int Alarm_Hour;
    private int Alarm_Minute;
    private int Alarm_Repeat;
    private List<MessageAttributeInfo> AttrInfos;
    private int BackLight;
    private int Beep;
    private int CalorieTarget;
    private int City2DST;
    private int City2ID;
    private int CityDST;
    private int CityID;
    private int DistanceTarget;
    private final String FIELD_AGE;
    private final String FIELD_ALARM_HOUR;
    private final String FIELD_ALARM_MINUTE;
    private final String FIELD_ALARM_REPEAT;
    private final String FIELD_BACK_LIGHT;
    private final String FIELD_BEEP;
    private final String FIELD_CITY2_DST;
    private final String FIELD_CITY2_ID;
    private final String FIELD_CITY_DST;
    private final String FIELD_CITY_ID;
    private final String FIELD_FATIGUE_FLAG;
    private final String FIELD_GENDER;
    private final String FIELD_HEIGHT;
    private final String FIELD_NOTIFICATION;
    private final String FIELD_SCREEN_LOCK;
    private final String FIELD_TARGET_CAL;
    private final String FIELD_TARGET_DISTANCE;
    private final String FIELD_TARGET_HEART;
    private final String FIELD_TARGET_STEP;
    private final String FIELD_TIME_FORMAT;
    private final String FIELD_UNIT_TYPE;
    private final String FIELD_VIBRATION;
    private final String FIELD_WATCH_FACE;
    private final String FIELD_WEIGHT;
    private int FatigueFlag;
    private int Gender;
    private int HeartRateTarget;
    private int Height;
    private int Notifications;
    private int ScreenLock;
    private int StepTarget;
    private int TimeFormat;
    private int UnitType;
    private int Vibration;
    private int WatchFace;
    private int Weight;
    private Map<String, Object> valueMap;

    public DevSetting() {
        this.FIELD_WATCH_FACE = "WatchFace";
        this.FIELD_BACK_LIGHT = "BackLight";
        this.FIELD_SCREEN_LOCK = "ScreenLock";
        this.FIELD_NOTIFICATION = "Notifications";
        this.FIELD_BEEP = "Beep";
        this.FIELD_VIBRATION = "Vibration";
        this.FIELD_TARGET_STEP = "StepTarget";
        this.FIELD_TARGET_CAL = "CalorieTarget";
        this.FIELD_TARGET_DISTANCE = "DistanceTarget";
        this.FIELD_TARGET_HEART = "HeartRateTarget";
        this.FIELD_TIME_FORMAT = "TimeFormat";
        this.FIELD_CITY_ID = "CityID";
        this.FIELD_CITY_DST = "CityDST";
        this.FIELD_CITY2_DST = "City2DST";
        this.FIELD_CITY2_ID = "City2ID";
        this.FIELD_ALARM_MINUTE = "Alarm_Minute";
        this.FIELD_ALARM_HOUR = "Alarm_Hour";
        this.FIELD_ALARM_REPEAT = "Alarm_Repeat";
        this.FIELD_UNIT_TYPE = "UnitType";
        this.FIELD_FATIGUE_FLAG = "FatigueFlag";
        this.FIELD_GENDER = "Gender";
        this.FIELD_AGE = "Age";
        this.FIELD_WEIGHT = "Weight";
        this.FIELD_HEIGHT = "Height";
    }

    public int getAge() {
        return this.Age;
    }

    public int getAlarm_Hour() {
        return this.Alarm_Hour;
    }

    public int getAlarm_Minute() {
        return this.Alarm_Minute;
    }

    public int getAlarm_Repeat() {
        return this.Alarm_Repeat;
    }

    @Override
    public List<MessageAttributeInfo> getAttributeInfos() {
        if (this.AttrInfos == null) {
            (this.AttrInfos = new ArrayList<MessageAttributeInfo>()).add(new MessageAttributeInfo(AttrType.RESERVED, "NON", 2));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "WatchFace", 2));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "BackLight", 2));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "ScreenLock", 3));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "Notifications", 5));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "Beep", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "Vibration", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.SIGNED_INT, "StepTarget", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.SIGNED_INT, "CalorieTarget", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.SIGNED_INT, "DistanceTarget", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "HeartRateTarget", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.RESERVED, "NON", 2));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "TimeFormat", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.RESERVED, "NON", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "CityID", 12));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "CityDST", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "City2DST", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "City2ID", 14));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "Alarm_Minute", 6));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "Alarm_Hour", 5));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "Alarm_Repeat", 5));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "UnitType", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.RESERVED, "NON", 6));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "FatigueFlag", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "Gender", 1));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "Age", 7));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "Weight", 16));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.UNSIGNED_INT, "Height", 16));
        }
        return this.AttrInfos;
    }

    @Override
    public Map<String, Object> getAttributes() {
        if (this.valueMap == null) {
            this.valueMap = new HashMap<String, Object>();
        }
        this.valueMap.put("WatchFace", this.WatchFace);
        this.valueMap.put("BackLight", this.BackLight);
        this.valueMap.put("ScreenLock", this.ScreenLock);
        this.valueMap.put("Notifications", this.Notifications);
        this.valueMap.put("Beep", this.Beep);
        this.valueMap.put("Vibration", this.Vibration);
        this.valueMap.put("StepTarget", this.StepTarget);
        this.valueMap.put("CalorieTarget", this.CalorieTarget);
        this.valueMap.put("DistanceTarget", this.DistanceTarget);
        this.valueMap.put("HeartRateTarget", this.HeartRateTarget);
        this.valueMap.put("TimeFormat", this.TimeFormat);
        this.valueMap.put("CityID", this.CityID);
        this.valueMap.put("CityDST", this.CityDST);
        this.valueMap.put("City2DST", this.City2DST);
        this.valueMap.put("City2ID", this.City2ID);
        this.valueMap.put("Alarm_Minute", this.Alarm_Minute);
        this.valueMap.put("Alarm_Hour", this.Alarm_Hour);
        this.valueMap.put("Alarm_Repeat", this.Alarm_Repeat);
        this.valueMap.put("UnitType", this.UnitType);
        this.valueMap.put("FatigueFlag", this.FatigueFlag);
        this.valueMap.put("Gender", this.Gender);
        this.valueMap.put("Age", this.Age);
        this.valueMap.put("Weight", this.Weight);
        this.valueMap.put("Height", this.Height);
        return this.valueMap;
    }

    public int getBackLight() {
        return this.BackLight;
    }

    public int getBeep() {
        return this.Beep;
    }

    public int getCalorieTarget() {
        return this.CalorieTarget;
    }

    public int getCity2DST() {
        return this.City2DST;
    }

    public int getCity2ID() {
        return this.City2ID;
    }

    public int getCityDST() {
        return this.CityDST;
    }

    public int getCityID() {
        return this.CityID;
    }

    public int getDistanceTarget() {
        return this.DistanceTarget;
    }

    public int getFatigueFlag() {
        return this.FatigueFlag;
    }

    public int getGender() {
        return this.Gender;
    }

    public int getHeartRateTarget() {
        return this.HeartRateTarget;
    }

    public int getHeight() {
        return this.Height;
    }

    public int getNotifications() {
        return this.Notifications;
    }

    public int getScreenLock() {
        return this.ScreenLock;
    }

    public int getStepTarget() {
        return this.StepTarget;
    }

    public int getTimeFormat() {
        return this.TimeFormat;
    }

    @Override
    public HLPackageConstants.TYPE_MESSAGE getType() {
        return HLPackageConstants.TYPE_MESSAGE.DEV_SETTING;
    }

    public int getUnitType() {
        return this.UnitType;
    }

    public int getVibration() {
        return this.Vibration;
    }

    public int getWatchFace() {
        return this.WatchFace;
    }

    public int getWeight() {
        return this.Weight;
    }

    public void setAge(final int age) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Range(age, 0, 90)) {
            throw new InvalidMessageFieldException("Age", age);
        }
        this.Age = age;
    }

    public void setAlarm_Hour(final int alarm_Hour) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Time_Hour(alarm_Hour)) {
            throw new InvalidMessageFieldException("Alarm_Hour", alarm_Hour);
        }
        this.Alarm_Hour = alarm_Hour;
    }

    public void setAlarm_Minute(final int alarm_Minute) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Time_Minute(alarm_Minute)) {
            throw new InvalidMessageFieldException("Alarm_Minute", alarm_Minute);
        }
        this.Alarm_Minute = alarm_Minute;
    }

    public void setAlarm_Repeat(final int alarm_Repeat) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Range(alarm_Repeat, 0, 3)) {
            throw new InvalidMessageFieldException("Alarm_Repeat", alarm_Repeat);
        }
        this.Alarm_Repeat = alarm_Repeat;
    }

    @Override
    public void setAttributes(final Map<String, Object> map) throws InvalidMessageFieldException {
        this.setWatchFace((Integer) map.get("WatchFace"));
        this.setBackLight((Integer) map.get("BackLight"));
        this.setScreenLock((Integer) map.get("ScreenLock"));
        this.setNotifications((Integer) map.get("Notifications"));
        this.setBeep((Integer) map.get("Beep"));
        this.setVibration((Integer) map.get("Vibration"));
        this.setStepTarget((Integer) map.get("StepTarget"));
        this.setCalorieTarget((Integer) map.get("CalorieTarget"));
        this.setDistanceTarget((Integer) map.get("DistanceTarget"));
        this.setHeartRateTarget((Integer) map.get("HeartRateTarget"));
        this.setTimeFormat((Integer) map.get("TimeFormat"));
        this.setCityID((Integer) map.get("CityID"));
        this.setCityDST((Integer) map.get("CityDST"));
        this.setCity2DST((Integer) map.get("City2DST"));
        this.setCity2ID((Integer) map.get("City2ID"));
        this.setAlarm_Minute((Integer) map.get("Alarm_Minute"));
        this.setAlarm_Hour((Integer) map.get("Alarm_Hour"));
        this.setAlarm_Repeat((Integer) map.get("Alarm_Repeat"));
        this.setUnitType((Integer) map.get("UnitType"));
        this.setFatigueFlag((Integer) map.get("FatigueFlag"));
        this.setGender((Integer) map.get("Gender"));
        this.setAge((Integer) map.get("Age"));
        this.setWeight((Integer) map.get("Weight"));
        this.setHeight((Integer) map.get("Height"));
    }

    public void setBackLight(final int backLight) throws InvalidMessageFieldException {
        if (backLight != 0 && backLight != 1 && backLight != 2 && backLight != 3) {
            throw new InvalidMessageFieldException("BackLight", backLight);
        }
        this.BackLight = backLight;
    }

    public void setBeep(final int beep) {
        this.Beep = beep;
    }

    public void setCalorieTarget(final int calorieTarget) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Range(calorieTarget, 0, 50)) {
            throw new InvalidMessageFieldException("CalorieTarget", calorieTarget);
        }
        this.CalorieTarget = calorieTarget;
    }

    public void setCity2DST(final int city2DST) {
        this.City2DST = city2DST;
    }

    public void setCity2ID(final int city2ID) {
        this.City2ID = city2ID;
    }

    public void setCityDST(final int cityDST) {
        this.CityDST = cityDST;
    }

    public void setCityID(final int cityID) {
        this.CityID = cityID;
    }

    public void setDistanceTarget(final int distanceTarget) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Range(distanceTarget, 0, 59)) {
            throw new InvalidMessageFieldException("DistanceTarget", distanceTarget);
        }
        this.DistanceTarget = distanceTarget;
    }

    public void setFatigueFlag(final int fatigueFlag) {
        this.FatigueFlag = fatigueFlag;
    }

    public void setGender(final int gender) {
        this.Gender = gender;
    }

    public void setHeartRateTarget(final int heartRateTarget) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Range(heartRateTarget, 0, 5)) {
            throw new InvalidMessageFieldException("HeartRateTarget", heartRateTarget);
        }
        this.HeartRateTarget = heartRateTarget;
    }

    public void setHeight(final int height) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Range(height, 0, 1199)) {
            throw new InvalidMessageFieldException("Height", height);
        }
        this.Height = height;
    }

    public void setNotifications(final int notifications) throws InvalidMessageFieldException {
        if (notifications > 31 || notifications < 0) {
            throw new InvalidMessageFieldException("Notifications", notifications);
        }
        this.Notifications = notifications;
    }

    public void setScreenLock(final int screenLock) throws InvalidMessageFieldException {
        if (screenLock < 0 || screenLock > 6) {
            throw new InvalidMessageFieldException("ScreenLock", screenLock);
        }
        this.ScreenLock = screenLock;
    }

    public void setStepTarget(final int stepTarget) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Range(stepTarget, 0, 59)) {
            throw new InvalidMessageFieldException("StepTarget", stepTarget);
        }
        this.StepTarget = stepTarget;
    }

    public void setTimeFormat(final int timeFormat) {
        this.TimeFormat = timeFormat;
    }

    public void setUnitType(final int unitType) {
        this.UnitType = unitType;
    }

    public void setVibration(final int vibration) {
        this.Vibration = vibration;
    }

    public void setWatchFace(final int watchFace) throws InvalidMessageFieldException {
        if (watchFace < 0 || watchFace > 3) {
            throw new InvalidMessageFieldException("WatchFace", watchFace);
        }
        this.WatchFace = watchFace;
    }

    public void setWeight(final int weight) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Range(weight, 0, 3749)) {
            throw new InvalidMessageFieldException("Weight", weight);
        }
        this.Weight = weight;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DevSetting{");
        sb.append("WatchFace=").append(this.WatchFace);
        sb.append(", BackLight=").append(this.BackLight);
        sb.append(", ScreenLock=").append(this.ScreenLock);
        sb.append(", Notifications=").append(this.Notifications);
        sb.append(", Beep=").append(this.Beep);
        sb.append(", Vibration=").append(this.Vibration);
        sb.append(", StepTarget=").append(this.StepTarget);
        sb.append(", CalorieTarget=").append(this.CalorieTarget);
        sb.append(", DistanceTarget=").append(this.DistanceTarget);
        sb.append(", HeartRateTarget=").append(this.HeartRateTarget);
        sb.append(", TimeFormat=").append(this.TimeFormat);
        sb.append(", CityID=").append(this.CityID);
        sb.append(", CityDST=").append(this.CityDST);
        sb.append(", City2DST=").append(this.City2DST);
        sb.append(", City2ID=").append(this.City2ID);
        sb.append(", Alarm_Minute=").append(this.Alarm_Minute);
        sb.append(", Alarm_Hour=").append(this.Alarm_Hour);
        sb.append(", Alarm_Repeat=").append(this.Alarm_Repeat);
        sb.append(", UnitType=").append(this.UnitType);
        sb.append(", FatigueFlag=").append(this.FatigueFlag);
        sb.append(", Gender=").append(this.Gender);
        sb.append(", Age=").append(this.Age);
        sb.append(", Weight=").append(this.Weight);
        sb.append(", Height=").append(this.Height);
        sb.append('}');
        return sb.toString();
    }
}
