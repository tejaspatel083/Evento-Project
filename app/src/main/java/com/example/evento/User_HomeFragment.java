package com.example.evento;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class User_HomeFragment extends Fragment {

    private EventListAdapter mAdapter;
    private SQLiteDatabase mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View event_View =  inflater.inflate(R.layout.fragment_home,container,false);


        EventDBHelper eventDBHelper = new EventDBHelper(getActivity());
        mDatabase = eventDBHelper.getWritableDatabase();

        RecyclerView recyclerView = event_View.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new EventListAdapter(getActivity(),getAllEvents());
        recyclerView.setAdapter(mAdapter);

        return event_View;

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
