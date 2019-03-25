package com.example.notesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String NOTE_TITLE = "title";
    public static final String NOTE_BODY = "body";

    Button btnNext;
    Button btnClose;
    EditText edtTitle;
    EditText edtBody;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        edtTitle = (EditText) findViewById(R.id.edtNoteTitle);
        edtBody = (EditText) findViewById(R.id.edtBody);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnClose=findViewById(R.id.btnClose);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ActivityTwo.class);
                intent.putExtra(NOTE_TITLE,edtTitle.getText().toString());
                intent.putExtra(NOTE_BODY,edtBody.getText().toString());
                startActivity(intent);
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

    }

}
