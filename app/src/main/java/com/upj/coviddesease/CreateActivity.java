package com.upj.coviddesease;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreateActivity extends AppCompatActivity {
    DataAdapter dbAdapter;
    Button btnSave;
    EditText etProvinceCode, etProvinceName, etCureTotal, etDeathsTotal, etActiveTotal;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        dbAdapter = new DataAdapter(this);
        etProvinceCode = findViewById(R.id.etProvinceCodeCreate);
        etProvinceName = findViewById(R.id.etProvinceNameCreate);
        etCureTotal = findViewById(R.id.etCureCaseCreate);
        etDeathsTotal = findViewById(R.id.etCureCaseCreate);
        etActiveTotal = findViewById(R.id.etActiveCaseCreate);
        btnSave = findViewById(R.id.btnSave);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbAdapter.getWritableDatabase();
                String sql = "insert into covidcase(no, provId, provName, recover, deaths, active) values (NULL, '" +
                        etProvinceCode.getText().toString() + "','" +
                        etProvinceName.getText().toString() + "','" +
                        etCureTotal.getText().toString() + "','" +
                        etDeathsTotal.getText().toString() + "','" +
                        etActiveTotal.getText().toString() + "')";

                db.execSQL(sql);
                Toast.makeText(getApplicationContext(), "Success Add data", Toast.LENGTH_LONG).show();
                MainActivity.ma.list();
                finish();
            }
        });


    }
}
