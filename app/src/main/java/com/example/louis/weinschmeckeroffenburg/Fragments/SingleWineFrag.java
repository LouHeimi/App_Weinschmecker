package com.example.louis.weinschmeckeroffenburg.Fragments;


import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.louis.weinschmeckeroffenburg.Datenbank.DatabaseHelper;
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
        TextView tv_wine_price = getActivity().findViewById(R.id.tv_wine_price);
        TextView tv_wine_year = getActivity().findViewById(R.id.tv_wine_year);
        TextView tv_wine_sort = getActivity().findViewById(R.id.tv_wine_sort);
        TextView tv_wine_taste = getActivity().findViewById(R.id.tv_wine_taste);
        TextView tv_wine_shop = getActivity().findViewById(R.id.tv_wine_shop);
        TextView tv_wine_origin = getActivity().findViewById(R.id.tv_wine_origin);
        TextView tv_wine_content = getActivity().findViewById(R.id.tv_wine_content);
        ImageView iv_wine_img = getActivity().findViewById(R.id.imageView);


        String wineID = this.getArguments().get("wineName").toString();
        String wine_price = this.getArguments().get("winePrice").toString();
        String wine_year = this.getArguments().get("wineYear").toString();
        String wine_sort = this.getArguments().get("wineSort").toString();
        String wine_taste = this.getArguments().get("wineTaste").toString();
        String wine_shop = this.getArguments().get("wineShop").toString();
        String wine_origin = this.getArguments().get("wineOrigin").toString();
        String wine_content = this.getArguments().get("wineContent").toString();
        String wine_img = this.getArguments().get("wineImg").toString();


     /*Falls wir ein Zurück-Knopf zur Liste nochhaben wollen, aber eher nicht userfreundlich

        Button backToListButton = getActivity().findViewById(R.id.B_back_to_list);


        backToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SearchFrag searchFrag = new SearchFrag();
                getFragmentManager().beginTransaction().replace(R.id.content, searchFrag, searchFrag.getTag()).commit();
            }
        }); */





        tv.setText(wineID);
        tv_wine_price.setText(wine_price);
        tv_wine_year.setText(wine_year);

        tv_wine_sort.setText(wine_sort);
        tv_wine_taste.setText(wine_taste);
        tv_wine_shop.setText(wine_shop);
        tv_wine_origin.setText(wine_origin);
        tv_wine_content.setText(wine_content);
        iv_wine_img.setImageResource(R.drawable.weinflasche);


    }

}
