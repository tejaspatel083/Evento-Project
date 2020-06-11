package com.example.evento;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment {

    private EventListAdapter mAdapter;
    private SQLiteDatabase mDatabase;
    private RelativeLayout layout;
    private Spinner event_type;
    private static final String[] category = {"Select","Sports", "Conference", "Workshop", "Reunion", "Party", "Trade Show","Galas"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View event_View =  inflater.inflate(R.layout.fragment_home,container,false);


        EventDBHelper eventDBHelper = new EventDBHelper(getActivity());
        mDatabase = eventDBHelper.getWritableDatabase();

        event_type = event_View.findViewById(R.id.filter_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,category);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        event_type.setAdapter(adapter);




        RecyclerView recyclerView = event_View.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mAdapter = new EventListAdapter(getActivity(),getAllEvents());
        recyclerView.setAdapter(mAdapter);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                removeEvent((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);


        return event_View;

    }


    private void removeEvent(long id)
    {

        AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());

        adb.setTitle("Delete?");
        adb.setMessage("Are you sure you want to delete?");

        final long item_id = id;

        adb.setNegativeButton("Cancel", new AlertDialog.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                mAdapter.swapCursor(getAllEvents());

            }});
        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {


                mDatabase.delete(EventContract.EventEntry.TABLE_NAME,
                        EventContract.EventEntry._ID + "=" +item_id , null);
                mAdapter.swapCursor(getAllEvents());

            }});


        adb.show();

    }

    private Cursor getAllEvents()
    {
        return mDatabase.query(
                EventContract.EventEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                EventContract.EventEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }


}
