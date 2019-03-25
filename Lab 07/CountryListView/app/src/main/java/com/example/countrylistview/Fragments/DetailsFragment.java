package com.example.countrylistview.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.countrylistview.MyAsyncTask;
import com.example.countrylistview.R;
import com.example.countrylistview.dataProccess.CountryDAO;
import com.example.countrylistview.dataProccess.KeyTags;

public class DetailsFragment extends Fragment {

    TextView txtRank,txtCountry,txtPopulation;
    public ImageView imgFlag;
    CountryDAO countryDAO;
    public DetailsFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        txtRank=(TextView)view.findViewById(R.id.txtRank);
        txtCountry=(TextView)view.findViewById(R.id.txtCountry);
        txtPopulation=(TextView)view.findViewById(R.id.txtPopulation);
        imgFlag=(ImageView) view.findViewById(R.id.imgFlag);
        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Bundle bundle = this.getArguments();
        if(bundle != null)
        {
            countryDAO = (CountryDAO) getArguments().getSerializable(KeyTags.objectKey);
            setData(countryDAO);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KeyTags.objectKey,countryDAO);
    }

    public void sendData(CountryDAO countryDAO)
    {
        setData(countryDAO);
    }

    public void setData(CountryDAO countryDAO)
    {
        txtRank.setText(countryDAO.getRank());
        txtCountry.setText(countryDAO.getCountry());
        txtPopulation.setText(countryDAO.getPopulation());
        Log.i("img",countryDAO.getFlag());
        new MyAsyncTask(DetailsFragment.this).execute(countryDAO.getFlag());
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
