package com.example.firebaselogindmo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.firebaselogindmo.R;
import com.example.firebaselogindmo.model.NoteDAO;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NoteActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText noteTitle,noteBody;
    String title,body;
    Button btnAddNote;
    ListView noteListview ;
    ArrayAdapter<NoteDAO> adapter;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Intent intent = getIntent();
        userId=intent.getStringExtra("user");
        initalize();
        //Toast.makeText(this, userId+" **", Toast.LENGTH_SHORT).show();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        noteListview.setAdapter(adapter);
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title =noteTitle.getText().toString();
                body=noteBody.getText().toString();
                NoteDAO noteDAO = new NoteDAO(title,body);
                myRef.child(myRef.push().getKey()).setValue(noteDAO);
                noteTitle.setText("");
                noteBody.setText("");
            }
        });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                NoteDAO note = dataSnapshot.getValue(NoteDAO.class);
                if(note == null)
                {
                    Toast.makeText(NoteActivity.this, "No Notes To show ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    adapter.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren())
                    {
                        adapter.add(snapshot.getValue(NoteDAO.class));
                    }
                    adapter.notifyDataSetChanged();
                    noteListview.setSelection(adapter.getCount()-1);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        noteListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               NoteDAO noteDAO = (NoteDAO) adapter.getItem(position);
                //Toast.makeText(NoteActivity.this, noteDAO.getTitle()+"\n"+noteDAO.getBody(), Toast.LENGTH_SHORT).show();
                Intent DetailsIntent = new Intent(NoteActivity.this,DetaisActivity.class);
                DetailsIntent.putExtra("note",noteDAO);
                startActivity(DetailsIntent);
            }
        });
    }

    private void initalize()
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Notes").child(userId);
        noteListview = (ListView) findViewById(R.id.noteListview);
        noteTitle=(EditText)findViewById(R.id.noteTitle);
        noteBody=(EditText)findViewById(R.id.noteBody);
        btnAddNote = (Button)findViewById(R.id.btnAddNote);
    }

}
