package com.example.listviewdaysviewholder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DaysDAO [] days={
            new DaysDAO("Saturday","Saturday Description",R.drawable.one),
            new DaysDAO("Sunday","Sunday Description",R.drawable.two),
            new DaysDAO("Monday","Monday Description",R.drawable.three),
            new DaysDAO("Tuesday","Tuesday Description",R.drawable.four),
            new DaysDAO("Wednesday","Wednesday Description",R.drawable.five),
            new DaysDAO("Thursday","Thursday Description",R.drawable.six),
            new DaysDAO("Friday","Friday Description",R.drawable.seven),
    };
    ListView myList;
    DaysCustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList= (ListView)findViewById(R.id.myList);
        adapter=new DaysCustomAdapter(this,R.layout.custom_single_row,R.id.txtName,days);
        myList.setAdapter(adapter);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, parent.getItemAtPosition(position).toString()
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }
}



