package com.asus.smartwatchcontroller;

import com.asus.smartwatchcontroller.Exceptions.InvalidMessageFieldException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Monect on 25/11/2016.
 */


public class Weather implements HLMediateResponseMessage {
    public static final int CODE_CLOUDY = 1;
    public static final int CODE_ERROR = -1;
    public static final int CODE_FOGGY = 5;
    public static final int CODE_OVERCAST = 2;
    public static final int CODE_RAIN = 3;
    public static final int CODE_SNOWY = 4;
    public static final int CODE_SUNNY = 0;
    private static final long serialVersionUID = -7448350524893551006L;
    private List<MessageAttributeInfo> AttrInfos;
    private final String FIELD_TEMPERATURE;
    private final String FIELD_WEATHER_CODE;
    final float TEMPERATURE_UNIT;
    private float temperature;
    private Map<String, Object> valueMap;
    private int weatherCode;

    public Weather() {
        this.FIELD_TEMPERATURE = "temperature";
        this.FIELD_WEATHER_CODE = "weatherCode";
        this.TEMPERATURE_UNIT = 10.0f;
        this.temperature = 0.0f;
        this.weatherCode = -1;
    }

    private void setTemperature(final int n) throws InvalidMessageFieldException {
        if (AttributeRange.isInvalid_Signed_Short(n)) {
            throw new InvalidMessageFieldException("Wrong value for temperature : " + n);
        }
        this.temperature = n / 10.0f;
    }

    @Override
    public List<MessageAttributeInfo> getAttributeInfos() {
        if (this.AttrInfos == null) {
            (this.AttrInfos = new ArrayList<MessageAttributeInfo>()).add(new MessageAttributeInfo(AttrType.SIGNED_INT, "temperature", 16));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.SIGNED_INT, "weatherCode", 8));
            this.AttrInfos.add(new MessageAttributeInfo(AttrType.RESERVED, "NON", 8));
        }
        return this.AttrInfos;
    }

    @Override
    public Map<String, Object> getAttributes() {
        if (this.valueMap == null) {
            this.valueMap = new HashMap<String, Object>();
        }
        this.valueMap.put("weatherCode", this.weatherCode);
        this.valueMap.put("temperature", (int) (this.temperature * 10.0f));
        return this.valueMap;
    }

    public float getTemperature() {
        return this.temperature;
    }

    @Override
    public HLPackageConstants.TYPE_MESSAGE getType() {
        return HLPackageConstants.TYPE_MESSAGE.WEATHER;
    }

    public int getWeatherCode() {
        return this.weatherCode;
    }

    @Override
    public void setAttributes(final Map<String, Object> map) throws InvalidMessageFieldException {
        this.setTemperature((Float) map.get("temperature"));
        this.setWeatherCode((Integer) map.get("weatherCode"));
    }

    public void setTemperature(final float n) throws InvalidMessageFieldException {
        this.setTemperature((int) (10.0f * n));
    }

    public void setWeatherCode(final int weatherCode) throws InvalidMessageFieldException {
        if (weatherCode > 5 || weatherCode < 0) {
            throw new InvalidMessageFieldException("Wrong value for weatherCode : " + weatherCode);
        }
        this.weatherCode = weatherCode;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Weather{");
        String s = "";
        switch (this.weatherCode) {
            case 0: {
                s = "CODE_SUNNY";
                break;
            }
            case 1: {
                s = "CODE_CLOUDY";
                break;
            }
            case 2: {
                s = "CODE_OVERCAST";
                break;
            }
            case 3: {
                s = "CODE_RAIN";
                break;
            }
            case 4: {
                s = "CODE_SNOWY";
                break;
            }
            case 5: {
                s = "CODE_FOGGY";
                break;
            }
            case -1: {
                s = "CODE_ERROR";
                break;
            }
        }
        sb.append("weatherCode=").append(s);
        sb.append(", temperature=").append(this.temperature).append(" C");
        sb.append('}');
        return sb.toString();
    }
}

