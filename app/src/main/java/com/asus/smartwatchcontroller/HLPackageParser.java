package com.asus.smartwatchcontroller;

import android.util.Log;

import com.asus.smartwatchcontroller.Exceptions.PackageParserException;

import java.util.Arrays;
import java.util.BitSet;

/**
 * Created by Monect on 25/11/2016.
 */


public class HLPackageParser {
    public static final int NO_SEQUENCE_NUMBER = -1;
    private final int MASK_DIR;
    private final int MASK_ROLE;
    private final int MASK_TYPE_MESSAGE;
    private final int MASK_TYPE_SEQUENCE;
    private final int SHIFT_DIR;
    private final int SHIFT_ROLE;
    private final int SHIFT_TYPE_MESSAGE;
    private final int SHIFT_TYPE_SEQUENCE;
    private int SequenceNumber;
    private final byte[] body;
    private final HLPackageConstants.DIR dir;
    private final HLPackageConstants.TYPE_MESSAGE messageType;
    private final HLPackageConstants.ROLE role;
    private final HLPackageConstants.TYPE_SEQUENCE sequenceType;

    public HLPackageParser(final HLPackage hlPackage) throws PackageParserException {
        this.MASK_TYPE_SEQUENCE = 192;
        this.MASK_TYPE_MESSAGE = 15;
        this.MASK_ROLE = 32;
        this.MASK_DIR = 16;
        this.SHIFT_DIR = 4;
        this.SHIFT_ROLE = 5;
        this.SHIFT_TYPE_MESSAGE = 0;
        this.SHIFT_TYPE_SEQUENCE = 6;
        final byte header = hlPackage.getHeader();
        this.role = HLPackageConstants.ROLE.values()[(header & 0x20) >> 5];
        this.dir = HLPackageConstants.DIR.values()[(header & 0x10) >> 4];
        this.messageType = HLPackageConstants.TYPE_MESSAGE.values()[(header & 0xF) >> 0];
        this.sequenceType = HLPackageConstants.TYPE_SEQUENCE.values()[(header & 0xC0) >> 6];
        if (this.sequenceType == HLPackageConstants.TYPE_SEQUENCE.ONE) {
            this.SequenceNumber = -1;
            this.body = hlPackage.getBody();
            return;
        }
        if (hlPackage.getBody() == null) {
            throw new PackageParserException("message with Sequence Type :" + this.sequenceType + " must has it's body");
        }
        this.SequenceNumber = (hlPackage.getBody()[0] & 0xFF);
        this.body = Arrays.copyOfRange(hlPackage.getBody(), 1, hlPackage.getBody().length);
    }

    public int getBodyLength() {
        if (this.body == null) {
            return 0;
        }
        return this.body.length;
    }

    public byte[] getBytesAt(final int n, final int n2) throws IndexOutOfBoundsException {
        return Arrays.copyOfRange(this.body, n, n2);
    }

    public HLPackageConstants.DIR getDir() {
        return this.dir;
    }

    public boolean getFlagValueAt(final int n) throws IndexOutOfBoundsException {
        return BitSet.valueOf(this.body).get(n);
    }

    public HLPackageConstants.TYPE_MESSAGE getMessageType() {
        return this.messageType;
    }

    public Object getNullableAt(final int n, final int n2) {
        if (this.body == null) {
            return new NullObject();
        }
        return this.getNumberAt(n, n2, HLBluetoothMessage.AttrType.UNSIGNED_INT);
    }

    public int getNumberAt(final int n, final int n2, final HLBluetoothMessage.AttrType attrType) throws IndexOutOfBoundsException {
        if ((n2 - n) % 8 == 0) {
            final int n3 = n / 8;
            final int n4 = n2 / 8;
            try {
                final byte[] copyOfRange = Arrays.copyOfRange(this.body, n3, n4);
                Log.d("HLPackageParser", "startIndex : " + n + " endIndex : " + n2 + " number : " + DebugHelper.byteArrayToHex(copyOfRange));
                return DataFormatConverter.ByteArrayToInt(copyOfRange, attrType);
            } catch (Exception ex) {
                final StringBuilder append = new StringBuilder().append("body:");
                String byteArrayToHex;
                if (this.body == null) {
                    byteArrayToHex = "null";
                } else {
                    byteArrayToHex = DebugHelper.byteArrayToHex(this.body);
                }
                throw new IndexOutOfBoundsException(append.append(byteArrayToHex).append(" startByte:").append(n3).append(" endByte:").append(n4).toString());
            }
        }
        final BitSet value = BitSet.valueOf(this.body).get(n, n2);
        int n5 = 0;
        for (int i = value.nextSetBit(0); i >= 0; i = value.nextSetBit(i + 1)) {
            n5 += 1 << i;
        }
        Log.d("HLPackageParser", "startIndex : " + n + " endIndex : " + n2 + " number : " + n5);
        return n5;
    }

    public HLPackageConstants.ROLE getRole() {
        return this.role;
    }

    public int getSequenceNumber() {
        return this.SequenceNumber;
    }

    public HLPackageConstants.TYPE_SEQUENCE getSequenceType() {
        return this.sequenceType;
    }

    public String getStringAt(final int n, final int n2) {
        return DataFormatConverter.UTF8ByteArrayToString(this.getBytesAt(n / 8, n2 / 8));
    }
}
