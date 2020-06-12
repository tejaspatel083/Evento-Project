package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Payment extends AppCompatActivity {

    EditText cardnumber,cardname,month,year,cvvnumber;
    Button make_payment_button;
    String useremail,name,tickets,total;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardnumber = findViewById(R.id.card_number);
        cardname = findViewById(R.id.card_name);
        month = findViewById(R.id.expiry_month);
        year = findViewById(R.id.expiry_year);
        cvvnumber = findViewById(R.id.cvv_number);

        make_payment_button = findViewById(R.id.make_payment);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        final MediaPlayer mp = MediaPlayer.create(Payment.this, R.raw.click);

        useremail = firebaseUser.getEmail().toString().trim();
        name = getIntent().getStringExtra("event");
        tickets = getIntent().getStringExtra("tickets");
        total = getIntent().getStringExtra("total");


        make_payment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.start();

                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(20);

                if (cardnumber.getText().toString().trim().length() == 0 || cardname.getText().toString().trim().length() == 0 || month.getText().toString().trim().length() == 0 || year.getText().toString().trim().length() == 0 || cvvnumber.getText().toString().trim().length() == 0 )
                {
                    Toast.makeText(Payment.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    FirebaseDatabase fD = FirebaseDatabase.getInstance();
                    DatabaseReference reference = fD.getReference().child("Booking List").child(firebaseAuth.getUid()).push();

                    BookingListGetterSetter bookingListGetterSetter = new BookingListGetterSetter(useremail,name,tickets,total);
                    reference.setValue(bookingListGetterSetter);


                    Toast.makeText(Payment.this, "Payment Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Payment.this,User_HomePage.class);
                    startActivity(intent);
                }


            }
        });

    }
}
