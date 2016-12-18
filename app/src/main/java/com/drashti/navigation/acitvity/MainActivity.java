package com.drashti.navigation.acitvity;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.drashti.navigation.R;
import com.drashti.navigation.SpeechManipulator.Speaker;
import com.drashti.navigation.location.GPSTracker;
import com.drashti.navigation.location.GpsDirection;
import com.drashti.navigation.location.GpsListener;
import com.drashti.navigation.services.BluetoothAcceptThread;
import com.drashti.navigation.services.BluetoothService;
import com.drashti.navigation.services.LocationHandler;
import com.drashti.navigation.utils.JsonParser;
import com.drashti.navigation.utils.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.hardware.SensorManager.SENSOR_DELAY_NORMAL;
import static android.hardware.SensorManager.SENSOR_DELAY_UI;
import static android.widget.AdapterView.OnItemSelectedListener;


public class MainActivity extends AppCompatActivity {
    BluetoothService bluetoothService;
    Speaker speaker;
    GPSTracker gps;
    Button btnStartNavigation;
    private SensorManager sensorManager;
    private Sensor sensorAccelerometer;
    private Sensor sensorMagneticField;
    private GpsDirection gpsDirection;
    private JsonParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bluetoothService = new BluetoothService(this);
        bluetoothService.createAdaptor();
        speaker = Speaker.getInstance(getApplicationContext());
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(bluetoothService.deviceListAdapter());
        btnStartNavigation = (Button) findViewById(R.id.button);

        btnStartNavigation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                initialiseNavigation();
            }
        });

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

    private void initialiseNavigation() {
        InputStream inputStream = getResources().openRawResource(R.raw.twoturn);

        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        try {
            parser = JsonReader.parseJson(new BufferedReader(new InputStreamReader(inputStream)));
            LocationHandler locationHandler = new LocationHandler(parser.getAllStep(0, 0), getApplicationContext());
            //gps = new GPSTracker(this);
            //gps.setLocationHandler(locationHandler);
            GpsListener locationListener = new GpsListener(locationHandler, this);
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, 10);
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION
                }, 10);
            }
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 2000, 2, locationListener);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMagneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        gpsDirection = new GpsDirection(getApplicationContext());
        sensorManager.registerListener(gpsDirection, sensorAccelerometer, SENSOR_DELAY_NORMAL, SENSOR_DELAY_UI);
        sensorManager.registerListener(gpsDirection, sensorMagneticField, SENSOR_DELAY_NORMAL, SENSOR_DELAY_UI);
    }


    @Override
    protected void onResume() {
        if (sensorManager != null) {
            sensorManager.registerListener(gpsDirection,
                    sensorAccelerometer,
                    SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(gpsDirection,
                    sensorMagneticField,
                    SENSOR_DELAY_NORMAL);
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(gpsDirection,
                    sensorAccelerometer);
            sensorManager.unregisterListener(gpsDirection,
                    sensorMagneticField);
        }
        super.onPause();
    }
}
