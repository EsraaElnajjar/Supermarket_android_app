package com.example.trialproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {


    EditText username,email, password,passconfirm;
    Button register;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


    }
    public void haveAccount(View v){
        Button have=findViewById(R.id.havingbutton);
        Intent i=new Intent(this,loginmain.class);
        startActivity(i);
    }
    public void sign(View v){
        username = (EditText) findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password = (EditText) findViewById(R.id.signup_password);
        passconfirm = (EditText) findViewById(R.id.signup_confirm);
        register = (Button) findViewById(R.id.signup_button);
        DB = new DBHelper(this);

                String user = username.getText().toString();
                String useremail=email.getText().toString();
                String pass = password.getText().toString();
                String repass = passconfirm.getText().toString();

                if(user.equals("")||useremail.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user,useremail, pass,repass);
                            if(insert==true){
                                Toast.makeText(register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),loginmain.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(register.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(register.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }

    }
}