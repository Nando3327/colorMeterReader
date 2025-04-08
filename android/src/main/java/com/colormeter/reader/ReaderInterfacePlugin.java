package com.colormeter.reader;

import static androidx.core.content.ContextCompat.RECEIVER_EXPORTED;
import static androidx.core.content.ContextCompat.registerReceiver;

import android.Manifest;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import androidx.annotation.RequiresPermission;

import com.clj.fastble.BleManager;
import com.colormeter.reader.bean.parse.AdjustBean;
import com.colormeter.reader.bean.parse.DeviceInfoBean;
import com.colormeter.reader.bean.parse.MeasureBean;
import com.colormeter.reader.bean.parse.ReadLabMeasureDataBean;
import com.colormeter.reader.bean.parse.ReadMeasureDataBean;
import com.colormeter.reader.bean.parse.ReadRgbMeasureDataBean;
import com.colormeter.reader.bean.parse.StandardSampleDataBean;
import com.colormeter.reader.bean.parse.struct.DeviceInfoStruct;
import com.colormeter.reader.models.PairedDevice;
import com.colormeter.reader.util.Constant;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@CapacitorPlugin(name = "ReaderInterface")
public class ReaderInterfacePlugin extends Plugin {

    private final ReaderInterface implementation = new ReaderInterface();
    private final static String REQUEST_ENABLE_BT = "bluetoothActivity";

    private List<BluetoothDevice> devicesFound = new ArrayList<>();

    Context appcontext;
    BluetoothManager bluetoothManager;
    BluetoothAdapter bluetoothAdapter;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        // onReceive method of the BroadcastReceiver
        @Override
        public void onReceive(Context context, Intent intent) {
            // Retrieve the action string from the received intent
            String action = intent.getAction();
            if (action != null) {
                Log.i("deep", action);
            }
            Serializable data = intent.getSerializableExtra("data");
            byte state = intent.getByteExtra("state", (byte) 0x00);
            short standardNum = intent.getShortExtra("standard_num", (short) 0);
            short sampleNum = intent.getShortExtra("sample_num", (short) 0);
            short standardCount = intent.getShortExtra("standard_count", (short) 0);
            short sampleCount = intent.getShortExtra("sample_count", (short) 0);
            switch (action) {
                case Constant.BLACK_ADJUST:
                    onBlackAdjust((AdjustBean) data);
                    break;
                case Constant.WHITE_ADJUST:
                    onWhiteAdjust((AdjustBean) data);
                    break;
                case Constant.MEASURE:
                    onMeasure((MeasureBean) data);
                    break;
                case Constant.READ_MEASURE_DATA:
                    onReadMeasureData((ReadMeasureDataBean) data);
                    break;
                case Constant.READ_LAB_MEASURE_DATA:
                    onReadLabMeasureData((ReadLabMeasureDataBean) data);
                    break;
                case Constant.READ_RGB_MEASURE_DATA:
                    onReadRgbMeasureData((ReadRgbMeasureDataBean) data);
                    break;
                case Constant.GET_STANDARD_DATA_COUNT:
                    onGetStandardCount(standardCount);
                    break;
                case Constant.GET_STANDARD_DATA_FOR_NUM:
                    onGetStandardDataForNumber((StandardSampleDataBean.StandardDataBean) data);
                    break;
                case Constant.DELETE_ALL_STANDARD_DATA:
                    onDeleteAllStandardData(state);
                    break;
                case Constant.DELETE_STANDARD_DATA_FOR_NUM:
                    onDeleteStandardDataForNumber(standardNum, state);
                    break;
                case Constant.GET_SAMPLE_COUNT_FOR_STANDARD_NUM:
                    onGetSampleCountForStandardNumber(standardNum, state, sampleCount);
                    break;
                case Constant.GET_NUM_SAMPLE_DATA_FOR_NUM_STANDARD:
                    onGetNumSampleDataForNumStandard(standardNum, sampleNum, state,
                            (StandardSampleDataBean.SampleDataBean) data);
                    break;
                case Constant.DELETE_ALL_SAMPLE_FOR_STANDARD_NUM:
                    onDeleteAllSampleForStandardNum(standardNum, state);
                    break;
                case Constant.DELETE_NUM_SAMPLE_DATA_FOR_NUM_STANDARD:
                    onDeleteNumSampleDataForNumStandard(standardNum, sampleNum, state);
                    break;
                case Constant.POST_STANDARD_DATA:
                    onPostStandardData(state);
                    break;
                case Constant.GET_DEVICE_INFO:
                    onGetDeviceInfo((DeviceInfoStruct) data);
                    break;
                case Constant.GET_DEVICE_POWER_INFO:
                    onGetDevicePowerInfo((DeviceInfoBean.PowerInfo) data);
                    break;
                case Constant.GET_DEVICE_ADJUST_STATE:
                    onGetDeviceAdjustState((DeviceInfoBean.DeviceAdjustState) data);
                    break;
                case Constant.SET_DEVICE_DISPLAY_PARAM:
                    onSetDeviceDisplayParam(state);
                    break;
                case Constant.SET_TOLERANCE:
                    onSetTolerance(state);
                    break;
                case Constant.SET_BLUETOOTH:
                    onSetBluetooth(state);
                    break;
                case Constant.SET_BLUETOOTH_NAME:
                    onSetBluetoothName(state);
                    break;
                case Constant.SET_POWER_MANAGEMENT_TIME:
                    onSetPowerManagementTime(state);
                    break;
                case Constant.SET_DEVICE_TIME:
                    onSetDeviceTime(state);
                    break;
                case Constant.SET_SAVE_MODE:
                    onSetSaveMode(state);
                    break;
                case Constant.ON_FAIL:
                    String failType = intent.getStringExtra(Constant.ON_FAIL);
                    onFail(failType);
                    break;
                case BluetoothDevice.ACTION_FOUND:
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    devicesFound.add(device);
                    break;
            }
        }
    };


    public void onBlackAdjust(AdjustBean bean) {

    }

    public void onWhiteAdjust(AdjustBean bean) {

    }

    public void onMeasure(MeasureBean bean) {
        implementation.measure(bean);
    }

    public void onReadMeasureData(ReadMeasureDataBean bean) {

    }

    public void onReadLabMeasureData(ReadLabMeasureDataBean bean) {
        float[] lab = bean.getLab();
    }

    public void onReadRgbMeasureData(ReadRgbMeasureDataBean bean) {

    }

    public void onGetStandardCount(short count) {

    }

    public void onGetStandardDataForNumber(StandardSampleDataBean.StandardDataBean standardDataBean) {

    }

    public void onDeleteAllStandardData(byte state) {

    }

    public void onDeleteStandardDataForNumber(short standardNum, byte state) {

    }

    public void onGetSampleCountForStandardNumber(short standardNum, byte state, short sampleCount) {

    }

    public void onGetNumSampleDataForNumStandard(short standardNum, short sampleNum,
                                                 byte state, StandardSampleDataBean.SampleDataBean sampleDataBean) {

    }

    public void onDeleteAllSampleForStandardNum(short standardNum, byte state) {

    }

    public void onDeleteNumSampleDataForNumStandard(short standardNum, short sampleNum, byte state) {

    }

    public void onPostStandardData(byte state) {

    }

    public void onGetDeviceInfo(DeviceInfoStruct deviceInfoStruct) {

    }

    public void onGetDevicePowerInfo(DeviceInfoBean.PowerInfo powerInfo) {

    }

    public void onGetDeviceAdjustState(DeviceInfoBean.DeviceAdjustState deviceAdjustState) {

    }

    public void onSetDeviceDisplayParam(byte state) {

    }

    public void onSetTolerance(byte state) {

    }

    public void onSetBluetooth(byte state) {

    }

    public void onSetPowerManagementTime(byte state) {

    }

    public void onSetDeviceTime(byte state) {

    }

    public void onSetSaveMode(byte state) {

    }

    public void onSetBluetoothName(byte state) {

    }

    public void onFail(String failType) {

    }

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void valueDetected(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("l", "10");
        ret.put("a", "10");
        ret.put("b", "20");
        call.resolve(ret);
    }

    @PluginMethod
    public void getReaderCalibrationStatus(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("black", implementation.getReaderCalibrationStatus().black);
        ret.put("white", implementation.getReaderCalibrationStatus().white);
        call.resolve(ret);
    }

    @PluginMethod
    public void calibrateWhite(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("calibrated", implementation.calibrateWhite());
        call.resolve(ret);
    }

    @PluginMethod
    public void calibrateBlack(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("calibrated", implementation.calibrateBlack());
        call.resolve(ret);
    }

    @PluginMethod
    public void isReaderConnected(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("value", implementation.isReaderConnected());
        call.resolve(ret);
    }

    @PluginMethod
    public void initNueServiceBle(PluginCall call) {
        appcontext = getActivity().getApplicationContext();
        bluetoothManager = (BluetoothManager) appcontext.getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();

        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BLACK_ADJUST);
        filter.addAction(Constant.WHITE_ADJUST);
        filter.addAction(Constant.MEASURE);
        filter.addAction(Constant.READ_MEASURE_DATA);
        filter.addAction(Constant.READ_LAB_MEASURE_DATA);
        filter.addAction(Constant.READ_RGB_MEASURE_DATA);
        filter.addAction(Constant.GET_STANDARD_DATA_COUNT);
        filter.addAction(Constant.GET_STANDARD_DATA_FOR_NUM);
        filter.addAction(Constant.DELETE_ALL_STANDARD_DATA);
        filter.addAction(Constant.DELETE_STANDARD_DATA_FOR_NUM);
        filter.addAction(Constant.GET_SAMPLE_COUNT_FOR_STANDARD_NUM);
        filter.addAction(Constant.GET_NUM_SAMPLE_DATA_FOR_NUM_STANDARD);
        filter.addAction(Constant.DELETE_ALL_SAMPLE_FOR_STANDARD_NUM);
        filter.addAction(Constant.DELETE_NUM_SAMPLE_DATA_FOR_NUM_STANDARD);
        filter.addAction(Constant.POST_STANDARD_DATA);
        filter.addAction(Constant.GET_DEVICE_INFO);
        filter.addAction(Constant.GET_DEVICE_POWER_INFO);
        filter.addAction(Constant.GET_DEVICE_ADJUST_STATE);
        filter.addAction(Constant.SET_DEVICE_DISPLAY_PARAM);
        filter.addAction(Constant.SET_TOLERANCE);
        filter.addAction(Constant.SET_BLUETOOTH);
        filter.addAction(Constant.SET_POWER_MANAGEMENT_TIME);
        filter.addAction(Constant.SET_DEVICE_TIME);
        filter.addAction(Constant.SET_SAVE_MODE);
        filter.addAction(Constant.SET_BLUETOOTH_NAME);
        filter.addAction(Constant.ON_FAIL);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        registerReceiver(appcontext, receiver, filter, RECEIVER_EXPORTED);

        implementation.initNueServiceBle((Application) appcontext, appcontext);
        call.resolve();
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
        JSObject ret = new JSObject();
        ret.put("value", implementation.disconnect());
        call.resolve(ret);
    }

    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_SCAN})
    @PluginMethod
    public void listPairedDevices(PluginCall call) throws JSONException {
        devicesFound.clear();
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
                jObjd.put("macAddress", devices.get(i).getMacAddress());
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
