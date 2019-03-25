package com.example.sharedpref;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs",MODE_PRIVATE);
        boolean isFirst=sharedPreferences.getBoolean("myFirstTime",true);
        if(isFirst)
        {
            Toast.makeText(this, "First Time To Run :D", Toast.LENGTH_SHORT).show();
        }
        sharedPreferences.edit().putBoolean("myFirstTime",false).apply();
    }
}
