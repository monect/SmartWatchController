package com.asus.smartwatchcontroller;

import android.util.Log;

import com.asus.smartwatchcontroller.Exceptions.MessageBuildException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Monect on 25/11/2016.
 */


public class HLPackageAssembler {
    private final int MAX_SEQUENCE_BIT_LENGTH;
    private final int MAX_SEQUENCE_BYTE_LENGTH;
    private final int MAX_SEQUENCE_NUMBER;
    private Listener assemblerListener;
    private HLPackageParser currentParser;
    private List<HLPackageParser> hlPackageParsers;
    private HLBluetoothMessage message;
    private MessageFactory messageFactory;
    private HLPackageConstants.TYPE_MESSAGE messageType;
    private int sequenceNumber;
    private SequenceState sequenceState;
    private HLPackageConstants.TYPE_SEQUENCE sequenceType;

    public HLPackageAssembler(final MessageFactory messageFactory, final Listener assemblerListener) {
        this.MAX_SEQUENCE_NUMBER = 255;
        this.MAX_SEQUENCE_BYTE_LENGTH = 17;
        this.MAX_SEQUENCE_BIT_LENGTH = 136;
        this.clearData();
        this.messageFactory = messageFactory;
        this.assemblerListener = assemblerListener;
    }

    private void buildMessage() throws MessageBuildException {
        final Iterator<HLPackageParser> iterator = this.hlPackageParsers.iterator();
        final HashMap<String, Object> attributes = new HashMap<>();
        int n = 0;
        int intValue = 0;
        this.currentParser = iterator.next();
        for (final MessageAttributeInfo messageAttributeInfo : this.message.getAttributeInfos()) {
            final HLBluetoothMessage.AttrType type = messageAttributeInfo.getType();
            final String fieldName = messageAttributeInfo.getFieldName();
            final int bitCount = messageAttributeInfo.getBitCount();
            int n3;
            Object valueFromParser;
            if (type == HLBluetoothMessage.AttrType.UTF8) {
                Log.d("HLPackage Message", "type : " + type + " startIndex : " + n + " UTF8ByteLength :" + intValue);
                final String stringFromParsers = this.getStringFromParsers(iterator, n, intValue);
                final int n2 = n3 = n + intValue * 8;
                valueFromParser = stringFromParsers;
                if (n2 > 136) {
                    n3 = n2 - 136;
                    valueFromParser = stringFromParsers;
                }
            } else {
                valueFromParser = this.getValueFromParser(type, this.currentParser, n, bitCount + n);
                n3 = n + messageAttributeInfo.getBitCount();
            }
            Log.d("HLPackage attributes", "attrName : " + fieldName + " value : " + valueFromParser);
            attributes.put(fieldName, valueFromParser);
            n = n3;
            if (type == HLBluetoothMessage.AttrType.UTF8_LENGTH) {
                intValue = (int) valueFromParser;
                n = n3;
            }
        }
        this.message.setAttributes(attributes);
    }

    private void buildNormalMessage(final HLPackageParser hlPackageParser) throws MessageBuildException {
        if (this.isMessageComplete()) {
            this.buildMessage();
        }
        this.handleListeners(hlPackageParser);
    }

    private void buildSeparateMessage(final HLPackageParser hlPackageParser) throws MessageBuildException {
        this.buildMessage();
        this.handleListeners(hlPackageParser);
    }

    private void clearData() {
        this.sequenceNumber = -1;
        this.sequenceState = (SequenceState) new FirstState();
        this.messageType = null;
        this.message = null;
        this.hlPackageParsers = new ArrayList<HLPackageParser>();
    }

    private HLBluetoothMessage createMessageIfNull(final HLPackageConstants.ROLE role, final HLPackageConstants.TYPE_MESSAGE type_MESSAGE, final HLPackageParser hlPackageParser) {
        if (this.message != null) {
            return this.message;
        }
        final int minimumBodyLength = type_MESSAGE.getMinimumBodyLength();
        if (this.isSpecialExerciseCase(hlPackageParser)) {
            return this.messageFactory.createSpecialExerciseData();
        }
        if (hlPackageParser.getBodyLength() >= minimumBodyLength) {
            return this.messageFactory.createMessageByType(type_MESSAGE);
        }
        return this.messageFactory.createMessageByRole(role, type_MESSAGE, this.sequenceNumber);
    }

    private String getStringFromParsers(final Iterator<HLPackageParser> iterator, final int n, int i) {
        final int n2 = n + i * 8;
        String s;
        if (n2 < 136) {
            s = this.currentParser.getStringAt(n, n2);
        } else {
            final int n3 = n / 8;
            i -= 17 - n3;
            final BitWriter singleBitWriter = BitWriter.getSingleBitWriter();
            singleBitWriter.writeByteArray(this.currentParser.getBytesAt(n3, 17));
            if (iterator.hasNext()) {
                this.currentParser = iterator.next();
                while (i > 17) {
                    singleBitWriter.writeByteArray(this.currentParser.getBytesAt(0, 17));
                    i -= 17;
                    this.currentParser = iterator.next();
                }
                singleBitWriter.writeByteArray(this.currentParser.getBytesAt(0, i));
            }
            s = DataFormatConverter.UTF8ByteArrayToString(singleBitWriter.toByteArray());
        }
        Log.i("getStringFromParsers", "startIndex :" + n + " endIndex : " + n2 + " string : " + s);
        return s;
    }

    private Object getValueFromParser(final HLBluetoothMessage.AttrType attrType, final HLPackageParser hlPackageParser, final int n, final int n2) throws MessageBuildException {
        final Object o = null;
        try {
            Object o2 = null;
            switch (attrType) {
                default: {
                    o2 = o;
                    break;
                }
                case SIGNED_INT:
                case UNSIGNED_INT:
                case UTF8_LENGTH: {
                    o2 = hlPackageParser.getNumberAt(n, n2, attrType);
                    break;
                }
                case FLAG: {
                    o2 = hlPackageParser.getFlagValueAt(n);
                    break;
                }
                case RESERVED: {
                    o2 = new NullObject();
                    break;
                }
                case NULLABLE: {
                    o2 = hlPackageParser.getNullableAt(n, n2);
                    break;
                }
            }
            Log.d("getValueFromParser", "getValueFromParser - type : " + attrType + " value : " + o2);
            return o2;
        } catch (IndexOutOfBoundsException ex) {
            throw new MessageBuildException("IndexOutOfBound", ex);
        }
    }

    private void handleListeners(final HLPackageParser hlPackageParser) throws MessageBuildException {
        if (this.message instanceof HLSeparateMessage) {
            this.assemblerListener.onSeparateMessageReceived((HLSeparateMessage) this.message, hlPackageParser);
            this.hlPackageParsers.clear();
            this.message = ((HLSeparateMessage) this.message).getNextMessage();
            if (this.isMessageComplete()) {
                this.clearData();
            }
            return;
        }
        if (this.isMessageComplete()) {
            this.assemblerListener.onMessageComplete(this.message, hlPackageParser.getRole());
            this.clearData();
            return;
        }
        this.assemblerListener.onMessageIncomplete(hlPackageParser);
    }

    private boolean isMessageComplete() {
        return this.sequenceState instanceof FinishState;
    }

    private boolean isNewSequenceInvalid() {
        return this.sequenceState instanceof ErrorState;
    }

    private boolean isSpecialExerciseCase(final HLPackageParser hlPackageParser) {
        return hlPackageParser.getMessageType() == HLPackageConstants.TYPE_MESSAGE.EXERCISE_DATA && hlPackageParser.getSequenceType() == HLPackageConstants.TYPE_SEQUENCE.ONE && hlPackageParser.getBodyLength() > hlPackageParser.getMessageType().getMinimumBodyLength();
    }

    private void validateParser(final HLPackageParser hlPackageParser) throws MessageBuildException {
        if (this.sequenceNumber == hlPackageParser.getSequenceNumber() - 1) {
            if (this.sequenceNumber == 255) {
                this.sequenceNumber = 0;
            } else {
                ++this.sequenceNumber;
            }
        } else {
            if (hlPackageParser.getSequenceNumber() != 0 && hlPackageParser.getSequenceNumber() != -1) {
                throw new MessageBuildException("Wrong sequence number!! current : " + this.sequenceNumber + " next : " + hlPackageParser.getSequenceNumber());
            }
            this.sequenceNumber = hlPackageParser.getSequenceNumber();
            this.sequenceState = (SequenceState) new FirstState();
            this.hlPackageParsers.clear();
        }
        final HLPackageConstants.TYPE_SEQUENCE sequenceType = hlPackageParser.getSequenceType();
        this.sequenceState = this.sequenceState.nextState(sequenceType);
        if (this.isNewSequenceInvalid()) {
            throw new MessageBuildException("Invalid sequence type : " + sequenceType + " messageType : " + hlPackageParser.getMessageType());
        }
        this.sequenceType = sequenceType;
        if (this.messageType == null) {
            this.messageType = hlPackageParser.getMessageType();
        } else if (this.messageType != hlPackageParser.getMessageType()) {
            throw new MessageBuildException("Message type not match!! current : " + this.messageType + " next : " + hlPackageParser.getMessageType());
        }
    }

    public HLPackageAssembler append(final HLPackageParser hlPackageParser) throws MessageBuildException {
        try {
            this.validateParser(hlPackageParser);
            this.hlPackageParsers.add(hlPackageParser);
            this.message = this.createMessageIfNull(hlPackageParser.getRole(), hlPackageParser.getMessageType(), hlPackageParser);
            Log.d("HLPackageAssembler", "create message :" + this.message.getClass().getSimpleName());
            if (this.message instanceof HLSeparateMessage) {
                this.buildSeparateMessage(hlPackageParser);
                return this;
            }
            this.buildNormalMessage(hlPackageParser);
            return this;
        } catch (MessageBuildException ex) {
            this.clearData();
            throw ex;
        } catch (UnsupportedOperationException ex2) {
            this.clearData();
            throw new MessageBuildException("create message instance fail!", ex2);
        }
    }

    private static class ErrorState implements SequenceState {
        @Override
        public SequenceState nextState(final HLPackageConstants.TYPE_SEQUENCE type_SEQUENCE) {
            return new ErrorState();
        }
    }

    private static class FinishState implements SequenceState {
        @Override
        public SequenceState nextState(final HLPackageConstants.TYPE_SEQUENCE type_SEQUENCE) {
            return new ErrorState();
        }
    }

    private static class FirstState implements SequenceState {
        @Override
        public SequenceState nextState(final HLPackageConstants.TYPE_SEQUENCE type_SEQUENCE) {
            if (type_SEQUENCE == HLPackageConstants.TYPE_SEQUENCE.BEGIN) {
                return new MiddleState();
            }
            if (type_SEQUENCE == HLPackageConstants.TYPE_SEQUENCE.ONE) {
                return new FinishState();
            }
            return new ErrorState();
        }
    }

    public interface Listener {
        void onMessageComplete(final HLBluetoothMessage p0, final HLPackageConstants.ROLE p1);

        void onMessageIncomplete(final HLPackageParser p0);

        void onSeparateMessageReceived(final HLSeparateMessage p0, final HLPackageParser p1);
    }

    private static class MiddleState implements SequenceState {
        @Override
        public SequenceState nextState(final HLPackageConstants.TYPE_SEQUENCE type_SEQUENCE) {
            if (type_SEQUENCE == HLPackageConstants.TYPE_SEQUENCE.MIDDLE) {
                return new MiddleState();
            }
            if (type_SEQUENCE == HLPackageConstants.TYPE_SEQUENCE.END) {
                return new FinishState();
            }
            return new ErrorState();
        }
    }

    private interface SequenceState {
        SequenceState nextState(final HLPackageConstants.TYPE_SEQUENCE p0);
    }
}
