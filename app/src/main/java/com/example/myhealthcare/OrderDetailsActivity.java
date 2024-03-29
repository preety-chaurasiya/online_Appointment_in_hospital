package com.example.myhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class OrderDetailsActivity extends AppCompatActivity {

    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list; // Change to ArrayList<HashMap<String, String>>
    SimpleAdapter sa;
    ListView lst;
    Button btn;
    private String[][] order_details = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        btn = findViewById(R.id.buttonODBack);
        lst = findViewById(R.id.listViewOD);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, HomeActivity2.class));
            }
        });

        Database db = new Database(getApplicationContext(), "Healthcare", null, 1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();
        ArrayList<String> dbData = db.getOrderData(username); // Change to ArrayList<String>

         order_details = new String[dbData.size()][];

        for (int i = 0; i < order_details.length; i++) {

            order_details[i] = new String[5];
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));

            order_details[i][0] = strData[0]; // Assuming this is the name
            order_details[i][1] = strData[1];
            order_details[i][2] = "Rs." + strData[6];
              if(strData.length>=8) {
                  if ("medicine".equals(strData[5])) {
                      order_details[i][3] = "Date" + strData[3];
                  } else {
                      order_details[i][3] = "Date:" + strData[4] + " " + strData[5];
                  }
                  order_details[i][4] = "Type:" + strData[7];

                  Log.d("Debug", "arrData: " + arrData);
                  Log.d("Debug", "strData[5]: " + strData[5]);
                  Log.d("Debug", "strData[6]: " + strData[6]);
              }
        }
            // Convert the array to a list of HashMap for SimpleAdapter
        list = new ArrayList<>();
        for (int i = 0; i < order_details.length; i++) {
            item = new HashMap<>();
            item.put("line1", order_details[i][0]);
            item.put("line2", order_details[i][1]);
            item.put("line3", order_details[i][2]);
            item.put("line4", order_details[i][3]);
            item.put("line5", order_details[i][4]);
            list.add(item);
        }

        // Set up the SimpleAdapter
        sa = new SimpleAdapter(this, list, R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );
        lst.setAdapter(sa);
    }
}
