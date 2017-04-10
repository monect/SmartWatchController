package com.asus.smartwatchcontroller;

import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.asus.smartwatchcontroller.Exceptions.ChecksumMismatchException;
import com.asus.smartwatchcontroller.Exceptions.InvalidMessageFieldException;
import com.asus.smartwatchcontroller.Exceptions.InvalidPackageLengthException;
import com.asus.smartwatchcontroller.command.AssemblePackageCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BluetoothLeService extends Service {

    private static final String TAG = BluetoothLeService.class.getSimpleName();


    public static final UUID CCCD = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    public static final String DEVICE_NAME = "Impulse 8100 BLE";

    public static final UUID RX_SERVICE_UUID = UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e");
    public static final UUID RX_CHAR_UUID = UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e");
    public static final UUID TX_CHAR_UUID = UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e");

    private final IBinder binder = new LocalBinder();

    private BluetoothGatt bluetoothGatt;

    private int connectionState = BluetoothProfile.STATE_DISCONNECTED;

    private HLAssemblerPool assemblerPool;
    private HLPackageAssembler.Listener listener = new HLPackageAssembler.Listener() {
        @Override
        public void onMessageComplete(HLBluetoothMessage hlBluetoothMessage, HLPackageConstants.ROLE role) {

            Log.e(TAG, "onMessageComplete: " + hlBluetoothMessage + ", " + role);
            if (onPostMsg != null) {
                onPostMsg.postMsg("onMessageComplete: " + hlBluetoothMessage + ", " + role);
            }

//            final OttoBusProxy instance = OttoBusProxy.INSTANCE;
//            if (hlBluetoothMessage instanceof HLImmediateResponseMessage && role == HLPackageConstants.ROLE.SPONSOR) {
//                AssemblePackageService.this.postResponseMessage(hlBluetoothMessage.getType(), -1);
//                AssemblePackageService.this.insertScriptByType(hlBluetoothMessage, role);
//            }
//            instance.post(hlBluetoothMessage);
        }

        @Override
        public void onMessageIncomplete(HLPackageParser hlPackageParser) {
            Log.e(TAG, "onMessageIncomplete: " + hlPackageParser);
            if (onPostMsg != null) {
                onPostMsg.postMsg("onMessageIncomplete: " + hlPackageParser);
            }
            final HLPackageConstants.TYPE_MESSAGE messageType = hlPackageParser.getMessageType();
//            if (hlPackageParser.getRole() == HLPackageConstants.ROLE.RESPONSOR) {
//                OttoBusProxy.INSTANCE.post(new SendNextPackageCommand(messageType, hlPackageParser.getRole(), hlPackageParser.getSequenceNumber()));
//            }

        }

        @Override
        public void onSeparateMessageReceived(HLSeparateMessage hlSeparateMessage, HLPackageParser hlPackageParser) {
            Log.e(TAG, "onSeparateMessageReceived: " + hlSeparateMessage + ", " + hlPackageParser);
            if (onPostMsg != null) {
                onPostMsg.postMsg("onSeparateMessageReceived: " + hlSeparateMessage + ", " + hlPackageParser);
            }
//            final OttoBusProxy instance = OttoBusProxy.INSTANCE;
//            if (hlPackageParser.getRole() == HLPackageConstants.ROLE.SPONSOR) {
//                if (hlPackageParser.getSequenceType() == HLPackageConstants.TYPE_SEQUENCE.ONE) {
//                    AssemblePackageService.this.insertScriptByType(hlSeparateMessage, hlPackageParser.getRole());
//                    AssemblePackageService.this.postResponseMessage(hlSeparateMessage.getType(), -1);
//                } else if (hlPackageParser.getSequenceType() == HLPackageConstants.TYPE_SEQUENCE.END) {
//                    AssemblePackageService.this.insertScriptByType(hlSeparateMessage, hlPackageParser.getRole());
//                    AssemblePackageService.this.postSequenceResponseMessage(hlSeparateMessage.getType(), hlPackageParser.getSequenceNumber(), hlPackageParser.getSequenceType());
//                    if (AssemblePackageService.this.messageOne != null) {
//                        instance.post(AssemblePackageService.this.messageOne);
//                    }
//                } else {
//                    if (hlPackageParser.getSequenceType() == HLPackageConstants.TYPE_SEQUENCE.BEGIN) {
//                        AssemblePackageService.this.messageOne = hlSeparateMessage;
//                    }
//                    AssemblePackageService.this.insertScriptByType(hlSeparateMessage, hlPackageParser.getRole());
//                    AssemblePackageService.this.postSequenceResponseMessage(hlSeparateMessage.getType(), hlPackageParser.getSequenceNumber(), hlPackageParser.getSequenceType());
//                }
//                if (AssemblePackageService.this.messageOne != null && (hlPackageParser.getSequenceType() == HLPackageConstants.TYPE_SEQUENCE.MIDDLE || hlPackageParser.getSequenceType() == HLPackageConstants.TYPE_SEQUENCE.END)) {
//                    AssemblePackageService.this.specialExerciseDataCommand((ExerciseBody) hlSeparateMessage, ((ExerciseHeader) AssemblePackageService.this.messageOne).getTimestamp());
//                }
//            } else {
//                if (hlPackageParser.getRole() != HLPackageConstants.ROLE.RESPONSOR || !hlSeparateMessage.getType().equals(HLPackageConstants.TYPE_MESSAGE.EXERCISE_DATA) || ((ExerciseHeader) hlSeparateMessage).getExerciseCount() <= 0) {
//                    instance.post(hlSeparateMessage);
//                    return;
//                }
//                if (AssemblePackageService.this.messageOne == null) {
//                    AssemblePackageService.this.messageOne = hlSeparateMessage;
//                    return;
//                }
//                if (((ExerciseHeader) hlSeparateMessage).getTimestamp().getTime() != ((ExerciseHeader) AssemblePackageService.this.messageOne).getTimestamp().getTime()) {
//                    AssemblePackageService.this.messageOne = hlSeparateMessage;
//                    return;
//                }
//                AssemblePackageService.this.mWaitCount++;
//                if (AssemblePackageService.this.mWaitCount == 3) {
//                    instance.post(hlSeparateMessage);
//                    AssemblePackageService.this.messageOne = null;
//                    AssemblePackageService.this.mWaitCount = 0;
//                }
//            }

        }
    };

    private void insertCommonScripts(final CommonData commonData) {
//        final ScriptDao scriptDao = HoluxDBHelper.INSTANCE.getDaoSession().getScriptDao();
//        for (final Script script : ScriptFactory.getNormalScripts((Context)this, commonData)) {
//            if (script != null) {
//                ((AbstractDao<Script, K>)scriptDao).insert(script);
//            }
//        }
    }

    private void insertScriptByType(final HLBluetoothMessage hlBluetoothMessage, final HLPackageConstants.ROLE role) {
        if (hlBluetoothMessage instanceof CommonData) {
            this.insertCommonScripts((CommonData) hlBluetoothMessage);
        }
//        else {
//            if (hlBluetoothMessage instanceof DozeData) {
//                this.insertDozeScripts((DozeData)hlBluetoothMessage);
//                return;
//            }
//            if (hlBluetoothMessage instanceof ExerciseBody) {
//                this.insertExerciseScripts((ExerciseBody)hlBluetoothMessage);
//            }
//        }
    }

    private void postResponseMessage(final HLPackageConstants.TYPE_MESSAGE type_MESSAGE, final int code) {
//        final Response message = new Response(type_MESSAGE);
//        try {
//            message.setCode(code);
//            OttoBusProxy.INSTANCE.post(new GenerateNormalPackagesCommand().setMessage(message));
//        } catch (InvalidMessageFieldException ex) {
//            ex.printStackTrace();
//        }
    }

    public BluetoothLeService() {
        this.assemblerPool = HLAssemblerPool.INSTANCE;
        this.assemblerPool.setMessageListener(this.listener);
    }

    public int getConnectionState() {
        return this.connectionState;
    }

    public void connectWatch(BluetoothDevice bluetoothDevice) {
        this.bluetoothGatt = bluetoothDevice.connectGatt(this, false, this.gattCallback);
    }

    public void disconnectWatch() {
        if (this.bluetoothGatt == null) {
            return;
        }
        this.bluetoothGatt.close();
        this.bluetoothGatt = null;
        this.connectionState = BluetoothProfile.STATE_DISCONNECTED;
    }


    private final BluetoothGattCallback gattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            BluetoothLeService.this.connectionState = newState;

            if (newState == BluetoothProfile.STATE_CONNECTED) {
                Log.i(TAG, "Connected to GATT server.");
                Log.i(TAG, "Attempting to start service discovery:" + BluetoothLeService.this.bluetoothGatt.discoverServices());

                if (onPostMsg != null) {
                    onPostMsg.postMsg("Connected to watch.");
                }

            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                Log.i(TAG, "Disconnected from GATT server.");
                if (onPostMsg != null) {
                    onPostMsg.postMsg("Disconnected from watch.");
                }
            }
        }

        @Override
        // New services discovered
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.w(TAG, "onServicesDiscovered received: " + gatt);

//                gatt.getServices();

                enableTXNotification();
            } else {
                Log.w(TAG, "onServicesDiscovered received: " + status);
            }
        }

        @Override
        // Result of a characteristic read operation
        public void onCharacteristicRead(BluetoothGatt gatt,
                                         BluetoothGattCharacteristic characteristic,
                                         int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
            }
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            byte[] value = characteristic.getValue();
            try {
                HLPackage packages = new HLPackage(value);
                Log.i(TAG, "get package : " + DebugHelper.byteArrayToHex(packages.toByteArray()));

                byte type = (byte) (value[0] & 0xf);
                byte checkSum = (byte) 0xff;
                for (int i = 0; i < value.length - 1; i++) {
                    checkSum ^= value[i];
                }

                if (type == 10 && value[value.length - 1] == checkSum) {
                    int time = ((value[2] << 8) & 0xff00) | (value[1] & 0xff);
                    int day = ((time >>> 1) & 0x1f) + 1;
                    int month = ((time >>> 6) & 0xf) + 1;
                    int year = ((time >>> 10) & 0x3f) + 2014;

                    onPostMsg.postMsg("year = " + year + ", month = " + month + "day: " + day + ", doze_current = " + value[3] + ", doze_total = " + value[4]);
                }


//                AssemblePackageCommand assemblePackageCommand = new AssemblePackageCommand();
//                assemblePackageCommand.setHlPackage(packages);
//                assemblePackageCommand.execute();
//                OttoBusProxy.INSTANCE.post(new AssemblePackageCommand().setHlPackage(packages));

            } catch (InvalidPackageLengthException | ChecksumMismatchException e) {
                e.printStackTrace();
            }

        }
    };

    private void enableTXNotification() {
        BluetoothGattService bluetoothGattService = this.bluetoothGatt.getService(RX_SERVICE_UUID);
        if (bluetoothGattService != null) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = bluetoothGattService.getCharacteristic(TX_CHAR_UUID);
            this.bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
            BluetoothGattDescriptor bluetoothGattDescriptor = bluetoothGattCharacteristic.getDescriptor(CCCD);
            bluetoothGattDescriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            this.bluetoothGatt.writeDescriptor(bluetoothGattDescriptor);
        }
    }

    public void sendPackageToDevice(HLPackage paramHLPackage) {
        if (this.bluetoothGatt != null) {
            BluetoothGattService service = this.bluetoothGatt.getService(RX_SERVICE_UUID);
            BluetoothGattCharacteristic character = service.getCharacteristic(RX_CHAR_UUID);
            character.setValue(paramHLPackage.toByteArray());
            this.bluetoothGatt.writeCharacteristic(character);
        }
    }

    private List<ArrayList<BluetoothGattCharacteristic>> gattCharacteristics;

    private final static String LIST_NAME = "LIST_NAME";
    private final static String LIST_UUID = "LIST_UUID";

    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null) return;
        String uuid = null;
        String unknownServiceString = "unknown_service";
        String unknownCharaString = "unknown_characteristic";
        ArrayList<HashMap<String, String>> gattServiceData = new ArrayList<>();
        ArrayList<ArrayList<HashMap<String, String>>> gattCharacteristicData = new ArrayList<>();
        this.gattCharacteristics = new ArrayList<>();

        // Loops through available GATT Services.
        for (BluetoothGattService gattService : gattServices) {
            HashMap<String, String> currentServiceData = new HashMap<>();
            uuid = gattService.getUuid().toString();
            currentServiceData.put(LIST_NAME, uuid);
            currentServiceData.put(LIST_UUID, uuid);
            gattServiceData.add(currentServiceData);

            ArrayList<HashMap<String, String>> gattCharacteristicGroupData = new ArrayList<>();
            List<BluetoothGattCharacteristic> gattCharacteristics = gattService.getCharacteristics();
            ArrayList<BluetoothGattCharacteristic> charas = new ArrayList<>();
            // Loops through available Characteristics.
            for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                charas.add(gattCharacteristic);
                HashMap<String, String> currentCharaData = new HashMap<>();
                uuid = gattCharacteristic.getUuid().toString();
                currentCharaData.put(LIST_NAME, uuid);
                currentCharaData.put(LIST_UUID, uuid);
                gattCharacteristicGroupData.add(currentCharaData);
            }
            this.gattCharacteristics.add(charas);
            gattCharacteristicData.add(gattCharacteristicGroupData);
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return this.binder;
    }

    public class LocalBinder extends Binder {
        BluetoothLeService getService() {
            return BluetoothLeService.this;
        }
    }

    private OnPostMsg onPostMsg;

    public void setOnPostMsg(OnPostMsg onPostMsg) {
        this.onPostMsg = onPostMsg;
    }

    interface OnPostMsg {
        void postMsg(String msg);
    }
}
