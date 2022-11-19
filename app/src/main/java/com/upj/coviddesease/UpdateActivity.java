package com.upj.coviddesease;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataAdapter dbAdapter;
    Button btnEdit;
    EditText etProvCode, etProvName, etCure, etDeaths, etActive;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        dbAdapter = new DataAdapter(this);
        etProvCode = findViewById(R.id.etProvinceCodeEdit);
        etProvName = findViewById(R.id.etProvinceNameEdit);
        etCure = findViewById(R.id.etCureEdit);
        etDeaths = findViewById(R.id.etDeathsEdit);
        etActive = findViewById(R.id.etActiveEdit);

        btnEdit = findViewById(R.id.btnEdit);
        SQLiteDatabase db = dbAdapter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM covidcase WHERE provName = '" +
                getIntent().getStringExtra("id") + "'",null);
        cursor.moveToFirst();
        Log.d("Valueeee", getIntent().getStringExtra("id").toString());
        if(cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            etProvCode.setText(cursor.getString(1).toString());
            etProvName.setText(cursor.getString(2).toString());
            etCure.setText(cursor.getString(3).toString());
            etDeaths.setText(cursor.getString(4).toString());
            etActive.setText(cursor.getString(5).toString());
        }
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbAdapter.getWritableDatabase();
                String sql = "update covidcase set provId='" +
                        etProvCode.getText().toString() + "', provName = '" +
                        etProvName.getText().toString() + "', recover = '" +
                        etCure.getText().toString() + "', deaths = '" +
                        etDeaths.getText().toString() + "', active = '" +
                        etActive.getText().toString() + "' where provName = '" +
                        getIntent().getStringExtra("id") + "'";
                db.execSQL(sql);

                Toast.makeText(getApplicationContext(), "Success Update", Toast.LENGTH_LONG).show();
                MainActivity.ma.list();
                finish();
            }
        });

    }
}
