package com.example.listviewdaysviewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    private View currentView;
    private ImageView imgDay;
    private TextView txtName;
    private TextView txtDesc;

    public ViewHolder(View currentView) {
        this.currentView = currentView;
    }

    public View getCurrentView()
    {
        return currentView;
    }

    public ImageView getImgDay()
    {
        if(imgDay == null)
            imgDay=currentView.findViewById(R.id.imgDay);
        return imgDay;
    }

    public TextView getTxtName()
    {
        if(txtName ==null)
            txtName=currentView.findViewById(R.id.txtName);
        return txtName;
    }

    public TextView getTxtDesc()
    {
        if(txtDesc == null)
            txtDesc=currentView.findViewById(R.id.txtDesc);
        return txtDesc;
    }
}
