package com.example.abdo.androideaititserver.ViewHolder;


import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdo.androideaititserver.Common.Common;
import com.example.abdo.androideaititserver.Interface.ItemClickListener;
import com.example.abdo.androideaititserver.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener,View.OnCreateContextMenuListener

{
    public TextView food_name;
    public ImageView food_image;
    private ItemClickListener itemClickListener;

    public FoodViewHolder(View itemView) {
        super(itemView);
        food_name=itemView.findViewById(R.id.food_name);
        food_image=itemView.findViewById(R.id.food_image);

        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        contextMenu.setHeaderTitle("Select the Action");
        contextMenu.add(0,0,getAdapterPosition(), Common.UPDATE);
        contextMenu.add(0,1,getAdapterPosition(), Common.DELETE);


    }
}