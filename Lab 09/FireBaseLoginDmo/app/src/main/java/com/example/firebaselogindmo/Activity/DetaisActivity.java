package com.example.firebaselogindmo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.firebaselogindmo.R;
import com.example.firebaselogindmo.model.NoteDAO;

public class DetaisActivity extends AppCompatActivity {

    TextView titleDetails,bodyDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detais);
        titleDetails=(TextView)findViewById(R.id.titleDetails);
        bodyDetails=(TextView)findViewById(R.id.bodyDetails);
        NoteDAO noteDAO = (NoteDAO) getIntent().getSerializableExtra("note");
        titleDetails.setText(noteDAO.getTitle());
        bodyDetails.setText(noteDAO.getBody());
    }
}
