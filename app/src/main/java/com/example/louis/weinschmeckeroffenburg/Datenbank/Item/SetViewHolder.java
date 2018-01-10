package com.example.louis.weinschmeckeroffenburg.Datenbank.Item;

import android.support.v7.widget.RecyclerView;
import android.view.View;
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
    public ImageView img_wein;


    public SetViewHolder(View itemView) {
        super(itemView);
        txt_weinname = (TextView) itemView.findViewById(R.id.txt_weinname);
        txt_jahrgang = (TextView) itemView.findViewById(R.id.txt_jahrgang);
        txt_land = (TextView) itemView.findViewById(R.id.txt_land);
        txt_preis = (TextView) itemView.findViewById(R.id.txt_preis);
        img_wein = (ImageView) itemView.findViewById(R.id.weinbild);



    }
}
