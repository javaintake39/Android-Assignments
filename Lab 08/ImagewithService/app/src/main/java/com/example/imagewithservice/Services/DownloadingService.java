package com.example.imagewithservice.Services;

import android.app.IntentService;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.imagewithservice.BroadCastReceiver.MyReceiver;
import com.example.imagewithservice.Model.KeyTags;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadingService extends IntentService {
    String receiveURL,savedURL;
    Bitmap resultBitmapImage = null;
    MyReceiver receiver;
    public DownloadingService() {
        super("DownloadingService");
    }
    @Override
    protected void onHandleIntent(Intent intent)
    {
        receiveURL = intent.getStringExtra(KeyTags.imageKey);
        try {
            resultBitmapImage = downloadImage(receiveURL);
            savedURL = saveToInternalStorage(resultBitmapImage);
            Log.i("tag",savedURL);
            sendBroadCastMessage(savedURL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendBroadCastMessage(String savedURL)
    {
        IntentFilter filter = new IntentFilter("com.example.SendBroadcast");
        receiver = new MyReceiver();
        registerReceiver(receiver,filter);

        Intent broadCastIntent = new Intent();
        broadCastIntent.putExtra(KeyTags.urlkKey,savedURL);
        broadCastIntent.setAction("com.example.SendBroadcast");
        broadCastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(broadCastIntent);

        //unregisterReceiver(receiver);
    }
    private Bitmap downloadImage(String url) throws IOException {
        Bitmap result=null;
        URL urlObject ;
        HttpURLConnection httpURLConnection;
        InputStream inputStream = null;
        try {
            urlObject=new URL(url);
            httpURLConnection = (HttpURLConnection)urlObject.openConnection();
            httpURLConnection.connect();
            inputStream= httpURLConnection.getInputStream();
            result = BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(inputStream !=null)
                inputStream.close();
        }
        return result;
    }

    private String saveToInternalStorage(Bitmap bitmapImage)
    {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
