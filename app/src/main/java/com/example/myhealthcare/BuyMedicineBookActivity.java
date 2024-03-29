package com.example.myhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {
    EditText edname, edaddress, edcontact, edpincode;
    Button btnBook, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname = findViewById(R.id.editTextBMBFullName);
        edaddress = findViewById(R.id.editTextBMBAddress);
        edcontact = findViewById(R.id.editTextBMBContactnumber);
        edpincode = findViewById(R.id.editTextBMBPinCode);
        btnBook = findViewById(R.id.buttonBMBBook);
        btnBack = findViewById(R.id.buttonBMBBack);


        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");


        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                Database db = new Database(getApplicationContext(),"Healthcare",null,1);

                db.addOrder(username, edname.getText().toString(), edaddress.getText().toString(), edcontact.getText().toString(), edpincode.getText().toString(), date.toString(), "", Float.parseFloat(price[1].toString()), "medicine");
                db.removeCart(username,"medicine");
                Toast.makeText(getApplicationContext(),"Your booking is done successfully",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BuyMedicineBookActivity.this, HomeActivity2.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineBookActivity.this,BuyMedicineDetailsActivity.class));
            }
        });
    }
}