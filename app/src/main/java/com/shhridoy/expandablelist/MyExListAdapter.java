package com.shhridoy.expandablelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Code Land on 5/9/2017.
 */

public class MyExListAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> item;
    Map<String, List<String>> subitem;

    public MyExListAdapter(Context context, List<String> item, Map<String, List<String>> subitem) {
        this.context = context;
        this.item = item;
        this.subitem = subitem;
    }

    @Override
    public int getGroupCount() {
        return item.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return subitem.get(item.get(i)).size();
    }

    @Override
    public Object getGroup(int groupPositon) {
        return item.get(groupPositon);
    }

    @Override
    public Object getChild(int groupPositon, int childPositon) {
        return subitem.get(item.get(groupPositon)).get(childPositon);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        String title = (String) getGroup(i);

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_parent, null);
        }
        TextView tvparent = (TextView) view.findViewById(R.id.tvParent);
        tvparent.setText(title);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        String subtitle = (String) getChild(i, i1);

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_child, null);
        }
        TextView tvchild = (TextView) view.findViewById(R.id.tvChild);
        tvchild.setText(subtitle);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
