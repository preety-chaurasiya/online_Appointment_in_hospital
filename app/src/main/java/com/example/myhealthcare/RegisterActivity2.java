package com.example.myhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity2 extends AppCompatActivity {
    EditText edUsername, edEmail, edPassword, edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        edUsername = findViewById(R.id.editTextBMBFullName);
        edEmail = findViewById(R.id.editTextBMBAddress);
        edPassword = findViewById(R.id.editTextBMBPinCode);
        edConfirm = findViewById(R.id.editTextBMBContactnumber);
        btn = findViewById(R.id.buttonAppBookAppointment);
        tv = findViewById(R.id.textViewExistingUser);


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity2.this,LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edPassword.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edPassword.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if (username.length()==0 || email.length()==0 || password.length()==0 || confirm.length()==0) {
                    Toast.makeText(getApplicationContext(), "Please fill All details", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(password.compareTo(confirm) == 0) {
                        if (isvalid(password)) {

                        db.register(username,email,password);
                            Toast.makeText(getApplicationContext(),"Record Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity2.this, LoginActivity.class));
                        }
                     else {
                        Toast.makeText(getApplicationContext(),"Password must contain at least 8 charaters, having letter,digit and special symbol", Toast.LENGTH_SHORT).show();
                    }
                }else{
                        Toast.makeText(getApplicationContext(),"Password and Confirm password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public static boolean isvalid(String passwordhere) {
        int f1=0,f2=0,f3=0;
        if (passwordhere.length() < 8 ) {
            return false;
        }else{
                  for(int p = 0; p < passwordhere.length(); p++){
                      if (Character.isLetter(passwordhere.charAt(p))){
                          f1=1;
                      }
            }
                  for(int r = 0; r < passwordhere.length(); r++){
                      if (Character.isDigit(passwordhere.charAt(r))){
                          f2=1;
                      }
                  }
            for(int s = 0; s < passwordhere.length(); s++){
                char c  = passwordhere.charAt(s);
                if(c>33&&c<=46||c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1)
                return true;
            return false;

        }

    }


}

