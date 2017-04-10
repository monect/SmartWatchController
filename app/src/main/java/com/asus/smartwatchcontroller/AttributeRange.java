package com.asus.smartwatchcontroller;

/**
 * Created by Monect on 25/11/2016.
 */

final class AttributeRange {
    private AttributeRange() {
        throw new RuntimeException("This is a utility class");
    }

    static boolean isInvalid_Flag(final int n) {
        return (n | 0x1) == 0x1;
    }

    static boolean isInvalid_GPS_FixQuality(final int n) {
        return n != 0 && n != 1 && n != 2;
    }

    static boolean isInvalid_GPS_Latitude(final float n) {
        return Float.compare(n, -90.0f) == -1 || Float.compare(n, 90.0f) == 1;
    }

    static boolean isInvalid_GPS_Longitude(final float n) {
        return Float.compare(n, -180.0f) == -1 || Float.compare(n, 180.0f) == 1;
    }

    static boolean isInvalid_GPS_Mode(final int n) {
        return n != 3 && n != 1 && n != 2;
    }

    static boolean isInvalid_Heart(final int n) {
        return false;
    }

    static boolean isInvalid_Persent(final int n) {
        return n < 0 || n > 100;
    }

    static boolean isInvalid_Range(final int n, final int n2, final int n3) {
        return n < n2 || n > n3;
    }

    static boolean isInvalid_Signed_Short(final int n) {
        return n < -32768 || n > 32767;
    }

    static boolean isInvalid_Time_Day(final int n) {
        return n < 0 || n > 30;
    }

    static boolean isInvalid_Time_Hour(final int n) {
        return n < 0 || n > 23;
    }

    static boolean isInvalid_Time_Minute(final int n) {
        return n < 0 || n > 59;
    }

    static boolean isInvalid_Time_Month(final int n) {
        return n < 0 || n > 11;
    }

    static boolean isInvalid_Time_Second(final int n) {
        return n < 0 || n > 59;
    }

    static boolean isInvalid_Time_Year(final int n) {
        return n < 0 || n > 63;
    }

    static boolean isInvalid_Unsigned_Byte(final int n) {
        return n < 0 || n > 255;
    }

    static boolean isInvalid_Unsigned_Short(final int n) {
        return n < 0 || n > 65535;
    }
}
