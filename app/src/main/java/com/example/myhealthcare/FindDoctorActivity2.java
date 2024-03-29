package com.example.myhealthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FindDoctorActivity2 extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_find_doctor2);

        btn = findViewById(R.id.buttonFDBack);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDoctorActivity2.this, HomeActivity2.class));
            }
        });


        CardView familyphysician = findViewById(R.id.FDFamilyPhysicians);
        familyphysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity2.this,DoctorDetailsActivity2.class);
                it.putExtra("title","Family Physicians");
                startActivity(it);
            }
        });

        CardView dietician = findViewById(R.id.FDDietician);
        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity2.this,DoctorDetailsActivity2.class);
                it.putExtra("title","Dietician");
                startActivity(it);
            }
        });

        CardView dentist = findViewById(R.id.FDDentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity2.this,DoctorDetailsActivity2.class);
                it.putExtra("title","Dentist");
                startActivity(it);
            }
        });


        CardView surgeon= findViewById(R.id.FDSurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity2.this,DoctorDetailsActivity2.class);
                it.putExtra("title","Surgeon");
                startActivity(it);
            }
        });


        CardView cardiologists= findViewById(R.id.FDCardiologists);
        cardiologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity2.this,DoctorDetailsActivity2.class);
                it.putExtra("title","Cardiologists");
                startActivity(it);
            }
        });


    }
}


