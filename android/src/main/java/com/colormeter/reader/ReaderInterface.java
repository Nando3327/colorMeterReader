package com.colormeter.reader;

import android.annotation.SuppressLint;
import android.app.Application;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.util.Log;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.colormeter.reader.ble.BluetoothManager;
import com.colormeter.reader.models.PairedDevice;

import java.util.ArrayList;
import java.util.List;


public class ReaderInterface {

    private final static String REQUEST_ENABLE_BT = "bluetoothActivity";
    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }

    public void initNueServiceBle(Application app) {
        BleManager.getInstance().init(app);
        BleManager.getInstance()
                .enableLog(true)
                .setReConnectCount(3, 5000)
                .setSplitWriteNum(20)
                .setConnectOverTime(30000)
                .setOperateTimeout(40000);
    }

    public boolean connect(String value) {
        connectBluetoothDevice(value);
        Log.i("connect", value);
        return true;
    }

    public boolean disconnect(String value) {
        Log.i("disconnect", value);
        return true;
    }

    public List<PairedDevice> listPairedDevices(List<BluetoothDevice> pairedDevices) {
        List<PairedDevice> listResponse = new ArrayList();
        for (BluetoothDevice device : pairedDevices) {
            @SuppressLint("MissingPermission") String deviceName = device.getName();
            String deviceHardwareAddress = device.getAddress(); // MAC address
            if(deviceName != null && deviceName.contains("CM")) {
                listResponse.add(new PairedDevice(deviceHardwareAddress, deviceName, 100, "100%", "disconnected", false, false));
            }
        }
        return listResponse;
    }


    private void connectBluetoothDevice(String mac) {
        Log.i("deep", mac);
        BleManager.getInstance().connect(mac, new BleGattCallback() {
            @Override
            public void onStartConnect() {
                Log.i("deep", "onStartConnect");
            }

            @Override
            public void onConnectFail(BleDevice bleDevice, BleException exception) {
                Log.i("deep", "onConnectFail");
            }

            @Override
            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {
                initDevice(bleDevice);
                Log.i("deep", "onConnectSuccess");
            }

            @Override
            public void onDisConnected(boolean isActiveDisConnected, BleDevice device, BluetoothGatt gatt, int status) {
                Log.i("deep", "onDisConnected");
            }
        });
    }

    private void initDevice(BleDevice bleDevice) {
        BluetoothManager.getInstance().connectDevice = bleDevice;
        Log.d("cjq", "onConnectSuccess");
        BluetoothManager.getInstance().setNotify();
        BluetoothManager.getInstance().connect_init = true;
    }


}
