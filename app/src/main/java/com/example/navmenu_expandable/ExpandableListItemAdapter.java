package com.example.navmenu_expandable;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.annotation.IntegerRes;

import java.util.HashMap;
import java.util.List;

public class ExpandableListItemAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> groupHeader;
    private HashMap<String, List<String>> groupHeaderItems;
    private List<Drawable> leftDrawable;

    public ExpandableListItemAdapter(Context context, List<String> groupHeader, HashMap<String, List<String>> groupHeaderItems) {
        this.context = context;
        this.groupHeader = groupHeader;
        this.groupHeaderItems = groupHeaderItems;
    }

    public ExpandableListItemAdapter(Context context, List<String> groupHeader, HashMap<String, List<String>> groupHeaderItems, List<Drawable> leftDrawable) {
        this.context = context;
        this.groupHeader = groupHeader;
        this.groupHeaderItems = groupHeaderItems;
        this.leftDrawable = leftDrawable;
    }

    @Override
    public int getGroupCount() {
        return groupHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groupHeaderItems.get(groupHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupHeaderItems.get(groupHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitles = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item_group, null);
        }

        TextView header = convertView.findViewById(R.id.groupHeaderTitles);
        header.setText(headerTitles);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String items = (String) getChild(groupPosition,childPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item_content, null);
        }

        TextView header = convertView.findViewById(R.id.groupHeadercontent);
        header.setText(items);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
