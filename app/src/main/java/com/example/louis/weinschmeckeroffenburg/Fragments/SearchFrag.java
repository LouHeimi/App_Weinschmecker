package com.example.louis.weinschmeckeroffenburg.Fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SearchView;
import com.example.louis.weinschmeckeroffenburg.Datenbank.DatabaseHelper;
import com.example.louis.weinschmeckeroffenburg.Datenbank.Item.Item;
import com.example.louis.weinschmeckeroffenburg.Datenbank.adapter.WineAdapter;
import com.example.louis.weinschmeckeroffenburg.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFrag extends Fragment {

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
    private void setupSearch() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<Item> filteredWineList = filter(mWineList, newText);

                mWineAdapter.setFilter(filteredWineList);
                return true;
            }


            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            private List<Item> filter(List<Item> items, String query) {
                query = query.toLowerCase();
                final List<Item> filteredWineList = new ArrayList<>();
                for (Item item : items) {
                    final String text = item.getWeinname().toLowerCase();
                    if (text.contains(query)) {
                        filteredWineList.add(item);
                    }
                }
                return filteredWineList;
            }

        });
    }

public void loadDataFromDatabase() {
       /*Weine werden aus der Datenbank gelesen und weiter an den Wein-Adapter gegeben,
       dann in die Recyclerview übergeben*/
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        mWineList = databaseHelper.getAllWineFromDB();
        mWineAdapter = new WineAdapter(getActivity(), getFragmentManager(), mWineList, databaseHelper);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mWineAdapter);
    }
}
