package com.example.myhealthcare;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity2 extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton, btnbooking, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment2);

        Log.d("Debug", "Entering onCreate...");

        tv = findViewById(R.id.textViewBMBTitle);
        ed1 = findViewById(R.id.editTextBMBFullName);
        ed2 = findViewById(R.id.editTextBMBAddress);
        ed3 = findViewById(R.id.editTextBMBPinCode);
        ed4 = findViewById(R.id.editTextBMBContactnumber);
        dateButton = findViewById(R.id.buttonCartDate);
        timeButton = findViewById(R.id.buttonCartTime);
        btnbooking = findViewById(R.id.buttonAppBookAppointment);
        btnBack = findViewById(R.id.buttonBMBBook);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent intent = getIntent();
        String title = intent.getStringExtra("text1");
        String fullName = intent.getStringExtra("text2");
        String address = intent.getStringExtra("text3");
        String contact = intent.getStringExtra("text4");
        String fees = intent.getStringExtra("text5");

        Log.d("Debug", "Received intent data...");
        Log.d("Debug", "Title: " + title);
        Log.d("Debug", "FullName: " + fullName);
        Log.d("Debug", "Address: " + address);
        Log.d("Debug", "Contact: " + contact);
        Log.d("Debug", "Fees: " + fees);

        tv.setText(title);
        ed1.setText(fullName);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons Fees:" + fees + "/-");

        // Datepicker
        initDatePicker();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        // Timepicker
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity2.this, FindDoctorActivity2.class));
            }
        });

        btnbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Debug", "Button Book clicked...");

                Database db = new Database(getApplicationContext(), "Healthcare", null, 1);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();

                if (db.checkAppointmentExists(username, title + " =>" + fullName, address, contact, dateButton.getText().toString(), timeButton.getText().toString()) == 1) {

                    Toast.makeText(getApplicationContext(), "Appointment already booked", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Debug", "Adding appointment...");

                    db.addOrder(username, title + " => " + fullName, address, contact, "", dateButton.getText().toString(), timeButton.getText().toString(), Float.parseFloat(fees), "appointment");
                    Toast.makeText(getApplicationContext(), "Your appointment is done successfully", Toast.LENGTH_SHORT).show();

                    Log.d("Debug", "Redirecting to HomeActivity2...");
                    startActivity(new Intent(BookAppointmentActivity2.this, HomeActivity2.class));
                }
            }
        });
    }


        private void initDatePicker () {
            Log.d("Debug", "Initializing DatePicker...");
            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    i1 = i1 + 1;
                    dateButton.setText(i2 + "/" + i1 + "/" + i);
                }
            };

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            int style = AlertDialog.THEME_HOLO_DARK;
            datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
            datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000); // Added an extra '0'
            Log.d("Debug", "DatePicker initialized.");
        }

        private void initTimePicker () {
            Log.d("Debug", "Initializing TimePicker...");
            TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int i, int i1) {
                    timeButton.setText(i + ":" + i1);
                }
            };

            Calendar cal = Calendar.getInstance();
            int hrs = cal.get(Calendar.HOUR_OF_DAY); // Changed from get(Calendar.HOUR)
            int mins = cal.get(Calendar.MINUTE);

            int style = AlertDialog.THEME_HOLO_DARK;
            timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mins, true);
            Log.d("Debug", "TimePicker initialized.");
        }
    }
