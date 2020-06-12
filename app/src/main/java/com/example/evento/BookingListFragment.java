package com.example.evento;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookingListFragment extends Fragment {

    ListView listView;
    ArrayList<BookingListGetterSetter> list = new ArrayList<>();
    BookingListAdapter ArAd;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    BookingListGetterSetter bookingListGetterSetter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View booking_view =  inflater.inflate(R.layout.fragment_bookinglist,container,false);

        listView = booking_view.findViewById(R.id.booking_listview);

        ArAd=new BookingListAdapter(list);



        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference();
        DatabaseReference childreference = databaseReference.child("Booking List");

        childreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    for (DataSnapshot ds1: ds.getChildren())
                    {

                        bookingListGetterSetter = ds1.getValue(BookingListGetterSetter.class);
                        list.add(bookingListGetterSetter);


                    }


                }

                listView.setAdapter(ArAd);
                ArAd.notifyDataSetChanged();


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return booking_view;
    }
}
