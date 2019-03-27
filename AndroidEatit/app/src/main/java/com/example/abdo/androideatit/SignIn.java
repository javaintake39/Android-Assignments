package com.example.abdo.androideatit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abdo.androideatit.Common.Common;
import com.example.abdo.androideatit.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    private EditText edtPhone,edtPassword;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        edtPhone=findViewById(R.id.edtPhone);
        edtPassword=findViewById(R.id.edtPassword);
        btnSignIn=findViewById(R.id.btnSignIn);

        // Init FireBase Here
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Check_Internet_Connection())
                {
                    final ProgressDialog mDialog=new ProgressDialog(SignIn.this);
                    mDialog.setMessage("Please wait ....");
                    mDialog.show();
                    table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            String Phone=edtPhone.getText().toString().trim();
                            String Pass=edtPassword.getText().toString().trim();
                            if(dataSnapshot.child(Phone).exists())
                            {
                                mDialog.dismiss();
                                // Get User Information
                                User user = dataSnapshot.child(Phone).getValue(User.class);
                                user.setPhone(Phone);
                                if (user.getPassword().equals(Pass)) {
                                    // Toast.makeText(SignIn.this, "Welcome "+user.getName(), Toast.LENGTH_SHORT).show();
                                    Intent homeIntent=new Intent(SignIn.this,Home.class);
                                    Common.currentUser=user;
                                    startActivity(homeIntent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(SignIn.this, "Password Not Correct ", Toast.LENGTH_SHORT).show();
                                }

                            }
                            else
                            {
                                mDialog.dismiss();
                                Toast.makeText(SignIn.this, "User Not Exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(SignIn.this, "Check Internent Conncetion", Toast.LENGTH_SHORT).show();
                }

                }


        });

    }
    private boolean Check_Internet_Connection()
    {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
