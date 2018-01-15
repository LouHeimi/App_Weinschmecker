package com.example.louis.weinschmeckeroffenburg.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.louis.weinschmeckeroffenburg.Datenbank.DatabaseHelper;
import com.example.louis.weinschmeckeroffenburg.Datenbank.Item.Item;
import com.example.louis.weinschmeckeroffenburg.Datenbank.adapter.WineAdapter;
import com.example.louis.weinschmeckeroffenburg.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFrag extends Fragment {

    private RecyclerView recyclerView;
    private SearchView mSearchView;
    private ArrayList<Item> mWineList;
    private WineAdapter mWineAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.search_wine_list, container, false);

        mSearchView = viewGroup.findViewById(R.id.SV_suche);
        recyclerView = viewGroup.findViewById(R.id.recycler_view);

        loadDataFromDatabase();
        setupSearch();

        return viewGroup;
    }

    public void loadDataFromDatabase() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        mWineList = databaseHelper.getAllFavouriteWineFromDB();

        mWineAdapter = new WineAdapter(getActivity(), getFragmentManager(), mWineList, databaseHelper);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mWineAdapter);
    }

    private void setupSearch() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                filterWineList(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterWineList(s);
                return false;
            }
        });
    }

    private void filterWineList(String s) {
        ArrayList<Item> tmpList = new ArrayList<>();
        for (Item wine : mWineList) {
            if (wine.getWeinname().equals(s)) {
                tmpList.add(wine);
            }
        }
        mWineAdapter.setWeinListe(tmpList);
    }
}
