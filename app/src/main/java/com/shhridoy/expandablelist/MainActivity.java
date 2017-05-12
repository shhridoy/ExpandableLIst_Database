package com.shhridoy.expandablelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<String> item;
    Map<String, List<String>> subitem;
    ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        expandableListView = (ExpandableListView) findViewById(R.id.ExpandableListView);
        fillData();

        expandableListAdapter = new MyExListAdapter(this, item, subitem);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                Toast.makeText(MainActivity.this, item.get(i)+" : "+subitem.get(item.get(i)).get(i1), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    public void fillData(){
        item = new ArrayList<>();
        subitem = new HashMap<>();

        item.add("Hollywood");
        item.add("Bollywood");
        item.add("Dhallywood");

        List<String> hollywood = new ArrayList<>();
        hollywood.add("Avatar");
        hollywood.add("Warm Bodies");
        hollywood.add("Avengers");

        List<String> bollywood = new ArrayList<>();
        bollywood.add("3 Idiots");
        bollywood.add("Wanted");
        bollywood.add("PK");

        List<String> dhallywood = new ArrayList<>();
        dhallywood.add("আয়নাবাজি");
        dhallywood.add("টেলিভিশন");
        dhallywood.add("পরবাসিনী");

        subitem.put(item.get(0), hollywood);
        subitem.put(item.get(1), bollywood);
        subitem.put(item.get(2), dhallywood);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
