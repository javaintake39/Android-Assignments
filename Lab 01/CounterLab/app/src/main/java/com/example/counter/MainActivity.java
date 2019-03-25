package com.example.counter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    int counter=0;
    TextView txtCounter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCounter = findViewById(R.id.txtcounter);
        if(savedInstanceState!=null)

        {
            counter=savedInstanceState.getInt("countervalue");
            counter++;
            txtCounter.setText(Integer.toString(counter));
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("countervalue",counter);
    }
}