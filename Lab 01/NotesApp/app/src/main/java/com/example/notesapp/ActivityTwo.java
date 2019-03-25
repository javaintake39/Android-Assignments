package com.example.notesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {

    TextView txtTitle;
    TextView txtBody;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        btnBack=(Button) findViewById(R.id.btnBack);
        txtTitle = (TextView)findViewById(R.id.txtTitle);
        txtBody = (TextView)findViewById(R.id.txtBody);
        Intent incomingIntent = getIntent();
        String title=incomingIntent.getStringExtra(MainActivity.NOTE_TITLE);
        String body=incomingIntent.getStringExtra(MainActivity.NOTE_BODY);
        txtTitle.setText(title);
        txtBody.setText(body);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
