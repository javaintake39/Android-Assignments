package com.example.imagewithservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.imagewithservice.Model.KeyTags;
import com.example.imagewithservice.Services.DownloadingService;

public class MainActivity extends AppCompatActivity {
    EditText edtUrl;
    String url;
    Button btnDownload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                url=edtUrl.getText().toString();
                Intent intent = new Intent(MainActivity.this, DownloadingService.class);
                intent.putExtra(KeyTags.imageKey,url);
                startService(intent);
            }
        });
    }

    private void initComponents()
    {
        edtUrl=(EditText)findViewById(R.id.edtUrl);
        btnDownload=(Button)findViewById(R.id.btnDownload);
    }
}
