package com.asus.smartwatchcontroller;


import com.asus.smartwatchcontroller.Exceptions.ChecksumMismatchException;
import com.asus.smartwatchcontroller.Exceptions.InvalidPackageLengthException;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Monect on 25/11/2016.
 */

public class HLPackage implements Serializable {

    public static final int MAX_BODY_LENGTH = 18;
    public static final int MAX_PACKAGE_LENGTH = 20;
    public static final int MIN_PACKAGE_LENGTH = 2;
    private static final long serialVersionUID = -3830119347375131370L;
    private final int INIT_CHECKSUM = 255;
    private final byte[] body;
    private final byte header;
    private final byte[] packageContent;

    public HLPackage(byte[] paramArrayOfByte)
            throws InvalidPackageLengthException, ChecksumMismatchException {
        if (isPackageLengthInvalid(paramArrayOfByte.length)) {
            throw new InvalidPackageLengthException("Wrong package byte size : " + paramArrayOfByte.length);
        }
        if (isChecksumMismatch(paramArrayOfByte)) {
            throw new ChecksumMismatchException("Error checksum!! contents : " + DebugHelper.byteArrayToHex(paramArrayOfByte));
        }
        this.packageContent = paramArrayOfByte;
        this.header = paramArrayOfByte[0];
        if (paramArrayOfByte.length > 2) {
            this.body = Arrays.copyOfRange(paramArrayOfByte, 1, paramArrayOfByte.length - 1);
            return;
        }
        this.body = null;
    }

    private boolean isChecksumMismatch(byte[] paramArrayOfByte) {
        int j = 255;
        int i = 0;
        while (i != paramArrayOfByte.length) {
            j ^= paramArrayOfByte[i];
            i += 1;
        }
        return (j & 0xFF) != 0;
    }

    private boolean isPackageLengthInvalid(int paramInt) {
        return paramInt < 2 || paramInt > 20;
    }

    public boolean equals(Object paramObject) {

        do {
            if ((paramObject == null) || (getClass() != paramObject.getClass())) {
                return false;
            }

            if (this.header != ((HLPackage) paramObject).header) {
                return false;
            }
        } while (Arrays.equals(this.body, ((HLPackage) paramObject).body));
        return true;
    }

    public byte[] getBody() {
        return this.body;
    }

    public byte getHeader() {
        return this.header;
    }

    public int hashCode() {
        int j = this.header;
        int i = 0;
        if (this.body != null) {
            i = Arrays.hashCode(this.body);
        }
        return j * 31 + i;
    }

    public byte[] toByteArray() {
        return this.packageContent;
    }
}
