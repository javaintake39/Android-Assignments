package com.example.abdo.androideatit.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.abdo.androideatit.Interface.ItemClickListener;
import com.example.abdo.androideatit.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtOrderId,txtOrderStatus,txtOrderPhone,txtOrderAddress;
    private ItemClickListener itemClickListener;

    public OrderViewHolder(View itemView) {
        super(itemView);
        txtOrderId=itemView.findViewById(R.id.order_id);
        txtOrderStatus=itemView.findViewById(R.id.order_status);
        txtOrderPhone=itemView.findViewById(R.id.order_phone);
        txtOrderAddress=itemView.findViewById(R.id.order_address);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
