package com.example.notesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesapp.Model.NoteDAO;
import com.example.notesapp.DataBase.DatabaseAdapter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ActivityTwo extends AppCompatActivity {

    TextView txtTitle;
    TextView txtBody;
    Button btnBack;
    Button btnsaveInternal,btnLoadinternal;
    Button btnsaveSqlite,btnLoadSQl;
    NoteDAO noteDAO;
    DatabaseAdapter adapter;
    String title;
    String body;
    String FILE_NAME = "myfile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        adapter=new DatabaseAdapter(getApplicationContext());

        btnsaveInternal=(Button)findViewById(R.id.btnsaveInternal);
        btnLoadinternal=(Button)findViewById(R.id.btnloadInternal);
        btnsaveSqlite=(Button)findViewById(R.id.btnsaveSQl);
        btnLoadSQl=(Button)findViewById(R.id.btnloadSQl);
        btnBack=(Button) findViewById(R.id.btnBack);
        txtTitle = (TextView)findViewById(R.id.txtTitle);
        txtBody = (TextView)findViewById(R.id.txtBody);
        Intent incomingIntent = getIntent();
        title=incomingIntent.getStringExtra(MainActivity.NOTE_TITLE);
        body=incomingIntent.getStringExtra(MainActivity.NOTE_BODY);
        txtTitle.setText(title);
        txtBody.setText(body);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //************************************************************
        btnsaveInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DataOutputStream outputStream = new DataOutputStream(openFileOutput(FILE_NAME,MODE_PRIVATE));
                    outputStream.writeUTF(title);
                    outputStream.writeUTF(body);
                    txtTitle.setText("");
                    txtBody.setText("");
                    outputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //****************************************************************
        btnLoadinternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DataInputStream inputStream = new DataInputStream(openFileInput(FILE_NAME));
                    txtTitle.setText(inputStream.readUTF());
                    txtBody.setText(inputStream.readUTF());
                    inputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        //*****************************************************************
        btnsaveSqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteDAO=new NoteDAO(title,body);
                adapter.insertNote(noteDAO);
                txtTitle.setText("");
                txtBody.setText("");
                Toast.makeText(ActivityTwo.this, "Saved in SQLite", Toast.LENGTH_SHORT).show();
            }
        });
        //******************************************************************
        btnLoadSQl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                NoteDAO noteDAO = new NoteDAO();
                noteDAO = adapter.getNote(title);
                txtTitle.setText(noteDAO.getNoteTitle());
                txtBody.setText(noteDAO.getNoteBody());
            }
        });
    }
}
