package com.colormeter.reader;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;
import static androidx.core.content.ContextCompat.RECEIVER_EXPORTED;
import static androidx.core.content.ContextCompat.RECEIVER_NOT_EXPORTED;
import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.core.content.ContextCompat.registerReceiver;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresPermission;
import androidx.core.app.ActivityCompat;

import com.colormeter.reader.models.PairedDevice;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@CapacitorPlugin(name = "ReaderInterface")
public class ReaderInterfacePlugin extends Plugin {

    private final ReaderInterface implementation = new ReaderInterface();
    private final static String REQUEST_ENABLE_BT = "bluetoothActivity";

    private List<BluetoothDevice> devicesFound = new ArrayList<>();

    @PluginMethod
    public void valueDetected(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void connect(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.connect(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void disconnect(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.disconnect(value));
        call.resolve(ret);
    }


    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        // onReceive method of the BroadcastReceiver
        @Override
        public void onReceive(Context context, Intent intent) {
            // Retrieve the action string from the received intent
            String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)) {
                System.out.println("***********  Discovery started  ***********");
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                System.out.println("***********  Discovery finished  ***********");
            }

            // Check if the current action is "ACTION_FOUND" in order to proceed with processing the found device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the intent's extra data
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Get the Received Signal Strength Indicator (RSSI) value from the intent's extra data
                devicesFound.add(device);
            }
        }
    };

    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_SCAN})
    @PluginMethod
    public void listPairedDevices(PluginCall call) throws JSONException {
        Context appcontext = getActivity().getApplicationContext();
        BluetoothManager bluetoothManager = (BluetoothManager) appcontext.getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            return;
        }

        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(call, enableBtIntent, REQUEST_ENABLE_BT);
        }

        if (bluetoothAdapter.isDiscovering()){
            bluetoothAdapter.cancelDiscovery();
        }



        IntentFilter discoveryFilter = new IntentFilter();
        discoveryFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        discoveryFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        discoveryFilter.addAction(BluetoothDevice.ACTION_FOUND);
        registerReceiver(appcontext, receiver, discoveryFilter, RECEIVER_EXPORTED);
        bluetoothAdapter.startDiscovery();

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        bluetoothAdapter.cancelDiscovery();
        JSObject ret = new JSObject();
        JSONArray mJSONArray = new JSONArray();
        List<PairedDevice> devices = implementation.listPairedDevices(devicesFound);
        try
        {
            for (int i=0; i< devices.size(); i++)
            {
                JSONObject jObjd=new JSONObject();
                jObjd.put("id", devices.get(i).getMacAddress());
                jObjd.put("name", devices.get(i).getName());
                jObjd.put("batteryLevel", devices.get(i).getBatteryLevel());
                jObjd.put("batteryLevelString", devices.get(i).getBatteryLevelString());
                jObjd.put("status", devices.get(i).getStatus());
                jObjd.put("whiteCalibration", devices.get(i).isWhiteCalibration());
                jObjd.put("blackCalibration", devices.get(i).isBlackCalibration());
                mJSONArray.put(jObjd);
            }
            Log.e("Test", mJSONArray.toString());
        }
        catch(JSONException ex)
        {
            Log.i("paired devices", devices.toString());
            Log.i("paired devices error", ex.toString());
        }
        ret.putSafe("devices", mJSONArray);
        call.resolve(ret);
    }
}
