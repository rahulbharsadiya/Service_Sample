package com.example.service_sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.service_sample.Service.MyService;

public class MainActivity extends AppCompatActivity {
    Button butn_start_service, butn_stop_service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butn_start_service = findViewById(R.id.btn_start_service);
        butn_stop_service = findViewById(R.id.btn_stop_service);


        butn_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(v);
            }
        });

        butn_stop_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(v);
            }
        });
    }


    public void stopService(View v) {
        Intent serviceIntent = new Intent(this, MyService.class);
        stopService(serviceIntent);
    }

    private void startService(View v) {
        Intent serviceIntent = new Intent(this, MyService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");

        ContextCompat.startForegroundService(this, serviceIntent);
    }

}
