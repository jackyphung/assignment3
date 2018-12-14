package com.example.jackyphung.lab10;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        textView = findViewById(R.id.textView);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] v = event.values;
        float sqrt = (v[0]*v[0]+v[1]*v[1]+v[2]*v[2])/(SensorManager.GRAVITY_EARTH*SensorManager.GRAVITY_EARTH);
        if(sqrt>=1.5){
            textView.setText("X:"+v[0]+" Y:"+v[1]+" Z:"+v[2]);
            textView.setBackgroundColor(Color.RED);
        }
        else
            textView.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
