package com.asus.smartwatchcontroller;

import java.util.WeakHashMap;

/**
 * Created by Monect on 25/11/2016.
 */

public class MessageFactory {
    private DateAck dateAckHolder;
    private WeakHashMap<HLPackageConstants.TYPE_MESSAGE, HLBluetoothMessage> messageHolder;
    private Request requestHolder;
    private Response responseHolder;

    public MessageFactory() {
        this.messageHolder = new WeakHashMap<HLPackageConstants.TYPE_MESSAGE, HLBluetoothMessage>(2);
    }

    public HLBluetoothMessage createDateAck(final HLPackageConstants.TYPE_MESSAGE type_MESSAGE) {
        if (this.dateAckHolder == null) {
            this.dateAckHolder = new DateAck(type_MESSAGE);
        } else {
            this.dateAckHolder.reset(type_MESSAGE);
        }
        return this.dateAckHolder;
    }

    public HLBluetoothMessage createMessageByRole(final HLPackageConstants.ROLE role, final HLPackageConstants.TYPE_MESSAGE type_MESSAGE, final int n) {
        switch (role) {
            default: {
                throw new IllegalArgumentException("Impossible role!! " + role);
            }
            case RESPONSOR: {
                return this.createResponse(type_MESSAGE, n);
            }
            case SPONSOR: {
                return this.createRequest(type_MESSAGE);
            }
        }
    }

    public HLBluetoothMessage createMessageByType(final HLPackageConstants.TYPE_MESSAGE type_MESSAGE) {
        HLBluetoothMessage classInstance;
        if ((classInstance = this.messageHolder.get(type_MESSAGE)) == null) {
            classInstance = type_MESSAGE.getClassInstance();
        }
        return classInstance;
    }

    public HLBluetoothMessage createNormalRequest(final HLPackageConstants.TYPE_MESSAGE type_MESSAGE) {
        if (this.requestHolder == null) {
            this.requestHolder = new Request(type_MESSAGE);
        } else {
            this.requestHolder.reset(type_MESSAGE);
        }
        return this.requestHolder;
    }

    public HLBluetoothMessage createNormalResponse(final HLPackageConstants.TYPE_MESSAGE type_MESSAGE) {
        if (this.responseHolder == null) {
            this.responseHolder = new Response(type_MESSAGE);
        } else {
            this.responseHolder.reset(type_MESSAGE);
        }
        return this.responseHolder;
    }

    public HLBluetoothMessage createRequest(final HLPackageConstants.TYPE_MESSAGE type_MESSAGE) {
        switch (type_MESSAGE) {
            default: {
                throw new UnsupportedOperationException("SPEC NOT DEFINE THIS TYPE OF REQUEST " + type_MESSAGE.toString());
            }
            case WEATHER:
            case GPS:
            case DEV_SETTING:
            case SYNC_TIME:
            case DEV_STATUS:
            case DEV_BIND: {
                return this.createNormalRequest(type_MESSAGE);
            }
            case COMMON_DATA:
            case SLEEP_DATA:
            case DOZE_DATA:
            case EXERCISE_DATA: {
                return this.createDateAck(type_MESSAGE);
            }
        }
    }

    public HLBluetoothMessage createResponse(final HLPackageConstants.TYPE_MESSAGE type_MESSAGE, final int n) {
        if (type_MESSAGE == HLPackageConstants.TYPE_MESSAGE.COMMON_DATA || type_MESSAGE == HLPackageConstants.TYPE_MESSAGE.SLEEP_DATA || type_MESSAGE == HLPackageConstants.TYPE_MESSAGE.DOZE_DATA) {
            return this.createDateAck(type_MESSAGE);
        }
        if (n == -1) {
            return this.createNormalResponse(type_MESSAGE);
        }
        return this.createSequenceResponse(type_MESSAGE, n);
    }

    public HLBluetoothMessage createSequenceResponse(final HLPackageConstants.TYPE_MESSAGE type_MESSAGE, final int n) {
        return new SequenceResponse(type_MESSAGE, n);
    }

    public HLBluetoothMessage createSpecialExerciseData() {
        return new SpecialExerciseData();
    }
}