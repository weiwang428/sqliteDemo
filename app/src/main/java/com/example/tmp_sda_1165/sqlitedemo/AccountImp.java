package com.example.tmp_sda_1165.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
public class AccountImp {
    private DBHelper dbHelper;

    public AccountImp(Context context){
        dbHelper=new DBHelper(context);
    }

    public int insert(Account account){
        //write items to db
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Account.KEY_count, account.count);
        values.put(Account.KEY_date, account.date);
        values.put(Account.KEY_label, account.label);
        //
        long item_Id=db.insert(Account.TABLE,null,values);
        db.close();
        return (int)item_Id;
    }

    public void delete(int item_Id){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete(Account.TABLE,Account.KEY_ID+"=?", new String[]{String.valueOf(item_Id)});
        db.close();
    }
    public void update(Account account){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(Account.KEY_count, account.count);
        values.put(Account.KEY_date, account.date);
        values.put(Account.KEY_label, account.label);

        db.update(Account.TABLE,values,Account.KEY_ID+"=?",new String[] { String.valueOf(account.account_ID) });
        db.close();
    }

    public ArrayList<HashMap<String, String>> getAccountList(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                Account.KEY_ID+","+
                Account.KEY_label +","+
                Account.KEY_date +","+
                Account.KEY_count +" FROM "+Account.TABLE;
        ArrayList<HashMap<String,String>> itemList=new ArrayList<HashMap<String, String>>();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> item=new HashMap<String,String>();
                item.put("id",cursor.getString(cursor.getColumnIndex(Account.KEY_ID)));
                item.put("label",cursor.getString(cursor.getColumnIndex(Account.KEY_label)));
                item.put("date",cursor.getString(cursor.getColumnIndex(Account.KEY_date)));
                item.put("count",cursor.getString(cursor.getColumnIndex(Account.KEY_count)));
                itemList.add(item);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return itemList;
    }

    public Account getItemById(int Id){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                Account.KEY_ID + "," +
                Account.KEY_label + "," +
                Account.KEY_date + "," +
                Account.KEY_count +
                " FROM " + Account.TABLE
                + " WHERE " +
                Account.KEY_ID + "=?";
        int iCount=0;
        Account account =new Account();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(Id)});
        if(cursor.moveToFirst()){
            do{
                account.account_ID =cursor.getInt(cursor.getColumnIndex(Account.KEY_ID));
                account.label =cursor.getString(cursor.getColumnIndex(Account.KEY_label));
                account.date =cursor.getString(cursor.getColumnIndex(Account.KEY_date));
                account.count =cursor.getInt(cursor.getColumnIndex(Account.KEY_count));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return account;
    }
}
