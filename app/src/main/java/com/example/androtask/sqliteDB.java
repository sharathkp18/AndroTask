package com.example.androtask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class sqliteDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserApp.db";

    public static final String USERS_TABLE_NAME = "userTable";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_NAME = "userName";
    public static final String USER_COLUMN_PHONE = "phone";
    public static final String USER_COLUMN_PASSWORD = "password";

    public sqliteDB(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table " + USERS_TABLE_NAME + "(" + USER_COLUMN_ID + " integer primary key," +
                " " + USER_COLUMN_NAME + " text," +
                " " + USER_COLUMN_PHONE + " text," +
                " " + USER_COLUMN_PASSWORD + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+USERS_TABLE_NAME);
        onCreate(db);
    }

    public boolean addUser (String name, String phone, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COLUMN_NAME, name);
        contentValues.put(USER_COLUMN_PHONE, phone);
        contentValues.put(USER_COLUMN_PASSWORD, password);
        db.insert(USERS_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getUserData(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+USERS_TABLE_NAME+" where "+USER_COLUMN_NAME+"="+username+"", null );
        return res;
    }
    public boolean checkUserData(String username,String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+USERS_TABLE_NAME+" where "+USER_COLUMN_NAME+"='"+username+"' AND "+USER_COLUMN_PASSWORD+"='"+password+"'", null );
        if(res.moveToNext()){
            return true;
        }
        return false;
    }

    public int numberOfUserRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, USERS_TABLE_NAME);
        return numRows;
    }

    public boolean updateUser (Integer id, String name, String phone, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("password", password);
        db.update(USERS_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteUser (String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(USERS_TABLE_NAME,USER_COLUMN_NAME +"= ? ",
                new String[] { userName});
    }

    public ArrayList<String> getAllUsers() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ USERS_TABLE_NAME+"", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(USER_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}
