package com.example.countrylistview;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.example.countrylistview.Fragments.Communicator;
import com.example.countrylistview.Fragments.DetailsFragment;
import com.example.countrylistview.Fragments.HomeFragment;
import com.example.countrylistview.dataProccess.CountryDAO;
import com.example.countrylistview.dataProccess.KeyTags;

public class MainActivity extends AppCompatActivity implements Communicator {

    HomeFragment homeFragment;
    DetailsFragment detailsFragment=null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    int orientation;
    CountryDAO countryRetrived;
    private static final int REQUEST_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        if(savedInstanceState==null) // first time
        {
            homeFragment=new HomeFragment();
            fragmentTransaction.add(R.id.mainLayout,homeFragment,"homeFragment");
            fragmentTransaction.commit();
        }
        orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) // In landscape
        {
            detailsFragment=(DetailsFragment) fragmentManager.findFragmentByTag("detailsFragment");
            if(detailsFragment==null)
            {
                detailsFragment=new DetailsFragment();
                fragmentTransaction.add(R.id.Layoutdetails,detailsFragment,"detailsFragment");
                fragmentTransaction.commit();
            }
        }
    }
    @Override
    public void sendData(CountryDAO countryDAO)
    {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) // In landscape
        {
            detailsFragment.sendData(countryDAO);
        }
        else
        {
            moveToNewActivity(countryDAO);
        }
    }

    private void moveToNewActivity(CountryDAO countryDAO)
    {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra(KeyTags.objectKey,countryDAO);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) // In landscape
        {
            if(requestCode==REQUEST_CODE)
            {
                if(resultCode==RESULT_OK)
                {
                    countryRetrived = (CountryDAO) data.getSerializableExtra(KeyTags.objectKey);
                    detailsFragment.sendData(countryRetrived);
                }
            }
        }

    }
}
