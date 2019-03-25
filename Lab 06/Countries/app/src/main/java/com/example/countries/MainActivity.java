package com.example.countries;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.countries.dataProccess.CountryDAO;
import com.example.countries.dataProccess.JsonParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    TextView txtRank,txtCountry,txtPopulation;
    Button btnPrev,btnNext;
    ImageView imgFlag;
    private final static String api = "https://www.androidbegin.com/tutorial/jsonparsetutorial.txt";
    JsonParser parser = new JsonParser();
    List<CountryDAO> arrayList;
    public int counter=0;
    private Handler handler;
    MyAsyncTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        if(checkInternetConnection())
        {
            GetJson getJson = new GetJson();
            getJson.start();
        }
        else {
            Toast.makeText(this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        handler = new MyHandler(this);
    }

    public void showData(int counter)
    {
        ClearText();
        txtRank.setText(arrayList.get(counter).getRank());
        txtCountry.setText(arrayList.get(counter).getCountry());
        txtPopulation.setText(arrayList.get(counter).getPopulation());
        task= new MyAsyncTask(MainActivity.this);
        task.execute(arrayList.get(counter).getFlag());
    }

    public void initComponents()
    {
        txtRank=(TextView)findViewById(R.id.txtRank);
        txtCountry=(TextView)findViewById(R.id.txtCountry);
        txtPopulation=(TextView)findViewById(R.id.txtPopulation);
        btnPrev=(Button)findViewById(R.id.btnPrev) ;
        btnNext=(Button)findViewById(R.id.btnNext);
        imgFlag=(ImageView)findViewById(R.id.imgFlag);
    }

    public void ClearText()
    {
        txtRank.setText("");
        txtCountry.setText("");
        txtPopulation.setText("");
    }

    private class GetJson extends Thread {
        @Override
        public void run()
        {
            String JsonObjectAsString=getJsonObjectFromURL(api);
            arrayList = parser.JsonProcess(JsonObjectAsString);
            handler.sendEmptyMessage(0);
        }
    }

    public String getJsonObjectFromURL(String urlLink)
    {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String data="";
        try {
            StringBuffer buffer = new StringBuffer();
            URL url = new URL(urlLink);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while ((line = reader.readLine()) != null) {

                buffer.append(line + "\n");
            }
            data = buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;

    }

    private boolean checkInternetConnection()
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
}
