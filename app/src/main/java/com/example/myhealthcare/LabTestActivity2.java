package com.example.myhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity2 extends AppCompatActivity {

    private String[][] packages = {
            {"Full Body Checkup", "", "", "", "2999"},
            {"Blood Glucose Fasting", "", "", "", "9999"},
            {"COVID-19 Antibody - IgG", "", "", "", "4579"},
            {"Thyroid Check", "", "", "", "2939"},
            {"Immunity Check", "", "", "", "9979"},
    };

    private String[] packages_details = {
            "Blood Glucose Fasting\n" + "Complete Hemogram\n" + "HbA1c\n" + "Iron Studies\n" +
                    "Kidney Function Test\n" + "LDH Lactate Dehydrogenase, Serum\n" +
                    "Lipid Profile\n" + "Liver Function Test",
            "Blood Glucose Fasting",
            "COVID-19 Antibody IgG",
            "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)",
            "Complete Hemogram\n" + "CRP (C Reactive Protein) Quantitative, Serum\n" +
                    "Iron Studies\n" + "Kidney Function Test\n" +
                    "Vitamin D Total-25  Hydroxy\n" + "Liver Function Test\n" + "Lipid Profile"
    };

    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;
    Button btnGoToCard, btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test2);

        btnGoToCard = findViewById(R.id.buttonCartCheckout);
        btnBack = findViewById(R.id.buttonLTDCartBack);
        listView = findViewById(R.id.editTextLTDTextMultiLine);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity2.this, HomeActivity2.class));
            }
        });

        list = new ArrayList<>();
        for (int i = 0; i < packages_details.length; i++) {
            item = new HashMap<String,String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost:" + packages[i][4] + "/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(LabTestActivity2.this, LabTestDetailsActivity.class);
                it.putExtra("text1", packages[i][0]);
                it.putExtra("text3", packages[i][4]);
                it.putExtra("text2", packages_details[i]);
                startActivity(it);
            }
        });

        btnGoToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity2.this, CartLabActivity.class));
            }
        });
    }
}
