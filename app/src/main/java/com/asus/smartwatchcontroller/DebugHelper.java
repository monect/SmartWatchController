package com.asus.smartwatchcontroller;

/**
 * Created by Monect on 25/11/2016.
 */

public class DebugHelper {
    private DebugHelper() {
        throw new RuntimeException("This is an utility class!!");
    }

    public static String byteArrayToHex(byte[] paramArrayOfByte) {
        StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 2);
        localStringBuilder.append("[").append(String.format("%02x", new Object[]{Integer.valueOf(paramArrayOfByte[0] & 0xFF)}));
        int i = 1;
        while (i < paramArrayOfByte.length) {
            localStringBuilder.append(", ").append(String.format("%02x", new Object[]{Integer.valueOf(paramArrayOfByte[i] & 0xFF)}));
            i += 1;
        }
        localStringBuilder.append("]");
        return localStringBuilder.toString();
    }

    public static byte caculateChecksum(byte[] paramArrayOfByte) {
        int j = 255;
        int k = paramArrayOfByte.length;
        int i = 0;
        while (i < k) {
            j ^= paramArrayOfByte[i];
            i += 1;
        }
        return (byte) (j & 0xFF);
    }
}
