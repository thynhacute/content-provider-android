package com.example.pe01_se151101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pe01_se151101.R;
import com.example.pe01_se151101.adapter.DataAdapter;

public class UpdateActivity extends AppCompatActivity {

    EditText productId;
    EditText productName;
    EditText productPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        productId = (EditText) findViewById(R.id.edit_id);
        productName = (EditText) findViewById(R.id.edit_name);
        productPrice = (EditText) findViewById(R.id.edit_price);
    }
    public void updateData(View view) {
        String id = productId.getText().toString();
        String name = productName.getText().toString();
        String price = productPrice.getText().toString();
        if (!id.isEmpty()) {
            if (!name.isEmpty()) {
                if (!price.isEmpty()) {
                    try {
                        DataAdapter handler = new DataAdapter(this, null, null, 1);
                        boolean result = handler.updateDataAdapter(Integer.parseInt(id), name, Integer.parseInt(price));
                        if (result) {
                            productId.setText("");
                            productName.setText("");
                            productPrice.setText("");
                            Toast.makeText(this, "Update successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "CarID does not exist!", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(this, "Please fill right id!", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(this, "Please fill price!", Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(this, "Please fill name!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Please fill id!", Toast.LENGTH_LONG).show();
        }
    }
    public void backToHome(View view) {
        Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
        startActivity(intent);
    }
}