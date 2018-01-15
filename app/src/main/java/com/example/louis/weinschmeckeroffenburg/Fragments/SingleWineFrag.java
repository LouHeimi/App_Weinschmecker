package com.example.louis.weinschmeckeroffenburg.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.louis.weinschmeckeroffenburg.Fragments_old.SucheFrag;
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

    @Override
    public void onStart() {
        super.onStart();
        //wineID ausgeben
        TextView tv = getActivity().findViewById(R.id.TV_wine_name);
        String wineID = this.getArguments().get("wineName").toString();

        Button backToListButton = getActivity().findViewById(R.id.B_back_to_list);

        backToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SucheFrag sucheFrag = new SucheFrag();
                getFragmentManager().beginTransaction().replace(R.id.content, sucheFrag, sucheFrag.getTag()).commit();
            }
        });

        tv.setText(wineID);
    }

}
