package com.colormeter.reader;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.util.Log;

import com.colormeter.reader.models.PairedDevice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class ReaderInterface {

    private final static String REQUEST_ENABLE_BT = "bluetoothActivity";
    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }

    public boolean connect(String value) {
        Log.i("connect", value);
        return true;
    }

    public boolean disconnect(String value) {
        Log.i("disconnect", value);
        return true;
    }

    public List<PairedDevice> listPairedDevices(Set<BluetoothDevice> pairedDevices) {
        List<PairedDevice> listResponse = new ArrayList();
        for (BluetoothDevice device : pairedDevices) {
            @SuppressLint("MissingPermission") String deviceName = device.getName();
            String deviceHardwareAddress = device.getAddress(); // MAC address
            listResponse.add(new PairedDevice(deviceHardwareAddress, deviceName, 100, "100%", "disconnected", false, false));
        }
        return listResponse;
    }
}
