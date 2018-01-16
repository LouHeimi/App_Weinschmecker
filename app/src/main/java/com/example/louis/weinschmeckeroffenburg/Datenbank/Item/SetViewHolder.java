package com.example.louis.weinschmeckeroffenburg.Datenbank.Item;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.louis.weinschmeckeroffenburg.R;

/**
 * Created by louis on 21.12.2017.
 */

public class SetViewHolder extends RecyclerView.ViewHolder {
    public TextView txt_weinname;
    public TextView txt_jahrgang;
    public TextView txt_land;
    public TextView txt_preis;
    public ImageView wine_image;
    public ImageButton mButtonHerz;
    public int isFavourite;

    public SetViewHolder(View itemView) {
        super(itemView);
        txt_weinname = itemView.findViewById(R.id.txt_weinname);
        txt_jahrgang = itemView.findViewById(R.id.txt_jahrgang);
        txt_land = itemView.findViewById(R.id.txt_land);
        txt_preis = itemView.findViewById(R.id.txt_preis);
        wine_image = (ImageView) itemView.findViewById(R.id.wine_image);
        mButtonHerz = itemView.findViewById(R.id.Herz);
    }
}
