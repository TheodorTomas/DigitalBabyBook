package is.ru.DigitalBabyBook.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import is.ru.DigitalBabyBook.Global;
import is.ru.DigitalBabyBook.R;
import is.ru.DigitalBabyBook.adapters.EventAdapter;
import is.ru.DigitalBabyBook.adapters.HolidayEventAdapter;
import is.ru.DigitalBabyBook.domain.Event;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by arnif on 10/18/14.
 */
public class BabyHomeFragment extends Fragment {

    private static Global global = Global.getInstance();
    private HolidayEventAdapter holiDayEventAdapter;
    private EventAdapter eventAdapter;
    private Cursor mCursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.baby_home_tab, container, false);

        holiDayEventAdapter = new HolidayEventAdapter(V.getContext());
        eventAdapter = new EventAdapter(V.getContext());

        TextView babyName = (TextView) V.findViewById(R.id.home_babyName);
        babyName.setText(global.selectedBaby.getName());

        String age = global.calculateAge(global.selectedBaby.getDateOfBirth(), null);

        if (global.selectedBaby.getProfilePicture() != null) {
            ImageView profileImage = (ImageView) V.findViewById(R.id.home_babyProfilePicture);
            Bitmap d = new BitmapDrawable(V.getResources(), global.selectedBaby.getProfilePicture()).getBitmap();

            int nh = (int) (d.getHeight() * (256.0 / d.getWidth()));
            Bitmap scaled = Bitmap.createScaledBitmap(d, 256, nh, true);

            profileImage.setImageBitmap(scaled);
        }

        TextView babyAge = (TextView) V.findViewById(R.id.home_babyAge);
        babyAge.setText(age + " old");

        updateHolidayEventFeed(V);
        return V;
    }

    private void updateHolidayEventFeed(View v) {
        long babyId = global.selectedBaby.getId();

        final ArrayList<Event> events = new ArrayList<Event>();
        mCursor = eventAdapter.queryEventByBabyID(babyId);

        if (mCursor.moveToFirst()) {
            do {
                //"babyID  1", "description  2", "date  3", "photo  4", "type 5"
                Event e = new Event();
                e.setEventID(mCursor.getInt(0));
                e.setEventDescription(mCursor.getString(2));
                e.setDate(mCursor.getString(3));
                e.setPhotos(mCursor.getString(4));
                e.setType(mCursor.getString(5));

                events.add(e);

            } while (mCursor.moveToNext());
        }

        eventAdapter.close();
        Collections.reverse(events);
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.home_eventFeed);
        //LinearLayout linearLayout = new LinearLayout(v.getContext());
        ListView listView = new ListView(v.getContext());
        ArrayList<String> values = new ArrayList<String>();

        for (Event event : events) {
            values.add(event.getEventDescription());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), R.layout.event_home_feed_list, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
                Event event = events.get(position);
                System.out.println(event.getEventDescription());
                Intent i = new Intent(view.getContext(), ViewHoliday.class);
                i.putExtra("eventId", event.getEventID());
                i.putExtra("eventType", event.getType());
                startActivity(i);
            }
        });




        linearLayout.addView(listView, TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT);
        LinearLayout.LayoutParams layoutParams  = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 600);
        linearLayout.setLayoutParams(layoutParams);
    }




}
