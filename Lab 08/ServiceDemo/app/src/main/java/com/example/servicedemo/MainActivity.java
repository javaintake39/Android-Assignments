package com.example.servicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    BoundService myService;
    boolean isBound = false;
    String currentTime;
    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.MyLocalBiner binder = (BoundService.MyLocalBiner) service;
            myService= binder.getService();
            isBound=true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound=false;
        }
    };

    TextView txtDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,BoundService.class);
        bindService(intent,myConnection , Context.BIND_AUTO_CREATE);
        txtDate=(TextView) findViewById(R.id.txtDate);
    }

    public void showDate(View view)
    {
        currentTime = myService.getCurrentTime();
        txtDate.setText(currentTime);
    }
}
