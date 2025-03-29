package com.colormeter.reader;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;
import static androidx.core.content.ContextCompat.getSystemService;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
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

import java.util.List;
import java.util.Set;

@CapacitorPlugin(name = "ReaderInterface")
public class ReaderInterfacePlugin extends Plugin {

    private final ReaderInterface implementation = new ReaderInterface();
    private final static String REQUEST_ENABLE_BT = "bluetoothActivity";

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

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
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

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        JSObject ret = new JSObject();
        JSONArray mJSONArray = new JSONArray();
        List<PairedDevice> devices = implementation.listPairedDevices(pairedDevices);
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
