package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button loginbtn,createbtn;
    private TextView forgotpwd;
    private EditText useremail,userpassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);

        loginbtn = (Button)findViewById(R.id.MainLoginBtn);
        createbtn = (Button)findViewById(R.id.MainCreateBtn);
        forgotpwd = (TextView)findViewById(R.id.MainForgotPassword);
        useremail = findViewById(R.id.MainEmail);
        userpassword = findViewById(R.id.MainPassword);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {

                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(20);


                if (useremail.getText().toString().trim().length() == 0)
                {
                    useremail.setError("Email Id Required");
                    //Toast.makeText(MainActivity.this,"Enter Email",Toast.LENGTH_LONG).show();
                    Toast toast = Toast.makeText(MainActivity.this,"Enter Email",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else if (userpassword.getText().toString().trim().length() == 0)
                {
                    useremail.setError(null);
                    userpassword.setError("Password Required");
                    //Toast.makeText(MainActivity.this,"Enter Password",Toast.LENGTH_LONG).show();
                    Toast toast = Toast.makeText(MainActivity.this,"Enter Password",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else
                {
                    progressDialog.setMessage("Logging in...");
                    progressDialog.show();
                    useremail.setError(null);
                    userpassword.setError(null);
                    Intent i = new Intent(MainActivity.this,HomePage.class);
                    startActivity(i);


                }
            }
        });



        createbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {

                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(20);
                Intent i = new Intent(MainActivity.this,CreateAccount.class);
                startActivity(i);

            }
        });

        forgotpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(20);
                Intent i = new Intent(MainActivity.this,ForgotPassword.class);
                startActivity(i);

            }
        });

    }
}
