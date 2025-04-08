package com.colormeter.reader;

import android.annotation.SuppressLint;
import android.app.Application;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.content.Context;
import android.util.Log;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.colormeter.reader.bean.parse.MeasureBean;
import com.colormeter.reader.ble.BluetoothManager;
import com.colormeter.reader.models.PairedDevice;
import com.colormeter.reader.util.Constant;

import java.util.ArrayList;
import java.util.List;


public class ReaderInterface {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }

    public void measure(MeasureBean bean) {
        BluetoothManager.getInstance().measureMode = bean.getMeasureMode();
        BluetoothManager.getInstance().setOrder(Constant.READ_LAB_MEASURE_DATA);
        BluetoothManager.getInstance().postOrder();
    }

    public void initializeLABMeasure() {
        BluetoothManager.getInstance().setOrder(Constant.MEASURE);
    }

    public boolean calibrateWhite() {
        BluetoothManager.getInstance().setOrder(Constant.WHITE_ADJUST);
//        BluetoothManager.getInstance().postOrder();
        return true;
    }

    public boolean calibrateBlack() {
        BluetoothManager.getInstance().setOrder(Constant.BLACK_ADJUST);
//        BluetoothManager.getInstance().postOrder();
        return true;
    }

    public void getReaderCalibrationStatus() {
        BluetoothManager.getInstance().setOrder(Constant.GET_DEVICE_ADJUST_STATE);
        BluetoothManager.getInstance().postOrder();
    }

    public boolean isReaderConnected() {
        return BluetoothManager.getInstance().connectDevice != null;
    }

    public void initNueServiceBle(Application app, Context context) {
        BluetoothManager.getInstance().init(context);
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

    public boolean disconnect() {
        BleManager.getInstance().disconnect(BluetoothManager.getInstance().connectDevice);
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
