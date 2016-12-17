package com.drashti.navigation.acitvity;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.drashti.navigation.R;
import com.drashti.navigation.services.BluetoothAcceptThread;
import com.drashti.navigation.services.BluetoothService;

import static android.widget.AdapterView.OnItemSelectedListener;
import static com.drashti.navigation.R.id.spinner;

public class MainActivity extends AppCompatActivity {
    BluetoothService bluetoothService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bluetoothService = new BluetoothService(this);
        bluetoothService.createAdaptor();
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(bluetoothService.deviceListAdapter());

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //bluetoothService.populateListView();
                BluetoothDevice selectedDevice = null;
                if (position >= 1 && position < bluetoothService.getBluetoothDeviceList().size()) {
                    selectedDevice = bluetoothService.getBluetoothDeviceList().get(position);
                    new BluetoothAcceptThread(selectedDevice).start();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();


    }
}
