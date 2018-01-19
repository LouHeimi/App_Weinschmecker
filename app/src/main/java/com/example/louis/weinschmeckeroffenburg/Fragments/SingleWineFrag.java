package com.example.louis.weinschmeckeroffenburg.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.louis.weinschmeckeroffenburg.Datenbank.DatabaseHelper;
import com.example.louis.weinschmeckeroffenburg.Datenbank.Item.Item;
import com.example.louis.weinschmeckeroffenburg.Datenbank.Item.SetViewHolder;
import com.example.louis.weinschmeckeroffenburg.Datenbank.adapter.WineAdapter;
import com.squareup.picasso.Picasso;
import com.example.louis.weinschmeckeroffenburg.R;

import org.bytedeco.javacpp.RealSense;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingleWineFrag extends Fragment {

    private RecyclerView recyclerView;
    private SearchView mSearchView;
    private ArrayList<Item> mWineList;
    private Item wine;
    private WineAdapter mWineAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_single_wine, container, false);

        ImageButton herzBtn = (ImageButton) viewGroup.findViewById(R.id.Herz);


        herzBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        return viewGroup;
    }


    @Override
    public void onStart() {
        super.onStart();
        //wineID ausgeben
        TextView tv = getActivity().findViewById(R.id.TV_wine_name);
        TextView tv_wine_price = getActivity().findViewById(R.id.tv_wine_price);
        TextView tv_wine_year = getActivity().findViewById(R.id.tv_wine_year);
        TextView tv_wine_sort = getActivity().findViewById(R.id.tv_wine_sort);
        TextView tv_wine_taste = getActivity().findViewById(R.id.tv_wine_taste);
        TextView tv_wine_shop = getActivity().findViewById(R.id.tv_wine_shop);
        TextView tv_wine_origin = getActivity().findViewById(R.id.tv_wine_origin);
        TextView tv_wine_content = getActivity().findViewById(R.id.tv_wine_content);

        ImageView iv_wine = getActivity().findViewById(R.id.wine_image);

        String wineID = this.getArguments().get("wineName").toString();
        String wine_price = this.getArguments().get("winePrice").toString();
        String wine_year = this.getArguments().get("wineYear").toString();
        String wine_sort = this.getArguments().get("wineSort").toString();
        String wine_taste = this.getArguments().get("wineTaste").toString();
        String wine_shop = this.getArguments().get("wineShop").toString();
        String wine_origin = this.getArguments().get("wineOrigin").toString();
        String wine_content = this.getArguments().get("wineContent").toString();
String wine_img = this.getArguments().get("wineImg").toString();

        tv.setText(wineID);
        tv_wine_price.setText(wine_price);
        tv_wine_year.setText(wine_year);

        tv_wine_sort.setText(wine_sort);
        tv_wine_taste.setText(wine_taste);
        tv_wine_shop.setText(wine_shop);
        tv_wine_origin.setText(wine_origin);
    tv_wine_content.setText(wine_content);
        //Weine aus der DB auch im SingleWineFrag ausgeben

        switch (wine_img.toString()) {
            case "wine_0":
                Picasso.with(getContext()).load(R.drawable.wine_0).into(iv_wine);
                break;
            case "wine_1":
                Picasso.with(getContext()).load(R.drawable.wine_1).into(iv_wine);
                break;
            case "wine_2":
                Picasso.with(getContext()).load(R.drawable.wine_2).into(iv_wine);
                break;
            case "wine_3":
                Picasso.with(getContext()).load(R.drawable.wine_3).into(iv_wine);
                break;
            case "wine_4":
                Picasso.with(getContext()).load(R.drawable.wine_4).into(iv_wine);
                break;
            case "wine_5":
                Picasso.with(getContext()).load(R.drawable.wine_5).into(iv_wine);
                break;
            case "wine_6":
                Picasso.with(getContext()).load(R.drawable.wine_6).into(iv_wine);
                break;
            case "wine_7":
                Picasso.with(getContext()).load(R.drawable.wine_7).into(iv_wine);
                break;
            case "wine_8":
                Picasso.with(getContext()).load(R.drawable.wine_8).into(iv_wine);
                break;
            case "wine_9":
                Picasso.with(getContext()).load(R.drawable.wine_9).into(iv_wine);
                break;
            case "wine_10":
                Picasso.with(getContext()).load(R.drawable.wine_10).into(iv_wine);
                break;
            case "wine_11":
                Picasso.with(getContext()).load(R.drawable.wine_11).into(iv_wine);
                break;
            case "wine_12":
                Picasso.with(getContext()).load(R.drawable.wine_12).into(iv_wine);
                break;
            case "wine_13":
                Picasso.with(getContext()).load(R.drawable.wine_13).into(iv_wine);
                break;
            case "wine_14":
                Picasso.with(getContext()).load(R.drawable.wine_14).into(iv_wine);
                break;
            default:
                Picasso.with(getContext()).load(R.drawable.wine_15).into(iv_wine);
                break;
        }

    }


}
