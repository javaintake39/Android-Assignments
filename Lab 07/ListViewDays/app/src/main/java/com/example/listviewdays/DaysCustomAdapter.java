package com.example.listviewdays;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DaysCustomAdapter extends ArrayAdapter <DaysDAO>
{
    Context context;
    int resource;
    int textViewResourceId;
    DaysDAO[] days;
    private  static  int counter=0;
    public DaysCustomAdapter(Context context, int resource, int textViewResourceId, DaysDAO[] days)
    {
        super(context, resource, textViewResourceId, days);
        this.context=context;
        this.resource=resource;
        this.textViewResourceId=textViewResourceId;
        this.days=days;
    }

    @Override
    public View getView(int position , View currentView , ViewGroup listView)
    {
        counter++;
        Log.i("counter"," "+counter);
        View row=currentView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(resource,listView,false);
        ImageView imageView= row.findViewById(R.id.imgDay);
        TextView txtName=row.findViewById(R.id.txtName);
        TextView txtDesc=row.findViewById(R.id.txtDesc);

        imageView.setImageResource(days[position].getImgRes());
        txtName.setText(days[position].getName());
        txtDesc.setText(days[position].getDesc());
        return row;
    }
}
