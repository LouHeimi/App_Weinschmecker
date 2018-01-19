package com.example.louis.weinschmeckeroffenburg.Datenbank.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.louis.weinschmeckeroffenburg.Datenbank.DatabaseHelper;
import com.example.louis.weinschmeckeroffenburg.Datenbank.Item.Item;
import com.example.louis.weinschmeckeroffenburg.Datenbank.Item.SetViewHolder;
import com.example.louis.weinschmeckeroffenburg.Fragments.SingleWineFrag;
import com.example.louis.weinschmeckeroffenburg.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.squareup.picasso.Picasso;

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

        switch (wine.getImg()) {

            case "wine_0":
                Picasso.with(context).load(R.drawable.wine_0).into(holder.image);
                break;
            case "wine_1":
                Picasso.with(context).load(R.drawable.wine_1).into(holder.image);
                break;
            case "wine_2":
                Picasso.with(context).load(R.drawable.wine_2).into(holder.image);
                break;
            case "wine_3":
                Picasso.with(context).load(R.drawable.wine_3).into(holder.image);
                break;
            case "wine_4":
                Picasso.with(context).load(R.drawable.wine_4).into(holder.image);
                break;
            case "wine_5":
                Picasso.with(context).load(R.drawable.wine_5).into(holder.image);
                break;
            case "wine_6":
                Picasso.with(context).load(R.drawable.wine_6).into(holder.image);
                break;
            case "wine_7":
                Picasso.with(context).load(R.drawable.wine_7).into(holder.image);
                break;
            case "wine_8":
                Picasso.with(context).load(R.drawable.wine_8).into(holder.image);
                break;
            case "wine_9":
                Picasso.with(context).load(R.drawable.wine_9).into(holder.image);
                break;
            case "wine_10":
                Picasso.with(context).load(R.drawable.wine_10).into(holder.image);
                break;
            case "wine_11":
                Picasso.with(context).load(R.drawable.wine_11).into(holder.image);
                break;
            case "wine_12":
                Picasso.with(context).load(R.drawable.wine_12).into(holder.image);
                break;
            case "wine_13":
                Picasso.with(context).load(R.drawable.wine_13).into(holder.image);
                break;
            case "wine_14":
                Picasso.with(context).load(R.drawable.wine_14).into(holder.image);
                break;
            default:
                Picasso.with(context).load(R.drawable.wine_15).into(holder.image);
                break;
        }


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
                bundle.putString("wineImg", wine.getImg());







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


    public void setFilter(List<Item> weine) {
        weinListe = new ArrayList<>();
        weinListe.addAll(weine);
        notifyDataSetChanged();
    }
}
