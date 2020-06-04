package com.example.evento;

import android.provider.BaseColumns;

public class EventContract {

    private EventContract() {
    }

    public static final class EventEntry implements BaseColumns{

        public static final String TABLE_NAME = "eventlist";
        public static final String COLUMN_EVENT_NAME = "eventname";
        public static final String COLUMN_EVENT_HOST_NAME = "eventhostname";
        public static final String COLUMN_EVENT_LOCATION = "eventlocation";
        public static final String COLUMN_EVENT_DATE = "eventdate";
        public static final String COLUMN_EVENT_TIME = "eventtime";
        public static final String COLUMN_EVENT_COST = "eventcost";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
