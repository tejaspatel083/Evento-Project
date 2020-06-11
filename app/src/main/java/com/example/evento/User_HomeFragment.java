package com.example.evento;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class User_HomeFragment extends Fragment {

    private EventListAdapter mAdapter;
    private SQLiteDatabase mDatabase;
    private EditText search_text;
    private Spinner event_type;
    private static final String[] category = {"Select","Sports", "Conference", "Workshop", "Reunion", "Party", "Trade Show","Galas"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View event_View =  inflater.inflate(R.layout.fragment_home,container,false);

        event_type = event_View.findViewById(R.id.filter_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,category);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        event_type.setAdapter(adapter);



        EventDBHelper eventDBHelper = new EventDBHelper(getActivity());
        mDatabase = eventDBHelper.getWritableDatabase();

        search_text = event_View.findViewById(R.id.searchbar);

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
