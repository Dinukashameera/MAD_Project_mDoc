package com.example.mdoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Form extends AppCompatActivity {

    EditText username, password;
    DBconnection dBconnection;
    Daologin login = new Daologin();
    Button btnlogin, btnreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        //getSupportActionBar().setTitle("Login");

        dBconnection = new DBconnection(this);

        username = findViewById(R.id.txtusername);
        password = findViewById(R.id.txtrpassword);
        btnlogin = findViewById(R.id.btnlogin);
        btnreg = findViewById(R.id.btnreg_log);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast toast;

                if (TextUtils.isEmpty(username.getText().toString())) {
                    toast = Toast.makeText(getApplicationContext(), "Enter Username", Toast.LENGTH_LONG);
                    toast.show();
                } else if (TextUtils.isEmpty(password.getText().toString())) {
                    toast = Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_LONG);
                    toast.show();
                } else {

                    login.setUsername(username.getText().toString().trim());
                    login.setPassword(password.getText().toString().trim());

                    if (dBconnection.addloginInfo(login) == true) {
                        toast = Toast.makeText(getApplicationContext(), " Successfully loging ", Toast.LENGTH_LONG);
                        toast.show();

                    } else {
                        toast = Toast.makeText(getApplicationContext(), "Error in login", Toast.LENGTH_LONG);
                        toast.show();

                    }


                }


                // });

                // }


                //}


//public void login(View view) {

                //String uname,upass;
                //Intent intent;

                // uname = username.getText().toString();
                //upass = password.getText().toString();

                //if(uname.equals("admin") && upass.equals("admin"))
                //{

                //intent = new Intent(Login_Form.this, MainNavigationActivity.class);
                //startActivity(intent);

                //}else if(uname.equals("customer") && upass.equals("customer"))
                // {
                // intent = new Intent(this, MainHome.class);
                // startActivity(intent);
                //}else if(uname.equals("lab") && upass.equals("lab"))
                //{
                //intent = new Intent(this, reportMainja.class);
                // startActivity(intent);
                //}else if(uname.equals("doctor") && upass.equals("doctor"))
                // {
                //intent = new Intent(this, DocHomeActivity.class);
                // startActivity(intent);
                //}

                // }


//signup(View view)


            }
        });
    }

    public void viewregister(View view) {

        Intent intent = new Intent(this,register.class);
        startActivity(intent);
    }
}