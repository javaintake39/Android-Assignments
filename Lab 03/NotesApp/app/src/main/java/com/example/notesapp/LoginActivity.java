package com.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.DataProccess.KeyTags;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button btnLogin,btnRegister;
    EditText edtuserName,edtuserpassword;
    String name,pass;
    String savedName,savedPassword;
    boolean isFirst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(KeyTags.PREF_NAME, Context.MODE_PRIVATE);
        isFirst=sharedPreferences.getBoolean(KeyTags.KEY_LOGIN,true);
        if(isFirst)
        {
        edtuserName=(EditText) findViewById(R.id.edtuserName);
        edtuserpassword=(EditText)findViewById(R.id.edtuserpassword);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnRegister=(Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences(KeyTags.PREF_NAME, Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putString(KeyTags.KEY_USERNAME, KeyTags.MY_NAME);
                editor.putString(KeyTags.KEY_PASS,KeyTags.MY_PASSWORD);
                editor.putBoolean(KeyTags.KEY_LOGIN,KeyTags.ISLOGIN);
                editor.apply();
                Toast.makeText(LoginActivity.this, "Registered Successfully ", Toast.LENGTH_SHORT).show();
                edtuserName.setText(KeyTags.MY_NAME);
                edtuserpassword.setText(KeyTags.MY_PASSWORD);
            }
        });
        // End of Register Button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=edtuserName.getText().toString();
                pass=edtuserpassword.getText().toString();
                if(name ==null ||name.equals("") || pass==null || pass.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "You have To Register", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    savedName=sharedPreferences.getString(KeyTags.KEY_USERNAME,"empty");
                    savedPassword=sharedPreferences.getString(KeyTags.KEY_PASS,"empty");
                    if(validate(savedName,savedPassword))
                    {
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(LoginActivity.this, "Invalid UserName / Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    else
        {
            Toast.makeText(LoginActivity.this, "Welcome Back", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    private boolean validate(String savedName, String savedPassword)
    {
        return (KeyTags.MY_NAME==savedName && KeyTags.MY_PASSWORD ==savedPassword);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finishAffinity();
    }
}
