package com.example.listviewdaysviewholder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class DaysCustomAdapter extends ArrayAdapter<DaysDAO>
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

        View row=currentView;
        ViewHolder viewHolder;
        if(row == null)
        {
            counter++;
            Log.i("counter",""+counter);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(resource,listView,false);
            viewHolder=new ViewHolder(row);
            row.setTag(viewHolder);
        }
        else
        {
            Log.i("counter","Created");
            viewHolder =(ViewHolder)row.getTag();
        }
        viewHolder.getImgDay().setImageResource(days[position].getImgRes());
        viewHolder.getTxtName().setText(days[position].getName());
        viewHolder.getTxtDesc().setText(days[position].getDesc());
        return row;
    }
}
