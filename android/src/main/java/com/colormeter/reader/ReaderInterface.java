package com.colormeter.reader;

import android.util.Log;

public class ReaderInterface {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }
}
