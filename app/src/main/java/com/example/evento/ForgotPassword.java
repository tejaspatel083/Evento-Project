package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.widget.TextView;

public class ForgotPassword extends AppCompatActivity {

    private TextView text1,text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        text1 = findViewById(R.id.txt1);
        text2 = findViewById(R.id.txt2);
        //forgotpwdtext = findViewById(R.id.txt1);


        Shader myShader = new LinearGradient(
                0, 0, 0, 100,
                Color.WHITE, Color.BLACK,
                Shader.TileMode.CLAMP );
        text1.getPaint().setShader( myShader );
        text2.getPaint().setShader( myShader );

    }
}
