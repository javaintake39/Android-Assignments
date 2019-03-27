package com.example.abdo.androideatit;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abdo.androideatit.Common.Common;
import com.example.abdo.androideatit.Model.Category;
import com.example.abdo.androideatit.Model.Request;
import com.example.abdo.androideatit.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OrderStatus extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerAdapter<Request,OrderViewHolder> adapter;
    private FirebaseDatabase database;
    private DatabaseReference requests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        // Init FireBase
        database=FirebaseDatabase.getInstance();
        requests=database.getReference("Requests");

        recyclerView=findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(getIntent()==null)
        {
            loadOrders(Common.currentUser.getPhone());
        }
        else
        {
            loadOrders(getIntent().getStringExtra("userPhone"));
        }


    }
    @Override
    protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

    private void loadOrders(String phone) {

        final FirebaseRecyclerOptions<Request> options = new FirebaseRecyclerOptions.Builder<Request>()
                .setQuery(requests.orderByChild("phone").equalTo(phone), Request.class).build();

    adapter=new FirebaseRecyclerAdapter<Request, OrderViewHolder>(options) {

        @Override
        public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout, parent, false);
            return new OrderViewHolder(view);
        }

        @Override
        protected void onBindViewHolder( OrderViewHolder viewHolder, int position,  Request model) {

            viewHolder.txtOrderId.setText(adapter.getRef(position).getKey());
            viewHolder.txtOrderStatus.setText(Common.convertCodeToStatus(model.getStatus()));
            viewHolder.txtOrderAddress.setText(model.getAddress());
            viewHolder.txtOrderPhone.setText(model.getPhone());


        }
    };
    recyclerView.setAdapter(adapter);

    }


}
