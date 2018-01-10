package com.example.louis.weinschmeckeroffenburg.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.louis.weinschmeckeroffenburg.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingleWineFrag extends Fragment {


    public SingleWineFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment




        return inflater.inflate(R.layout.fragment_single_wine, container, false);

    }

}
