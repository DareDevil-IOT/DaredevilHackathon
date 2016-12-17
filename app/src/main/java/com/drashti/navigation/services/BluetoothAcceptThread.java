package com.drashti.navigation.services;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class BluetoothAcceptThread extends Thread {


    private DataOutputStream streamOut;
    private DataInputStream streamIn;
    private BluetoothDevice selectedDevice;
    private BluetoothSocket socket = null;
    private UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    public BluetoothAcceptThread(BluetoothDevice selectedDevice) {

        this.selectedDevice = selectedDevice;

    }

    public void run() {
        BluetoothSocket socket = null;
        boolean torun;
        try {
            socket = selectedDevice.createRfcommSocketToServiceRecord(uuid);
            socket.connect();
            OutputStream mOutputStream = socket.getOutputStream();
            InputStream mInputStream = socket.getInputStream();
            streamOut = new DataOutputStream(new BufferedOutputStream(mOutputStream));
            streamIn = new DataInputStream(new BufferedInputStream(mInputStream));
            new ObstacleDetector(streamIn);

        } catch (IOException e) {
            e.printStackTrace();
            cancel();
        }


    }

    private void manageConnectedSocket(BluetoothSocket socket) {

    }

    /**
     * Will cancel the listening socket, and cause the thread to finish
     */
    public void cancel() {
        try {


            if (streamIn != null) {

                streamIn.close();
            }
            if (streamOut != null) {
                streamOut.close();
            }
        } catch (IOException e) {
        }
    }
}
