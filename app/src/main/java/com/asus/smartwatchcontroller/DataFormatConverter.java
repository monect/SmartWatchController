package com.asus.smartwatchcontroller;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Created by Monect on 25/11/2016.
 */


public class DataFormatConverter {
    private DataFormatConverter() {
        throw new RuntimeException("This is a Utility class, can't access this instance");
    }

    private static int ByteArrayToInt(final byte[] array) {
        return new BigInteger(array).intValue();
    }

    public static int ByteArrayToInt(byte[] array, final HLBluetoothMessage.AttrType attrType) {
        array = reverseByte(array);
        final byte[] array2 = null;
        switch (attrType) {
            default: {
                array = array2;
                break;
            }
            case SIGNED_INT: {
                break;
            }
            case UNSIGNED_INT:
            case UTF8_LENGTH: {
                array = appendByteValueToHead(array, 1, (byte) 0);
                break;
            }
        }
        return ByteArrayToInt(array);
    }

    public static byte[] IntToByteArray(final int n, final int n2) {
        final byte[] intToByteArray = intToByteArray(n);
        if (isArrayLengthOutofBound(intToByteArray.length, n2)) {
            throw new RuntimeException("Overflow!! value : " + n + " expect byte length : " + n2);
        }
        byte[] clipFirstByte = intToByteArray;
        if (isUnsignedNumber(intToByteArray, n2)) {
            clipFirstByte = clipFirstByte(intToByteArray);
        }
        byte[] appendByteLength = clipFirstByte;
        if (clipFirstByte.length < n2) {
            appendByteLength = appendByteLength(clipFirstByte, n, n2);
        }
        return reverseByte(appendByteLength);
    }

    public static byte[] StringToUTF8ByteArray(final String s) {
        return s.getBytes(StandardCharsets.UTF_8);
    }

    public static String UTF8ByteArrayToString(final byte[] array) {
        return new String(array, StandardCharsets.UTF_8);
    }

    private static byte[] appendByteLength(final byte[] array, final int n, final int n2) {
        final int length = array.length;
        byte b;
        if (n < 0) {
            b = -1;
        } else {
            b = 0;
        }
        return appendByteValueToHead(array, n2 - length, b);
    }

    private static byte[] appendByteValueToHead(final byte[] array, final int n, final byte b) {
        final byte[] array2 = new byte[array.length + n];
        for (int i = 0; i != n; ++i) {
            array2[i] = b;
        }
        System.arraycopy(array, 0, array2, n, array.length);
        return array2;
    }

    private static byte[] clipFirstByte(final byte[] array) {
        return Arrays.copyOfRange(array, 1, array.length);
    }

    private static byte[] intToByteArray(final int n) {
        return new BigInteger("" + n, 10).toByteArray();
    }

    private static boolean isArrayLengthOutofBound(final int n, final int n2) {
        return n > n2 + 1;
    }

    private static boolean isUnsignedNumber(final byte[] array, final int n) {
        boolean b = false;
        if (array.length == n + 1) {
            if (array[0] != 0) {
                throw new RuntimeException("Singed value Overflow!! value : " + Arrays.toString(array) + " expect byte length : " + n);
            }
            b = true;
        }
        return b;
    }

    private static byte[] reverseByte(final byte[] array) {
        final byte[] array2 = new byte[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[array.length - 1 - i];
        }
        return array2;
    }
}
