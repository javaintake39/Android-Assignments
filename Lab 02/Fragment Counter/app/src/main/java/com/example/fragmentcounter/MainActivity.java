package com.example.fragmentcounter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Communicator{

    SecondFragment secondFragment;
    FirstFragment firstFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();

        if(savedInstanceState==null) // first time
        {
            firstFragment=new FirstFragment();
            secondFragment=new SecondFragment();
            fragmentTransaction.add(R.id.firstLayout,firstFragment,"firstFragment");
            fragmentTransaction.add(R.id.secondLayout,secondFragment,"secondFragment");
            fragmentTransaction.commit();
        }
        else
        {
            secondFragment=(SecondFragment)fragmentManager.findFragmentByTag("secondFragment");
        }
    }

    @Override
    public void increaseValue(int count)
    {
        secondFragment.increaseValue(count);
    }

}
