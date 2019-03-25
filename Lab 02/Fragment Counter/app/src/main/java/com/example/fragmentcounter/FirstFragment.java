package com.example.fragmentcounter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class FirstFragment extends Fragment {

    private static final String COUNTER="counter";
    Button btnCounter;
    Communicator communicator;
    private int counter =0;
    public FirstFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
       View view = inflater.inflate(R.layout.fragment_first, container, false);
        if (savedInstanceState != null)
        {
            counter=savedInstanceState.getInt(COUNTER);
        }
        btnCounter = (Button)view.findViewById(R.id.btnCounter);
        btnCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                counter++;
                communicator.increaseValue(counter);
            }
        });
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        communicator=((Communicator) getActivity());

    }
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNTER,counter);
    }

}
