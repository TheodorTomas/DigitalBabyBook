package is.ru.DigitalBabyBook.Activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import is.ru.DigitalBabyBook.Global;
import is.ru.DigitalBabyBook.R;
import is.ru.DigitalBabyBook.adapters.HolidayEventAdapter;
import is.ru.DigitalBabyBook.domain.HolidayEvent;

import java.util.ArrayList;

/**
 * Created by arnif on 10/31/14.
 */
public class ViewHoliday extends Activity {

    private Global global = Global.getInstance();
    private HolidayEventAdapter holidayEventAdapter = new HolidayEventAdapter(this);
    private HolidayEvent holidayEvent;
    private String eventType;
    private int eventId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_holiday);

        Bundle extras = getIntent().getExtras();

        eventType = extras.getString("eventType");
        eventId = extras.getInt("eventId");

        ArrayList<HolidayEvent> holidayEvents = new ArrayList<HolidayEvent>();
        HolidayEvent h;

        System.out.println("Event id " + eventId);
        Cursor mCursor = holidayEventAdapter.queryHolidayByEventId(eventId);
        //Cursor mCursor = holidayEventAdapter.queryHoliday();

        if (mCursor.moveToFirst()) {
            do {
                //  "babyID  1", "eventID  2", "description 3", "date  4", "location 5", "photo 6", "gifts 7", "notes  8"
                h = new HolidayEvent();
                h.setHoliDayID(mCursor.getInt(0));
                h.setEventDescription(mCursor.getString(3));
                h.setDate(mCursor.getString(4));
                h.setLocation(mCursor.getString(5));
                h.setPhotos(mCursor.getString(6));
                h.setGifts(mCursor.getString(7));
                h.setNotes(mCursor.getString(8));

                holidayEvents.add(h);

            } while (mCursor.moveToNext());
        }
        holidayEventAdapter.close();

        holidayEvent = holidayEvents.get(0);

        TextView holidayType = (TextView) this.findViewById(R.id.holidayType);
        holidayType.setText(eventType);

        if (global.selectedBaby.getGender().equals("boy")){
            holidayType.setBackgroundColor(Color.rgb(51, 181, 229));
        }
        else {
            holidayType.setBackgroundColor(Color.rgb(246, 96, 171));
        }

        TextView babyName = (TextView) this.findViewById(R.id.babyName);
        babyName.setText(holidayEvent.getEventDescription());

        TextView eventLocation = (TextView) this.findViewById(R.id.event_location);
        eventLocation.setText("Location: " + holidayEvent.getLocation());

        TextView eventGifts = (TextView) this.findViewById(R.id.event_gifts);
        eventGifts.setText("Gifts: " + holidayEvent.getGifts());

        TextView eventNotes = (TextView) this.findViewById(R.id.event_notes);
        eventNotes.setText("Notes: " + holidayEvent.getNotes());

    }

    public void editEvent(View v) {
        System.out.println("holiday ID " + holidayEvent.getHoliDayID());
        System.out.println("event ID " + eventId);

        Intent i = new Intent(v.getContext(), CreateHoliday.class);
        i.putExtra("type", eventType);
        i.putExtra("group", "Holiday");
        i.putExtra("edit", true);
        i.putExtra("holidayId", holidayEvent.getHoliDayID());
        i.putExtra("eventId", eventId);
        startActivity(i);
    }
}