package com.example.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BoundService extends Service
{
    private final IBinder myBinder = new MyLocalBiner();

    public BoundService()
    {
    }
    @Override
    public IBinder onBind(Intent intent) {

       return myBinder;
    }

    public String getCurrentTime()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:m:ss   MM/dd/yyyy", Locale.US);
        return (dateFormat.format(new Date()));
    }

    public class MyLocalBiner extends Binder
    {
        public BoundService getService()
        {
            return BoundService.this;
        }
    }
}
