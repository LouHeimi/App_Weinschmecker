package com.example.louis.weinschmeckeroffenburg.Fragments_old;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.louis.weinschmeckeroffenburg.R;


/**
 * Created by louis on 28.11.2017.
 */

public class FragmentEinstellungen extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_einstellungen, container, false);
    }
}
