package com.shhridoy.expandablelist.mListView;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.shhridoy.expandablelist.R;

/**
 * Created by Code Land on 5/10/2017.
 */

public class MyViewHolder implements View.OnLongClickListener, View.OnCreateContextMenuListener{
    TextView nameTxt;
    MyLongClickListener longClickListener;

    public MyViewHolder(View v) {
        nameTxt = (TextView) v.findViewById(R.id.nameTV);
        v.setOnLongClickListener(this);
        v.setOnCreateContextMenuListener(this);
    }

    @Override
    public boolean onLongClick(View view) {
        this.longClickListener.onItemLongClick();
        return false;
    }

    public void setLongClickListener(MyLongClickListener longClickListener){
        this.longClickListener = longClickListener;
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Action : ");
        contextMenu.add(0, 0, 0, "NEW");
        contextMenu.add(0, 1, 0, "EDIT");
        contextMenu.add(0, 2, 0, "DELETE");
    }
}
