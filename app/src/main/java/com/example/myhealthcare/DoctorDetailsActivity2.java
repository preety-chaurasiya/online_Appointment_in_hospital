
package com.example.myhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity2 extends AppCompatActivity {
    private String[][] doctor_details1 =
            {
            {"Doctor Name : Swapnil kal ", "Hospital Address :  Pune", "Exp: 8yrs", "Mobile No: 98765432123", "600"},
            {"Doctor Name : Prasad Pawar", "Hospital Address : Nigdi ", "Exp:32yrs", "Mobile No: 667467362", "900"},
            {"Doctor Name : Ajit Saste ", "Hospital Address : Pimpri", "Exp: 4yrs", "Mobile No: 536511771", "500"},
            {"Doctor Name : Deepak Deshmukh", "Hospital Address : Chinhwad", "Exp: 8yrs", "Mobile No: 67261767117", "800"},
            {"Doctor Name : Ashok Panda", "Hospital Address : Katraj", "Exp: 3yrs", "Mobile No:72365165617", "300"},
    };

    private String[][] doctor_details2 =
            {

            {"Doctor Name : Neelam kal ", "Hospital Address :  Pune", "Exp: 8yrs", "Mobile No: 98765432123", "600"},
            {"Doctor Name : Prasad Pawar", "Hospital Address : Nigdi ", "Exp:32yrs", "Mobile No: 667467362", "900"},
            {"Doctor Name : Neeraja Saste ", "Hospital Address : Pimpri", "Exp: 4yrs", "Mobile No: 536511771", "500"},
            {"Doctor Name : Deepak Deshmukh", "Hospital Address : Chinhwad", "Exp: 8yrs", "Mobile No: 67261767117", "800"},
            {"Doctor Name : Minakshi Panda", "Hospital Address : Katraj", "Exp: 3yrs", "Mobile No:72365165617", "300"},
    };
    private String[][] doctor_details3 =
            {
            {"Doctor Name : Sneha Sharma", "Hospital Address :  Mumbai", "Exp: 10yrs", "Mobile No: 98765432123", "650"},
            {"Doctor Name : Aniket Patil", "Hospital Address : Thane ", "Exp:25yrs", "Mobile No: 667467362", "950"},
            {"Doctor Name : Preeti Desai", "Hospital Address : Navi Mumbai", "Exp: 6yrs", "Mobile No: 536511771", "550"},
            {"Doctor Name : Vikram Singh", "Hospital Address : Kalyan", "Exp: 12yrs", "Mobile No: 67261767117", "850"},
            {"Doctor Name : Ritu Verma", "Hospital Address : Panvel", "Exp: 5yrs", "Mobile No:72365165617", "400"},
    };
    private String[][] doctor_details4=
            {
            {"Doctor Name : Aakash Singh", "Hospital Address :  Delhi", "Exp: 15yrs", "Mobile No: 98765432123", "700"},
            {"Doctor Name : Kavita Sharma", "Hospital Address : Gurgaon", "Exp:20yrs", "Mobile No: 667467362", "1000"},
            {"Doctor Name : Rajat Kapoor", "Hospital Address : Noida", "Exp: 7yrs", "Mobile No: 536511771", "600"},
            {"Doctor Name : Natasha Mehra", "Hospital Address : Faridabad", "Exp: 10yrs", "Mobile No: 67261767117", "900"},
            {"Doctor Name : Vivek Verma", "Hospital Address : Ghaziabad", "Exp: 4yrs", "Mobile No:72365165617", "450"},
    };
    private String[][] doctor_details5 =
            {
            {"Doctor Name : Priya Kapoor", "Hospital Address :  Bangalore", "Exp: 12yrs", "Mobile No: 98765432123", "750"},
            {"Doctor Name : Arjun Singhania", "Hospital Address : Hyderabad", "Exp:18yrs", "Mobile No: 667467362", "1100"},
            {"Doctor Name : Shreya Reddy", "Hospital Address : Chennai", "Exp: 9yrs", "Mobile No: 536511771", "620"},
            {"Doctor Name : Karthik Nair", "Hospital Address : Kochi", "Exp: 11yrs", "Mobile No: 67261767117", "950"},
            {"Doctor Name : Ananya Joshi", "Hospital Address : Mysuru", "Exp: 6yrs", "Mobile No:72365165617", "500"},
    };


    TextView tv;
    Button btn;
    String[][] Doctor_details2 = {};
    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details2);

        tv = findViewById(R.id.textViewLTDCartTitle);
        btn = findViewById(R.id.buttonCartCheckout);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Family Physicians") == 0) {
            Doctor_details2 = doctor_details1;
        } else if (title.compareTo("Dietician") == 0) {
            Doctor_details2 = doctor_details4;
        } else if (title.compareTo("Dentist") == 0) {
            Doctor_details2 = doctor_details3;
        } else if (title.compareTo("Surgeon") == 0) {
            Doctor_details2 = doctor_details4;
        } else {
            Doctor_details2 = doctor_details5;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity2.this,FindDoctorActivity2.class));
            }
        });

        list = new ArrayList<>();
        for (int i = 0; i < doctor_details2.length; i++) {
            item = new HashMap<>();
            item.put("line1", doctor_details2[i][0]);
            item.put("line2", doctor_details2[i][1]);
            item.put("line3", doctor_details2[i][2]);
            item.put("line4", doctor_details2[i][3]);
            item.put("line5", "Cons Fees:" + doctor_details2[i][4] + "/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this, list, R.layout.multi_lines, new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );
        ListView lst = findViewById(R.id.editTextLTDTextMultiLine);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0 && i < doctor_details2.length) {
                    Intent it = new Intent(DoctorDetailsActivity2.this,BookAppointmentActivity2.class);
                    it.putExtra("text1", title);
                    it.putExtra("text2", doctor_details2[i][0]);
                    it.putExtra("text3", doctor_details2[i][1]);
                    it.putExtra("text4", doctor_details2[i][3]);
                    it.putExtra("text5", doctor_details2[i][4]);
                    startActivity(it);
                }
            }
        });
    }
}
