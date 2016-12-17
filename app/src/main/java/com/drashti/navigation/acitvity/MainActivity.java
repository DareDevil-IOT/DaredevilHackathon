package com.drashti.navigation.acitvity;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.drashti.navigation.R;
import com.drashti.navigation.location.GPSTracker;
import com.drashti.navigation.location.GpsDirection;
import com.drashti.navigation.services.BluetoothAcceptThread;
import com.drashti.navigation.services.BluetoothService;
import com.drashti.navigation.textToSpeech.Speaker;

import static android.hardware.SensorManager.SENSOR_DELAY_NORMAL;
import static android.hardware.SensorManager.SENSOR_DELAY_UI;
import static android.widget.AdapterView.OnItemSelectedListener;
import static com.drashti.navigation.R.id.spinner;

public class MainActivity extends AppCompatActivity {
    BluetoothService bluetoothService;
    Speaker speaker;
    GPSTracker gps;
    Button btnShowLocation;
    private SensorManager sensorManager;
    private Sensor sensorAccelerometer;
    private Sensor sensorMagneticField;
    private GpsDirection gpsDirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bluetoothService = new BluetoothService(this);
        bluetoothService.createAdaptor();
        speaker = Speaker.getInstance(getApplicationContext());
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(bluetoothService.deviceListAdapter());

        gps = new GPSTracker(this);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMagneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        gpsDirection = new GpsDirection(getApplicationContext());
        sensorManager.registerListener(gpsDirection, sensorAccelerometer, SENSOR_DELAY_NORMAL, SENSOR_DELAY_UI);
        sensorManager.registerListener(gpsDirection, sensorMagneticField, SENSOR_DELAY_NORMAL, SENSOR_DELAY_UI);

        // show location button click event
//        btnShowLocation.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // create class object
//                gps = new GPSTracker(getApplicationContext());
//
//                // check if GPS enabled
//                if (gps.canGetLocation()) {
//
//                    double latitude = gps.getLatitude();
//                    double longitude = gps.getLongitude();
//                    //gps.getLocation().getBearing();
//
//                    // \n is for new line
//                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
//                } else {
//                    // can't get location
//                    // GPS or Network is not enabled
//                    // Ask user to enable GPS/network in settings
//                    gps.showSettingsAlert();
//                }
//
//            }
//        });

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
        sensorManager.registerListener(gpsDirection,
                sensorAccelerometer,
                SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(gpsDirection,
                sensorMagneticField,
                SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(gpsDirection,
                sensorAccelerometer);
        sensorManager.unregisterListener(gpsDirection,
                sensorMagneticField);
        super.onPause();
    }
}
