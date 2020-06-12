package com.example.evento;

public class BookingListGetterSetter {

    public String user_email;
    public String event_name;
    public String tickets;
    public String total;

    public BookingListGetterSetter() {
    }

    public BookingListGetterSetter(String user_email, String event_name, String tickets, String total) {
        this.user_email = user_email;
        this.event_name = event_name;
        this.tickets = tickets;
        this.total = total;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
