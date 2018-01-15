package com.example.louis.weinschmeckeroffenburg.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.louis.weinschmeckeroffenburg.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingleWineFragBarcode extends Fragment {
    public SingleWineFragBarcode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_wine, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();



      /*  //wineID ausgeben
        TextView tv = (TextView) getActivity().findViewById(R.id.wineID);
        tv.setText(""+this.getArguments().get("wineID").toString()+"");*/

    }



}

