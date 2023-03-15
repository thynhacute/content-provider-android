package com.example.pe01_se151101;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pe01_se151101.adapter.DataAdapter;
import com.example.pe01_se151101.R;

public class MainActivity extends AppCompatActivity {
    TextView dataList;
    TextView txtColumnName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataList = findViewById(R.id.txtData);
        txtColumnName = findViewById(R.id.txtColumnName);
        DataAdapter handler =new DataAdapter(this, null, null, 1);
        dataList.setText(handler.loadDataAdapter());
        if (!dataList.getText().toString().isEmpty()) {
            txtColumnName.setVisibility(View.VISIBLE);
        }
    }

    public void addProduct(View view) {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(intent);
    }
    public void deleteProduct(View view) {
        Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
        startActivity(intent);
    }
    public void updateProduct(View view) {
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        startActivity(intent);
    }
}