package com.example.pe01_se151101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pe01_se151101.adapter.DataAdapter;
import com.example.pe01_se151101.dto.CarDTO;

public class AddActivity extends AppCompatActivity {
    EditText productName;
    EditText productPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        productName = (EditText) findViewById(R.id.edit_name);
        productPrice = (EditText) findViewById(R.id.edit_price);
    }
    public void addData(View view) {
        String name = productName.getText().toString();
        String price = productPrice.getText().toString();
        if (!name.isEmpty()) {
            if (!price.isEmpty()) {
                DataAdapter handler = new DataAdapter(this, null, null, 1);
                System.out.println(price);
                CarDTO product = new CarDTO(name, Integer.parseInt(price));
                handler.addDataAdapter(product);
                productName.setText("");
                productPrice.setText("");
                Toast.makeText(this, "Add successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please fill price!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Please fill name!", Toast.LENGTH_LONG).show();
        }
    }
    public void backToHome(View view) {
        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        startActivity(intent);
    }
}