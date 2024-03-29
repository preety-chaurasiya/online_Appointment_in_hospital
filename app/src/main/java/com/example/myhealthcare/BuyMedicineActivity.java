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

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] packages = {
            {"Uprise-D3 1000IU Capsule","","","", "60"},
            {"HealthVit Chromium Picolinate 200mcg ","","","", "305"},
            {"Vitamin B Complex Capsules","","","", "448"},
            {"Inlife Vitamin E Wheat Germ Oil Capsule","","","", "539"},
            {"Dole 450 Tablet","","","", "30"},
            {"Crocin 450 Advance Tablet","","","", "50"},
            {"Strepsils Medicated Lozenges for Sore Throat","","","", "309"},
            {"Tata ing Calcium Vitamin B3","","","", "20"},
            {"Feronia -XT Tablet","","","", "130"},

    };


    private String[] packages_details = {

            "Building and keeping the bones & teeth strongla\n" +
                    "Reducing Fatigue/stress and muscular pains\n" +
                    "Boosting innunity and increasing resistance against infection\n",
            "Chroniun is an essential trace mineral that plays an important role in helping insulin regula ",
            "Provides relief fros vitamin & deficiencies\n" +
                    "Helps in formation of red blood cells\n" +
                    "Maintains healthy nervous system\n" +
                    "It promotes health as well as skin benefit.\n" +
                    "It helps reduce skin blemish and pigmentation.\n" +
                    "It act as safeguard the skin from the harsh UVA and UVB sun rays.\n" +
                    "Dole 650 Tablet helps relieve pain and fever by blocking the release of certain chemical mess\n" +
                    "Helps relieve fever and bring down a high temperature\n" +
                    "Suitable for people with a heart condition or high blood\n" +
                    "Relieves the syaptons of a bacterial throat infection and seethes the recovery processth\n" +
                    "Provides a sarn and conforting feeling during sore threat",
            " Reduces the risk of calcius deficiency, Rickets, and Osteoporosisin\n" +
                    "Promotes mobility and flexibility of joints",
            "Helps to reduce the fron deficiency due to chronic blood loss or low intake of iron"

    };

    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list;
    ListView lst;
    SimpleAdapter sa;
    Button btnBack, btnGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst = findViewById(R.id.editTextLTDTextMultiLine);
        btnBack = findViewById(R.id.buttonLTDCartBack);
        btnGoToCart = findViewById(R.id.buttonCartCheckout);


        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity2.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost:" + packages[i][4] + "/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3","line4","line5"},
                new int[]{R.id.line_a, R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                it.putExtra("text1", packages[i][0]);
                it.putExtra("text3", packages[i][4]);
                it.putExtra("text2", packages_details[i]);
                startActivity(it);
            }
        });
    }
}