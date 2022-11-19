package com.upj.coviddesease;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataAdapter extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "coviddesease.db";
    private static final int DATABASE_VERSION = 1;
    public DataAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlDb = "create table covidcase(no integer primary key autoincrement," +
                "provId text null, provName text null, recover text null, " +
                "deaths text null, active text null);";
        db.execSQL(sqlDb);

        String sql = "INSERT INTO covidcase(no, provId, provName, recover, deaths, active)" +
                "VALUES('1', 'dki1', 'DKI Jakarta', '100', '10', '90');";


        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
