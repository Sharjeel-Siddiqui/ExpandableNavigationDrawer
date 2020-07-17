package com.example.navmenu_expandable;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private ExpandableListView expandableListView;
    private ExpandableListItemAdapter expandableListItemAdapter;
    private List<String> groupHeaders;
    private HashMap<String, List<String>> groupitems;
    private List<Drawable> drawables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer);
        expandableListView = findViewById(R.id.elv);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, null,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        initData();
        expandableListItemAdapter = new ExpandableListItemAdapter(this, groupHeaders, groupitems, drawables);
        expandableListView.setAdapter(expandableListItemAdapter);


        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                String check = groupitems.get(groupHeaders.get(groupPosition)).get(childPosition);
                Toast.makeText(MainActivity.this, "click :" + check, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void initData() {
        groupHeaders = new ArrayList<>();
        groupitems = new HashMap<>();
        drawables = new ArrayList<>();

        groupHeaders.add("Group item 1");
        groupHeaders.add("Group item 2");
        groupHeaders.add("Group item 3");

        List<String> groupOneItems = new ArrayList<>();
        groupOneItems.add("item One");
        groupOneItems.add("item Two");
        groupOneItems.add("item Three");

        List<String> groupTwoItems = new ArrayList<>();
        groupTwoItems.add("item Four");
        groupTwoItems.add("item Five");
        groupTwoItems.add("item Six");

        List<String> groupThreeItems = new ArrayList<>();

        groupitems.put(groupHeaders.get(0), groupOneItems);
        groupitems.put(groupHeaders.get(1), groupTwoItems);
        groupitems.put(groupHeaders.get(2), groupThreeItems);

        drawables.add(getDrawable(R.drawable.stop));
        drawables.add(getDrawable(R.drawable.stop));
        drawables.add(getDrawable(R.drawable.stop));
        drawables.add(getDrawable(R.drawable.stop));
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
