package com.example.fragmentlifecycle;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment extends Fragment {

    private static final String TAG="lifecycle";
    public MyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG,"(Fragment) onAttach ");
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"(Fragment) onCreate ");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.i(TAG,"(Fragment) onCreateView ");
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG,"(Fragment) onActivityCreated ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG,"(Fragment) onStart ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"(Fragment) onResume ");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"(Fragment) onPause ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"(Fragment) onStop ");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG,"(Fragment) onDestroyView ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"(Fragment) onDestroy ");
    }
    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG,"(Fragment) onDetach ");
    }
}
