package com.shhridoy.expandablelist;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shhridoy.expandablelist.mDataBase.DBAdapter;
import com.shhridoy.expandablelist.mDataObject.Spacecraft;
import com.shhridoy.expandablelist.mListView.CustomAdapter;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ListView listView;
    SearchView sv;
    EditText nameEditText;
    Button saveBtn, retrieveBtn;
    ArrayList<Spacecraft> spacecrafts = new ArrayList<>();
    CustomAdapter adapter;
    final Boolean forUpdate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sv = (SearchView) findViewById(R.id.searchVIew);

        listView = (ListView) findViewById(R.id.ListView);
        adapter = new CustomAdapter(this, spacecrafts);

        this.getSpacecrafts(null);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog(false);
            }
        });*/

        sv.setQueryHint("Search Here");
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getSpacecrafts(newText);
                return false;
            }
        });
    }

    private void displayDialog(Boolean forUpdate){
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("SQLITE DATA");
        dialog.setContentView(R.layout.dialog_layout);

        nameEditText = (EditText) dialog.findViewById(R.id.nameEditText);
        saveBtn = (Button) dialog.findViewById(R.id.savebtn);
        retrieveBtn = (Button) dialog.findViewById(R.id.retrievbtn);

        if (!forUpdate){
            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    save(nameEditText.getText().toString());
                    dialog.dismiss();
                }
            });

            retrieveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSpacecrafts(null);
                    dialog.dismiss();
                }
            });
        } else {

            //SET SELECTED TEXT
            nameEditText.setText(adapter.getSelectedItemName());

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    update(nameEditText.getText().toString());
                    dialog.dismiss();
                }
            });

            retrieveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSpacecrafts(null);
                    dialog.dismiss();
                }
            });
        }
        dialog.show();
    }

    //SAVE
    private void save(String name){
        DBAdapter db = new DBAdapter(this);
        db.openDB();
        boolean saved = db.add(name);
        db.closeDB();
        if (saved){
            nameEditText.setText("");
            getSpacecrafts(null);
        }else {
            Toast.makeText(this, "Unable to save", Toast.LENGTH_SHORT).show();
        }

    }

    //RETRIEVE OF GETSPACECRAFTS
    private void getSpacecrafts(String searchItem){
        spacecrafts.clear();
        DBAdapter db = new DBAdapter(this);
        db.openDB();
        Cursor cursor = db.retrieve(searchItem);
        Spacecraft spacecraft = null;

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);

            spacecraft = new Spacecraft();
            spacecraft.setId(id);
            spacecraft.setName(name);

            spacecrafts.add(spacecraft);
        }
        db.closeDB();
        listView.setAdapter(adapter);
    }

    //UPDATE OR EDIT
    private void update(String newName){
        //GET ID OF SPACECRAFT
        int id = adapter.getSelectedItemId();

        //UPDATE IN DB
        DBAdapter db = new DBAdapter(this);
        db.openDB();
        boolean updated = db.update(newName, id);
        db.closeDB();

        if (updated){
            nameEditText.setText(newName);
            getSpacecrafts(null);
        }else {
            Toast.makeText(this, "Unable to Update", Toast.LENGTH_SHORT).show();
        }

    }

    //DELETE OR REMOVE
    private void delete(){
        //GET ID
        int id = adapter.getSelectedItemId();

        //DELETE FROM DB
        DBAdapter db = new DBAdapter(this);
        db.openDB();
        boolean deleted = db.delete(id);
        db.closeDB();

        if (deleted){
            getSpacecrafts(null);
        }else {
            Toast.makeText(this, "Unable to Delete", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        CharSequence title = item.getTitle();

        if (title == "NEW"){
            displayDialog(!forUpdate);
        }
        else if (title == "EDIT"){
            displayDialog(forUpdate);
        }
        else if (title == "DELETE"){
            delete();
        }


        return super.onContextItemSelected(item);
    }

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
            displayDialog(false);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
