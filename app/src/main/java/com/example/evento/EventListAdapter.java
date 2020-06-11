package com.example.evento;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {

    private Context mContext;
    private Cursor mCursor;
    private AdapterCallback mAdapterCallback;

    public EventListAdapter(Context mContext, Cursor mCursor) {
        this.mContext = mContext;
        this.mCursor = mCursor;
    }

    public EventListAdapter(Context context) {
        try {
            this.mAdapterCallback = ((AdapterCallback) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement AdapterCallback.");
        }
    }


    public class EventViewHolder extends RecyclerView.ViewHolder{

        public TextView eventnameText,hostnameText,eventlocationText,eventcostText;
        public RelativeLayout relativeLayout;


        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            eventnameText = itemView.findViewById(R.id.event_name);
            hostnameText = itemView.findViewById(R.id.event_hostname);
            eventlocationText = itemView.findViewById(R.id.event_location);
            eventcostText = itemView.findViewById(R.id.event_cost);
            relativeLayout = itemView.findViewById(R.id.relative);

        }


    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        final View view = inflater.inflate(R.layout.eventlist_model,parent,false);

        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EventViewHolder holder, int position) {

        if (!mCursor.moveToPosition(position))
        {
            return;
        }

        final String name_event = mCursor.getString(mCursor.getColumnIndex(EventContract.EventEntry.COLUMN_EVENT_NAME));
        String name_host = mCursor.getString(mCursor.getColumnIndex(EventContract.EventEntry.COLUMN_EVENT_HOST_NAME));
        String location_event = mCursor.getString(mCursor.getColumnIndex(EventContract.EventEntry.COLUMN_EVENT_LOCATION));
        int cost_event = mCursor.getInt(mCursor.getColumnIndex(EventContract.EventEntry.COLUMN_EVENT_COST));
        long id = mCursor.getLong(mCursor.getColumnIndex(EventContract.EventEntry._ID));

        holder.eventnameText.setText(name_event);
        holder.hostnameText.setText(name_host);
        holder.eventlocationText.setText(location_event);
        holder.eventcostText.setText(String.valueOf(cost_event));
        holder.itemView.setTag(id);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(v.getContext(),EventDescription.class);
                intent.putExtra("key",name_event);
                v.getContext().startActivity(intent);
            }
        });

    }

    public static interface AdapterCallback {
        void onMethodCallback(String yourValue);
    }


    @Override
    public int getItemCount() {

        return mCursor.getCount();

    }

    public void swapCursor(Cursor newCursor)
    {

        if (mCursor != null)
        {

            mCursor.close();

        }
        mCursor = newCursor;

        if (newCursor != null)
        {
            notifyDataSetChanged();
        }

    }
}
