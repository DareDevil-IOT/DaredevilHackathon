package com.drashti.navigation.services;


import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

public class BluetoothService {

    public void createAdaptor() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!bluetoothAdapter.isEnabled()){
                Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBT, 1);
            }
        }
    }
}
