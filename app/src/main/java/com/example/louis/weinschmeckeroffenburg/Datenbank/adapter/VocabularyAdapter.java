package com.example.louis.weinschmeckeroffenburg.Datenbank.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.louis.weinschmeckeroffenburg.Datenbank.Item.Item;
import com.example.louis.weinschmeckeroffenburg.Datenbank.Item.SetViewHolder;
import com.example.louis.weinschmeckeroffenburg.Datenbank.OnTapListener;
import com.example.louis.weinschmeckeroffenburg.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by louis on 21.12.2017.
 */

public class VocabularyAdapter extends RecyclerView.Adapter<SetViewHolder> {


    private Activity activity;
    List<Item> items= Collections.emptyList();
    private OnTapListener onTapListener;

    public VocabularyAdapter (Activity activity,List<Item> items) {
        this.activity = activity;
        this.items = items;
    }



    @Override
    public SetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,parent,false);

        return new SetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SetViewHolder holder, final int position) {
        holder.txt_weinname.setText(items.get(position).getWeinname());
        holder.txt_jahrgang.setText(items.get(position).getJahrgang());
        holder.txt_land.setText(items.get(position).getLand());
        holder.txt_preis.setText(items.get(position).getPreis());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


             if (onTapListener !=null) {
                    onTapListener.OnTapView(position);
                }





            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnTapListener(OnTapListener onTapListener) {

        this.onTapListener=onTapListener;
    }
}
