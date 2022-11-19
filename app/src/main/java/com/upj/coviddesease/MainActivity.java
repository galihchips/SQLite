package com.upj.coviddesease;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String[] list;
    ListView ListCovid;
    protected Cursor cursor;
    DataAdapter dbcenter;
    Button btnCreate;
    public static MainActivity ma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });
        dbcenter = new DataAdapter(this);
        ma = this;
        list();

    }

    public void list() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM covidcase", null);
        list = new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            list[i] = cursor.getString(2).toString();
        }
        ListCovid = findViewById(R.id.lvCovid);
        ListCovid.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list));
        ListCovid.setSelected(true);

        ListCovid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String selection = list[i];
                final CharSequence[] dialogItem = {"Update Data", "Delete Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Option");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
                                intent.putExtra("id", selection);
                                startActivity(intent);
                                break;
                            case 1:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                String sql = "delete from covidcase where provName = '"+ selection + "'";
                                db.execSQL(sql);
                                list();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)ListCovid.getAdapter()).notifyDataSetInvalidated();
    }
}