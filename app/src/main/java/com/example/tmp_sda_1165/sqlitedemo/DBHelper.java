package com.example.tmp_sda_1165.sqlitedemo;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;


public class DBHelper extends SQLiteOpenHelper {
    //version number
    private static final int DATABASE_VERSION=4;

    //db name
    private static final String DATABASE_NAME="accountSystem.db";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        String CREATE_TABLE_ACCOUNT="CREATE TABLE "+ Account.TABLE+"("
                +Account.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                +Account.KEY_label +" TEXT, "
                +Account.KEY_count +" INTEGER, "
                +Account.KEY_date +" TEXT)";
        db.execSQL(CREATE_TABLE_ACCOUNT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
        db.execSQL("DROP TABLE IF EXISTS "+ Account.TABLE);

        //
        onCreate(db);
    }
}