package com.example.evento;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BookingListAdapter extends BaseAdapter {

    private ArrayList<BookingListGetterSetter> arrayList;
    private TextView user,event,tickets,total;

    public BookingListAdapter(ArrayList<BookingListGetterSetter> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookinglist_model,parent,false);
        user = convertView.findViewById(R.id.bookinglist_email);
        event = convertView.findViewById(R.id.bookinglist_event);
        tickets = convertView.findViewById(R.id.bookinglist_tickets);
        total = convertView.findViewById(R.id.bookinglist_total);


        user.setText(arrayList.get(position).getUser_email());
        event.setText(arrayList.get(position).getEvent_name());
        tickets.setText(arrayList.get(position).getTickets());
        total.setText(arrayList.get(position).getTotal());

        return convertView;
    }
}
