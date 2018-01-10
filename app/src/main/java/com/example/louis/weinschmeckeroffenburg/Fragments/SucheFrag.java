package com.example.louis.weinschmeckeroffenburg.Fragments;


import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.louis.weinschmeckeroffenburg.Datenbank.DatabaseHelper;
import com.example.louis.weinschmeckeroffenburg.Datenbank.Item.Item;
import com.example.louis.weinschmeckeroffenburg.Datenbank.OnTapListener;
import com.example.louis.weinschmeckeroffenburg.Datenbank.adapter.VocabularyAdapter;
import com.example.louis.weinschmeckeroffenburg.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SucheFrag extends Fragment {

        private  RecyclerView recyclerView;
        private DatabaseHelper databaseHelper;
        private ArrayList<Item> arrayList = new ArrayList<Item>();
        private Cursor cursor;
        private VocabularyAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.list_item,container,false);

recyclerView = viewGroup.findViewById(R.id.recycler_view);
        loadDatabase();
        return viewGroup;
    }
public void loadDatabase(){
        databaseHelper = new DatabaseHelper(getActivity());
        try {
            databaseHelper.checkAndCopyDatabase();
            databaseHelper.openDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            cursor = databaseHelper.QueryData("select * from wein_table");

            if (cursor!=null) {
                if (cursor.moveToFirst()) {
                    do {
                        Item item = new Item();
                        item.setWeinname(cursor.getString(1));
                        item.setJahrgang(cursor.getString(2));
                        item.setLand(cursor.getString(3));
                        item.setPreis(cursor.getString(4));
                        arrayList.add(item);
                    } while (cursor.moveToNext());
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    adapter = new VocabularyAdapter (getActivity(),arrayList);
    adapter.setOnTapListener(new OnTapListener() {
        @Override
        public void OnTapView(int position) {
            Toast.makeText(getContext(),"Click to" + position, Toast.LENGTH_SHORT).show();


        }
    });

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.setAdapter(adapter);

}


    }



