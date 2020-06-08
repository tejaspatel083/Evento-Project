package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class BookEvent extends AppCompatActivity {

    TextView book_nametxt,book_costtxt,book_totalcosttxt,inc_dec_textview;
    Button increment_btn,decrement_btn,pay_btn;
    int quantity1=1;
    String str_cost,str_name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_event);


        book_nametxt = findViewById(R.id.book_eventname);
        book_costtxt = findViewById(R.id.book_eventcost);
        book_totalcosttxt = findViewById(R.id.total_cost_text);
        increment_btn = findViewById(R.id.plus_btn);
        decrement_btn = findViewById(R.id.minus_btn);
        inc_dec_textview = findViewById(R.id.inc_dec_text);
        pay_btn = findViewById(R.id.pay_button);






        str_name = getIntent().getStringExtra("name");
        str_cost = getIntent().getStringExtra("cost");

        book_nametxt.setText(str_name);
        book_costtxt.setText(str_cost);
        book_totalcosttxt.setText("Total: $" + str_cost);

        decrement_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(20);

                decrement1();
            }
        });

        increment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(20);

                increment1();
            }
        });

        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(20);

                Intent intent = new Intent(BookEvent.this,Payment.class);
                startActivity(intent);

            }
        });


    }

    public void increment1 () {
        quantity1 = quantity1 + 1;
        display1(quantity1);
    }

    public void decrement1 () {
        if (quantity1>0){
            quantity1 = quantity1 - 1;
            display1(quantity1);
        }
        else
        {
            display1(quantity1);
        }
    }

    private void display1(int number) {

        inc_dec_textview.setText("" + number);
        int i=Integer.parseInt(str_cost);
        int l = i*number;
        book_totalcosttxt.setText("Total: $" + l);

    }
}
