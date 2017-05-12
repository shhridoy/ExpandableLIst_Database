package com.shhridoy.expandablelist.mDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Code Land on 5/10/2017.
 */

public class DBAdapter {

    Context context;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }

    //OPEN CONNECTION TO DATABASE
    public void openDB(){
        try {
            db = helper.getWritableDatabase();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    //CLOSE DATABASE
    public void closeDB(){
        try {
            helper.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    //SAVE
    public boolean add(String name){

        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.NAME, name);

            long result = db.insert(Constants.TB_NAME, Constants.ROW_ID, cv);

            if (result > 0){
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    //SELECT or FETCH
    public Cursor retrieve(String searchTerm){
        String[] columns = {Constants.ROW_ID, Constants.NAME};
        Cursor c = null;

        if(searchTerm != null && searchTerm.length()>0)
        {
            String sql = "SELECT * FROM " + Constants.TB_NAME + " WHERE " + Constants.NAME +" LIKE '%" + searchTerm + "%'";
            c = db.rawQuery(sql, null);
            return c;
        }
        c = db.query(Constants.TB_NAME, columns, null, null, null, null, null);
        return c;

        //Cursor cursor = db.query(Constants.TB_NAME, columns, null, null, null, null, null);
        //return cursor;
    }

    //UPDATE or EDIT
    public boolean update(String newName, int id){

        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.NAME, newName);

            int result = db.update(Constants.TB_NAME, cv, Constants.ROW_ID + " =?", new String[]{String.valueOf(id)});
            if (result > 0){
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    //DELETE / REMOVE
    public boolean delete(int id){
        try {
            int result = db.delete(Constants.TB_NAME, Constants.ROW_ID+" =?", new String[]{String.valueOf(id)});
            if (result > 0){
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
