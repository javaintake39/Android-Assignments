package com.example.abdo.androideatit;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.abdo.androideatit.Database.Database;
import com.example.abdo.androideatit.Model.Food;
import com.example.abdo.androideatit.Model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDetail extends AppCompatActivity {

    private TextView food_name, food_price, food_description;
    private ImageView food_image;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private FloatingActionButton btnCart;
    private ElegantNumberButton numberButton;

    private String foodId = "";
    private FirebaseDatabase database;
    private DatabaseReference foods;
    private Food currentFood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        // Init FireBase
        database = FirebaseDatabase.getInstance();
        foods = database.getReference("Foods");

        // Init View
        food_name = findViewById(R.id.food_name);
        food_price = findViewById(R.id.food_price);
        food_description = findViewById(R.id.food_description);
        food_image = findViewById(R.id.food_image);
        numberButton = findViewById(R.id.number_button);
        btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        foodId,
                        currentFood.getName(),
                        numberButton.getNumber(),
                        currentFood.getPrice(),
                        currentFood.getDiscount()
                ));
                Toast.makeText(FoodDetail.this, "Added To Cart", Toast.LENGTH_SHORT).show();
            }
        });


        collapsingToolbarLayout = findViewById(R.id.collapsing);
        // collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapseAppbar);

        // Get FoodId
        if (getIntent() != null) {
            foodId = getIntent().getStringExtra("FoodId");
            if (!foodId.isEmpty()) {
                getDetailFood(foodId);
            }

        }

    }

    private void getDetailFood(String foodId) {
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                currentFood = dataSnapshot.getValue(Food.class);
                Picasso.get().load(currentFood.getImage()).placeholder(R.drawable.load).into(food_image);
                collapsingToolbarLayout.setTitle(currentFood.getName());
                food_name.setText(currentFood.getName());
                food_price.setText(currentFood.getPrice());
                food_description.setText(currentFood.getDescription());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}