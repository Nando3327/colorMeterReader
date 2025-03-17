package com.colormeter.reader;

import android.util.Log;

import com.colormeter.reader.models.PairedDevice;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

@CapacitorPlugin(name = "ReaderInterface")
public class ReaderInterfacePlugin extends Plugin {

    private ReaderInterface implementation = new ReaderInterface();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void listPairedDevices(PluginCall call) throws JSONException {
        JSObject ret = new JSObject();
        JSONArray mJSONArray = new JSONArray();
        List<PairedDevice> devices = implementation.listPairedDevices();
        try
        {
            for (int i=0; i< devices.size(); i++)
            {
                JSONObject jObjd=new JSONObject();
                jObjd.put("id", devices.get(i).getId());
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

        }

        ret.putSafe("devices", mJSONArray);
        call.resolve(ret);
    }
}
