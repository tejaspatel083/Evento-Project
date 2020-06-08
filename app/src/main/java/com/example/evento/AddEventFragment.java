package com.example.evento;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class AddEventFragment extends Fragment {

    ImageView event_image;
    EditText event_name,host_name,event_location,event_date,event_time,event_cost;
    Button btn_addEvent;

    private EventListAdapter mAdapter;
    private SQLiteDatabase mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View eventView =  inflater.inflate(R.layout.fragment_addevent,container,false);

        EventDBHelper eventDBHelper = new EventDBHelper(getActivity());
        mDatabase = eventDBHelper.getWritableDatabase();

        event_image = eventView.findViewById(R.id.Add_EventImage);
        event_name = eventView.findViewById(R.id.Add_EventName);
        host_name = eventView.findViewById(R.id.Add_EventHostName);
        event_location = eventView.findViewById(R.id.Add_EventLocation);
        event_date = eventView.findViewById(R.id.Add_EventDate);
        event_time = eventView.findViewById(R.id.Add_EventTime);
        event_cost = eventView.findViewById(R.id.Add_EventCost);
        btn_addEvent = eventView.findViewById(R.id.Add_EventButton);


        final MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.click);


        btn_addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Vibrator vb = (Vibrator)   getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(20);

                mp.start();

                String eventname = event_name.getText().toString().trim();
                String hostname = host_name.getText().toString().trim();
                String eventlocation = event_location.getText().toString().trim();
                String eventdate = event_date.getText().toString().trim();
                String eventtime = event_time.getText().toString().trim();
                String eventcost = event_cost.getText().toString().trim();


                if (eventname.length() == 0 || hostname.length() == 0 || eventlocation.length() == 0 || eventdate.length() == 0 || eventtime.length() == 0 || eventcost.length() == 0)
                {
                    Toast.makeText(getActivity(), "Enter All Details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ContentValues cv = new ContentValues();
                    cv.put(EventContract.EventEntry.COLUMN_EVENT_NAME,eventname);
                    cv.put(EventContract.EventEntry.COLUMN_EVENT_HOST_NAME,hostname);
                    cv.put(EventContract.EventEntry.COLUMN_EVENT_LOCATION,eventlocation);
                    cv.put(EventContract.EventEntry.COLUMN_EVENT_DATE,eventdate);
                    cv.put(EventContract.EventEntry.COLUMN_EVENT_TIME,eventtime);
                    cv.put(EventContract.EventEntry.COLUMN_EVENT_COST,eventcost);

                    mDatabase.insert(EventContract.EventEntry.TABLE_NAME, null,cv);
                    Toast.makeText(getActivity(), "Event Added", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(),HomePage.class);
                    startActivity(intent);

                }


            }
        });

        return eventView;
    }

}
