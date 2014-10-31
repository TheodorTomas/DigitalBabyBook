package is.ru.DigitalBabyBook.Activities;

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
import is.ru.DigitalBabyBook.adapters.HolidayEventAdapter;
import is.ru.DigitalBabyBook.domain.HolidayEvent;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by arnif on 10/18/14.
 */
public class BabyHomeFragment extends Fragment {

    private static Global global = Global.getInstance();
    private HolidayEventAdapter mHEA;
    private Cursor mCursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.baby_home_tab, container, false);

        mHEA = new HolidayEventAdapter(V.getContext());

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

        ArrayList<HolidayEvent> holidayEvents = new ArrayList<HolidayEvent>();
        mCursor = mHEA.queryHoliday(babyId);

        if (mCursor.moveToFirst()) {
            do {
                // "babyID 1", "type 2", "description 3", "date 4", "location 5", "photo 6", "gifts 7", "notes 8" };
                HolidayEvent b = new HolidayEvent();
                b.setType(mCursor.getString(2));
                b.setEventDescription(mCursor.getString(3));
                b.setDate(mCursor.getString(4));
                b.setLocation(mCursor.getString(5));
                b.setPhotos(mCursor.getString(6));
                b.setGifts(mCursor.getString(7));
                b.setNotes(mCursor.getString(8));

                holidayEvents.add(b);

            } while (mCursor.moveToNext());
        }

        mHEA.close();
        Collections.reverse(holidayEvents);
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.home_eventFeed);
        //LinearLayout linearLayout = new LinearLayout(v.getContext());
        ListView listView = new ListView(v.getContext());
        ArrayList<String> values = new ArrayList<String>();

        for (HolidayEvent holidayEvent : holidayEvents) {
            values.add(holidayEvent.getEventDescription());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), R.layout.event_home_feed_list, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
            }
        });




        linearLayout.addView(listView, TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT);
        LinearLayout.LayoutParams layoutParams  = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 600);
        linearLayout.setLayoutParams(layoutParams);
    }




}
