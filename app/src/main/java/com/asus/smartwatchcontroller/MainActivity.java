package com.asus.smartwatchcontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static android.bluetooth.BluetoothAdapter.STATE_CONNECTED;
import static android.bluetooth.BluetoothProfile.STATE_DISCONNECTED;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    BluetoothLeService bluetoothLeService;


    private static final int REQUEST_ENABLE_BT = 0;

    BluetoothAdapter bluetoothAdapter;
    private boolean scanning;
    private Handler handler;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editText = (EditText) findViewById(R.id.console);

        this.handler = new Handler();

        if (this.bluetoothLeService == null) {
            // Bind to LocalService
            Intent intent = new Intent(this, BluetoothLeService.class);
            bindService(intent, this.serviceConnection, Context.BIND_AUTO_CREATE);
        }
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {

            BluetoothLeService.LocalBinder binder = (BluetoothLeService.LocalBinder) service;
            MainActivity.this.bluetoothLeService = binder.getService();

            MainActivity.this.bluetoothLeService.setOnPostMsg(new BluetoothLeService.OnPostMsg() {
                @Override
                public void postMsg(final String msg) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

//                            editText.setText(msg);
                            editText.append(msg + "\n");
                        }
                    });
                }
            });

            // Initializes Bluetooth adapter.
            BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            MainActivity.this.bluetoothAdapter = bluetoothManager.getAdapter();

            if (MainActivity.this.bluetoothAdapter == null || !MainActivity.this.bluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            } else {
                scanLeDevice(true);
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            MainActivity.this.bluetoothLeService = null;
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.bluetoothLeService != null) {
            MainActivity.this.bluetoothLeService.disconnectWatch();
            unbindService(this.serviceConnection);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (MainActivity.REQUEST_ENABLE_BT == requestCode) {

            if (resultCode == RESULT_OK) {

                scanLeDevice(true);
            } else {

            }
        }
    }

    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 10000;

    private void scanLeDevice(final boolean enable) {
        if (enable) {
            // Stops scanning after a pre-defined scan period.
            this.handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MainActivity.this.scanning = false;
                    MainActivity.this.bluetoothAdapter.getBluetoothLeScanner().stopScan(MainActivity.this.scanCallback);
                }
            }, SCAN_PERIOD);

            MainActivity.this.scanning = true;
            MainActivity.this.bluetoothAdapter.getBluetoothLeScanner().startScan(MainActivity.this.scanCallback);
        } else {
            MainActivity.this.scanning = false;
            MainActivity.this.bluetoothAdapter.getBluetoothLeScanner().stopScan(MainActivity.this.scanCallback);
        }
    }

    private ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            BluetoothDevice bluetoothDevice = result.getDevice();

            if (bluetoothDevice.getName() != null && bluetoothDevice.getName().contains(BluetoothLeService.DEVICE_NAME)) {
                MainActivity.this.bluetoothAdapter.getBluetoothLeScanner().stopScan(MainActivity.this.scanCallback);
                MainActivity.this.bluetoothLeService.connectWatch(bluetoothDevice);
            }
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
        }
    };


}
