package com.shhridoy.expandablelist.mListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.shhridoy.expandablelist.R;
import com.shhridoy.expandablelist.mDataObject.Spacecraft;

import java.util.ArrayList;

/**
 * Created by Code Land on 5/10/2017.
 */

public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Spacecraft> spacecrafts;
    LayoutInflater inflater;
    Spacecraft spacecraftObj;

    public CustomAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int i) {
        return spacecrafts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        if (inflater == null){
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null){
            view = inflater.inflate(R.layout.model, viewGroup, false);
        }

        //BIND DATA
        MyViewHolder holder = new MyViewHolder(view);
        holder.nameTxt.setText(spacecrafts.get(position).getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c, spacecrafts.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.setLongClickListener(new MyLongClickListener() {
            @Override
            public void onItemLongClick() {
                spacecraftObj = (Spacecraft) getItem(position);
            }
        });

        return view;
    }

    //EXPOSE NAME AND ID
    public int getSelectedItemId(){
        return spacecraftObj.getId();
    }

    public String getSelectedItemName(){
        return spacecraftObj.getName();
    }
}
