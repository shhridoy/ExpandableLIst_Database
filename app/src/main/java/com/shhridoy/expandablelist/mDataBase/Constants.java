package com.shhridoy.expandablelist.mDataBase;

/**
 * Created by Code Land on 5/10/2017.
 */

public class Constants {

    //COLUMNS
    static final String ROW_ID = "id";
    static final String NAME = "name";

    //DB PROPERTIES
    static final String DB_NAME = "hh_DB";
    static final String TB_NAME = "hh_TB";
    static final int DB_VERSION = 1;

    //CREATE TABLE STATEMENTS
    static final String CREATE_TB = "CREATE TABLE hh_TB(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT NOT NULL);";

    //DROP TABLE STATEMENTS
    static final String DROP_TB = "DROP TABLE IF EXISTS " + TB_NAME;
}
