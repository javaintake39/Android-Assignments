package com.example.abdo.androideaititserver;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.abdo.androideaititserver.Common.Common;
import com.example.abdo.androideaititserver.Interface.ItemClickListener;
import com.example.abdo.androideaititserver.Model.Request;
import com.example.abdo.androideaititserver.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class OrderStatus extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public MaterialSpinner spinner;

    private FirebaseRecyclerAdapter<Request,OrderViewHolder> adapter;
    private FirebaseDatabase db;
    private DatabaseReference requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        // Init FireBase
        db=FirebaseDatabase.getInstance();
        requests=db.getReference("Requests");

        recyclerView=findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

            loadOrders(); //Load All Orders

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getTitle().equals(Common.UPDATE))
        {
          showUpdateDialog(adapter.getRef(item.getOrder()).getKey(),adapter.getItem(item.getOrder()));

        }
        else if(item.getTitle().equals(Common.DELETE))
        {
            deleteOrder(adapter.getRef(item.getOrder()).getKey());
        }
        return super.onContextItemSelected(item);
    }

    private void deleteOrder(String key) {

        requests.child(key).removeValue();
    }

    private void showUpdateDialog(String key, final Request item) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderStatus.this);
        alertDialog.setTitle("Update Order");
        alertDialog.setMessage("Please Choose Order");

        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.update_order_layout, null);

        spinner=view.findViewById(R.id.statusSpinner);

        spinner.setItems("Placed","On My Way","Shipped");

        alertDialog.setView(view);

        final String localKey=key;
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                item.setStatus(String.valueOf(spinner.getSelectedIndex()));
                requests.child(localKey).setValue(item);


            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        alertDialog.show();

    }

    private void loadOrders() {

        final FirebaseRecyclerOptions<Request> options = new FirebaseRecyclerOptions.Builder<Request>()
                .setQuery(requests, Request.class).build();
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
                    viewHolder.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int postion, boolean isLongClick) {

                        }
                    });

                }



            };
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
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
}
