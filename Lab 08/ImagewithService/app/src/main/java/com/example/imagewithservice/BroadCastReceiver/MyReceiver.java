package com.example.imagewithservice.BroadCastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.imagewithservice.HomeActivity;
import com.example.imagewithservice.Model.KeyTags;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action = intent.getAction();
        Log.i("tag", "Broadcast received: " + action);

        if(action.equals("com.example.SendBroadcast"))
        {
            String state = intent.getExtras().getString(KeyTags.urlkKey);
            Intent incomingIntent = new Intent(context, HomeActivity.class);
            incomingIntent.putExtra(KeyTags.receivedkey,state);
            incomingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(incomingIntent);
        }

    }
}
