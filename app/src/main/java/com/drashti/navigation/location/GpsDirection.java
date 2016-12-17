package com.drashti.navigation.location;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

public class GpsDirection implements SensorEventListener {
    float[] mGravity = new float[3];
    float[] mGeomagnetic = new float[3];

    Context context;

    public GpsDirection(Context context) {
        this.context = context;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            for (int i = 0; i < 3; i++) {
                mGravity[i] = event.values[i];
            }
        }
        else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            for (int i = 0; i < 3; i++) {
                mGeomagnetic[i] = event.values[i];
            }
        }
        float R[] = new float[9];
        float outR[] = new float[9];
        float I[] = new float[9];

        boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
        if (success) {
            float orientation[] = new float[3];

            SensorManager.remapCoordinateSystem(R, SensorManager.AXIS_X, SensorManager.AXIS_Y, outR);

            SensorManager.getOrientation(outR, orientation);
            double azimut = orientation[0];

            float degree = (float) (Math.toDegrees(azimut) + 360) % 360;

            //System.out.println("degree " + degree);
            Toast.makeText(context, "Degree is " + degree, Toast.LENGTH_LONG).show();

        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
