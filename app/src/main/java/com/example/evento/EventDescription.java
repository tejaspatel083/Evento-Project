package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EventDescription extends AppCompatActivity implements EventListAdapter.AdapterCallback {

    private TextView event,host,location,date,time,cost;
    private Button book_event_button;

    private Context mContext;
    private Cursor mCursor;
    String yourValue;
    String name_event,name_host,location_event,date_event,time_event;
    int cost_event;
    long id;

    SQLiteDatabase db;

    public EventDescription() {
    }

    public EventDescription(int contentLayoutId) {
        super(contentLayoutId);
    }

    public EventDescription(Context mContext, Cursor mCursor) {
        this.mContext = mContext;
        this.mCursor = mCursor;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description);

        event = findViewById(R.id.eventname_detail);
        host = findViewById(R.id.hostname_detail);
        location = findViewById(R.id.eventlocation_detail);
        date = findViewById(R.id.eventdate_detail);
        time = findViewById(R.id.eventtime_detail);
        cost = findViewById(R.id.eventcost_detail);

        EventDBHelper eventDBHelper = new EventDBHelper(this.getApplicationContext());
        db = eventDBHelper.getWritableDatabase();

        String str = getIntent().getStringExtra("key");
        //event.setText(str);

        mCursor = db.rawQuery("SELECT * FROM " + EventContract.EventEntry.TABLE_NAME + " where eventname = '" +str + "'" , null);


        if (mCursor != null ) {
            if  (mCursor.moveToFirst()) {
                do {

                    name_event = mCursor.getString(mCursor.getColumnIndex(EventContract.EventEntry.COLUMN_EVENT_NAME));
                    name_host = mCursor.getString(mCursor.getColumnIndex(EventContract.EventEntry.COLUMN_EVENT_HOST_NAME));
                    location_event = mCursor.getString(mCursor.getColumnIndex(EventContract.EventEntry.COLUMN_EVENT_LOCATION));
                    date_event = mCursor.getString(mCursor.getColumnIndex(EventContract.EventEntry.COLUMN_EVENT_DATE));
                    time_event = mCursor.getString(mCursor.getColumnIndex(EventContract.EventEntry.COLUMN_EVENT_TIME));
                    cost_event = mCursor.getInt(mCursor.getColumnIndex(EventContract.EventEntry.COLUMN_EVENT_COST));
                    id = mCursor.getLong(mCursor.getColumnIndex(EventContract.EventEntry._ID));




                }while (mCursor.moveToNext());
            }
        }




        event.setText(name_event);
        host.setText(name_host);
        location.setText(location_event);
        date.setText(date_event);
        time.setText(time_event);
        cost.setText(String.valueOf(cost_event));





        //itemView.setTag(id);

    }

    @Override
    public void onMethodCallback(String yourValue) {

        this.yourValue = yourValue;
    }
}