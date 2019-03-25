package com.example.fragmentcounter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class SecondFragment extends Fragment {

    private static final String COUNTER="counter";
    TextView txtCounter;
    int receivedCounter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        txtCounter = view.findViewById(R.id.txtCounter);
        return view;
    }
    public void increaseValue(int count)
    {
        receivedCounter=count;
        txtCounter.setText(Integer.toString(receivedCounter));
    }

    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNTER,receivedCounter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null)
        {
            receivedCounter=savedInstanceState.getInt(COUNTER);
            txtCounter.setText(Integer.toString(receivedCounter));
        }
    }
}
