package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText edtUrl;
    ImageView imageView;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUrl=(EditText) findViewById(R.id.edtUrl);
        imageView=(ImageView)findViewById(R.id.imgview);
    }

    public void check(View view) // when I Click Download Button
    {
        if(checkInternetConnection())
        {
            url=edtUrl.getText().toString();
            new MyAsyncTask().execute(url);
        }
        else
        {
            Toast.makeText(this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkInternetConnection() // checkInternetConnection
    {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private class MyAsyncTask extends AsyncTask <String, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... url)
        {
            Bitmap result = null;
            try {
                result = downloadImage(url [0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            Toast.makeText(MainActivity.this, "Downloaded", Toast.LENGTH_SHORT).show();
            imageView.setImageBitmap(bitmap);
        }
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
}
