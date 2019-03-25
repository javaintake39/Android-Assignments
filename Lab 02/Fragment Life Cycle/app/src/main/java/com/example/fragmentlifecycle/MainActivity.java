package com.example.fragmentlifecycle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="lifecycle";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"(Activity) onCreate ");
        setContentView(R.layout.activity_main);

        DynamicFragment dynamicFragment=new DynamicFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.layoutThree,dynamicFragment,"myfragment");
        fragmentTransaction.commit();
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"(Activity) onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"(Activity) onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"(Activity) onPause");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"(Activity) onSaveInstanceState");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"(Activity) onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"(Activity) onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"(Activity) onDestroy");
    }

}