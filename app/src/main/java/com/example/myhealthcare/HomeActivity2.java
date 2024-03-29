 package com.example.myhealthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

 public class HomeActivity2 extends AppCompatActivity {

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_home2);
         SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
         String username = sharedPreferences.getString("username","").toString();
         Toast.makeText(getApplicationContext(),"welcome"+username, Toast.LENGTH_SHORT).show();

         CardView exit = findViewById(R.id.CardExit);
         exit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 SharedPreferences.Editor editor = sharedPreferences.edit();
                 editor.clear();
                 editor.apply();
                 startActivity((new Intent(HomeActivity2.this, LoginActivity.class)));
             }
         });

         CardView findDoctor = findViewById(R.id.FindDoctor);
         findDoctor.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(HomeActivity2.this, FindDoctorActivity2.class));
             }
         });
         CardView labtest = findViewById(R.id.LabTest);
         labtest.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(HomeActivity2.this, LabTestActivity2.class));
             }
         });

         CardView orderDetails = findViewById(R.id.OrderDetails);
         orderDetails.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(HomeActivity2.this, OrderDetailsActivity.class));
             }
         });
         CardView FindMedicine = findViewById(R.id.FindMedicine);
         FindMedicine.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(HomeActivity2.this, BuyMedicineActivity.class));
             }
         });

         CardView health = findViewById(R.id.HealthDoctor);
         health.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(HomeActivity2.this,HealthDoctorActivity.class));
             }
         });

     }
 }