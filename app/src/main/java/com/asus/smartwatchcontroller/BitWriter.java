package com.asus.smartwatchcontroller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Monect on 25/11/2016.
 */


public class BitWriter {
    private final int MAX_BUFFER_SIZE;
    private BigInteger bitBuffer;
    private int bitBufferSize;
    private int bufferSize;
    private List<Byte> byteBuffer;
    private StringBuilder stringBuilder;

    private BitWriter(final int max_BUFFER_SIZE) {
        this.stringBuilder = new StringBuilder();
        this.MAX_BUFFER_SIZE = max_BUFFER_SIZE;
        this.byteBuffer = new ArrayList<Byte>();
        this.bufferSize = 0;
        this.bitBufferSize = 0;
        this.bitBuffer = new BigInteger("0");
    }

    public static BitWriter getContinuesBitWriter() {
        return new BitWriter(Integer.MAX_VALUE);
    }

    public static BitWriter getSingleBitWriter() {
        return new BitWriter(144);
    }

    private boolean isIndexOutOfBound(final int n) {
        return this.bufferSize + n > this.MAX_BUFFER_SIZE;
    }

    private void saveBitBufferToByteBuffer() {
        if (this.bufferSize % 8 == 0) {
            final byte[] byteArray = this.bitBuffer.toByteArray();
            byte[] copyOfRange;
            if (this.bitBufferSize / 8 < byteArray.length) {
                copyOfRange = Arrays.copyOfRange(byteArray, 1, byteArray.length);
            } else {
                copyOfRange = byteArray;
                if (this.bitBufferSize / 8 > byteArray.length) {
                    copyOfRange = new byte[this.bitBufferSize / 8];
                    System.arraycopy(byteArray, 0, copyOfRange, copyOfRange.length - byteArray.length, byteArray.length);
                }
            }
            this.writeByteArrayReverse(copyOfRange);
            this.bitBuffer = new BigInteger("0", 16);
            this.bitBufferSize = 0;
        }
    }

    public byte[] toByteArray() {
        final byte[] array = new byte[this.byteBuffer.size()];
        for (int i = 0; i < this.byteBuffer.size(); ++i) {
            array[i] = this.byteBuffer.get(i);
        }
        return array;
    }

    public void writeByteArray(final byte[] array) {
        if (this.isIndexOutOfBound(array.length)) {
            throw new IndexOutOfBoundsException("max buffer size : " + this.MAX_BUFFER_SIZE + ", current size : " + array.length + this.bufferSize);
        }
        for (int i = 0; i != array.length; ++i) {
            this.byteBuffer.add(array[i]);
        }
    }

    public void writeByteArrayReverse(final byte[] array) {
        if (this.isIndexOutOfBound(array.length)) {
            throw new IndexOutOfBoundsException("max buffer size : " + this.MAX_BUFFER_SIZE + ", current size : " + array.length + this.bufferSize);
        }
        for (int i = array.length - 1; i >= 0; --i) {
            this.byteBuffer.add(array[i]);
        }
    }

    public void writeNumberWithBitSize(final int n, final int n2) {
        this.bufferSize += n2;
        this.bitBuffer = this.bitBuffer.or(new BigInteger(String.valueOf(n)).shiftLeft(this.bitBufferSize));
        this.bitBufferSize += n2;
        this.saveBitBufferToByteBuffer();
        this.stringBuilder.append("add number ").append(n).append(" : ").append(this.bitBuffer).append("\n");
    }

    public void writeNumberWithByteSize(final int n, final int n2) {
        this.writeByteArray(DataFormatConverter.IntToByteArray(n, n2));
    }
}
