package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    ImageView imageView;
    ListView listView;
    ArrayList<String> arList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    EventGetterSetter egs = new EventGetterSetter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

    }
}
