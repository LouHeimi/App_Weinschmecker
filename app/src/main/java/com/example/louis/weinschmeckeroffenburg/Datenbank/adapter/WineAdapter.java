package com.example.louis.weinschmeckeroffenburg.Datenbank.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.louis.weinschmeckeroffenburg.Datenbank.DatabaseHelper;
import com.example.louis.weinschmeckeroffenburg.Datenbank.Item.Item;
import com.example.louis.weinschmeckeroffenburg.Datenbank.Item.SetViewHolder;
import com.example.louis.weinschmeckeroffenburg.Fragments.FavouriteFrag;
import com.example.louis.weinschmeckeroffenburg.Fragments.SingleWineFrag;
import com.example.louis.weinschmeckeroffenburg.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by louis on 21.12.2017.
 */

public class WineAdapter extends RecyclerView.Adapter<SetViewHolder> {

    private final FragmentManager mFragmentManager;
    private Context context;
    List<Item> weinListe = Collections.emptyList();
    private DatabaseHelper mDatabaseHelper;

    public WineAdapter(Context context, FragmentManager fragmentManager, List<Item> weinListe, DatabaseHelper databaseHelper) {
        this.context = context;
        this.weinListe = weinListe;
        mDatabaseHelper = databaseHelper;
        mFragmentManager = fragmentManager;
    }

    @Override
    public SetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wine_list_item, parent, false);

        return new SetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SetViewHolder holder, final int position) {
        final Item wine = weinListe.get(position);
        holder.txt_weinname.setText(wine.getWeinname());
        holder.txt_jahrgang.setText(wine.getJahrgang());
        holder.txt_land.setText(wine.getLand());
        holder.txt_preis.setText(wine.getPreis());

        holder.isFavourite = wine.getIsFavourite();

        if (wine.getIsFavourite() == 1) {
            holder.mButtonHerz.setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.herz));
        } else {
            holder.mButtonHerz.setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.herzleer));
        }

        holder.mButtonHerz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.isFavourite == 1) {
                    holder.mButtonHerz.setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.herzleer));
                    holder.isFavourite = 0;
                    wine.setIsFavourite(0);

                } else {
                    holder.mButtonHerz.setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.herz));
                    holder.isFavourite = 1;
                    wine.setIsFavourite(1);
                }
                mDatabaseHelper.updateWine(wine);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleWineFrag singleWineFrag = new SingleWineFrag();
                Bundle bundle= new Bundle();
                bundle.putString("wineName", wine.getWeinname());
                bundle.putString("winePrice", wine.getPreis());
                bundle.putString("wineYear", wine.getJahrgang());
                bundle.putString("wineSort", wine.getArt());
                bundle.putString("wineTaste", wine.getGeschmack());
                bundle.putString("wineShop", wine.getLaden());
                bundle.putString("wineOrigin", wine.getLand());
                bundle.putString("wineContent", wine.getContent());

                singleWineFrag.setArguments(bundle);
                mFragmentManager.beginTransaction().replace(R.id.content, singleWineFrag, singleWineFrag.getTag()).commit();


            }
        });




        }

    @Override
    public int getItemCount() {
        return weinListe.size();
    }

    public List<Item> getWeinListe() {
        return weinListe;
    }

    public void setWeinListe(List<Item> weinListe) {
        this.weinListe = weinListe;
        notifyDataSetChanged();
    }
}
