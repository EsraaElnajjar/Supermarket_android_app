package com.example.trialproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginmain extends AppCompatActivity {
    EditText user, password;
    Button btnlogin;
    DBHelper DB;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginmain);
        user = (EditText) findViewById(R.id.login_user);
        password = (EditText) findViewById(R.id.login_password);
        btnlogin = (Button) findViewById(R.id.login_button);
        DB = new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = user.getText().toString();
                String pass = password.getText().toString();

                if(username.equals("")||pass.equals(""))
                    Toast.makeText(loginmain.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(username, pass);
                    if(checkuserpass==true){
                        Toast.makeText(loginmain.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(loginmain.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}