package com.colormeter.reader;

import android.util.Log;

import com.colormeter.reader.models.PairedDevice;

import java.util.ArrayList;
import java.util.List;


public class ReaderInterface {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }

    public List<PairedDevice> listPairedDevices() {
        List<PairedDevice> listResponse = new ArrayList();
        listResponse.add(new PairedDevice(0, "Android CM2018920", 85, "85%", "disconnected", true, false));
        listResponse.add(new PairedDevice(1, "Android CM2018920", 55, "55%", "disconnected", true, false));
        return listResponse;
    }
}
