package com.drashti.navigation.services;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;


public class BluetoothService {

    private BluetoothAdapter bluetoothAdapter;
    private Context context;
    private List<BluetoothDevice> bluetoothDeviceList;

    public BluetoothService(Context context) {
        this.context = context;
        this.bluetoothDeviceList = new ArrayList<>();
    }

    public List<BluetoothDevice> getBluetoothDeviceList() {
        return bluetoothDeviceList;
    }

    public void createAdaptor() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!bluetoothAdapter.isEnabled()) {
            return;
        }

    }


    @NonNull
    public ArrayAdapter<String> deviceListAdapter() {
        bluetoothDeviceList = new ArrayList<>();
        List<String> deviceNameList = new ArrayList<>();
        deviceNameList.add("None");
        bluetoothDeviceList.add(null);
        for (BluetoothDevice bluetoothDevice : bluetoothAdapter.getBondedDevices()) {
            bluetoothDeviceList.add(bluetoothDevice);
            deviceNameList.add(bluetoothDevice.getName());
        }
        return new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item, deviceNameList);
    }


}

