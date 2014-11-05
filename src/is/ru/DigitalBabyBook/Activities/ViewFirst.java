package is.ru.DigitalBabyBook.Activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import is.ru.DigitalBabyBook.Global;
import is.ru.DigitalBabyBook.R;
import is.ru.DigitalBabyBook.adapters.FirstEventAdapter;
import is.ru.DigitalBabyBook.domain.FirstEvent;

import java.util.ArrayList;

/**
 * Created by arnif on 11/5/14.
 */
public class ViewFirst extends Activity {

    private Global global = Global.getInstance();
    private FirstEventAdapter firstEventAdapter = new FirstEventAdapter(this);
    private FirstEvent firstEvent;
    private String eventType;
    private int eventId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_first);

        Bundle extras = getIntent().getExtras();

        eventType = extras.getString("eventType");
        eventId = extras.getInt("eventId");

        ArrayList<FirstEvent> firstEvents = new ArrayList<FirstEvent>();
        FirstEvent f;

        System.out.println("Event id " + eventId);
        Cursor mCursor = firstEventAdapter.queryFirstByEventId(eventId);
        //Cursor mCursor = holidayEventAdapter.queryHoliday();

        if (mCursor.moveToFirst()) {
            do {
                //  "babyID  1", "eventID  2", "description 3", "date  4", "location 5", "photo 6","notes 7" "witness 8"
                f = new FirstEvent();
                f.setFirstID(mCursor.getInt(0));
                f.setEventDescription(mCursor.getString(3));
                f.setDate(mCursor.getString(4));
                f.setLocation(mCursor.getString(5));
                f.setPhotos(mCursor.getString(6));
                f.setNotes(mCursor.getString(7));
                f.setWitness(mCursor.getString(8));

                firstEvents.add(f);

            } while (mCursor.moveToNext());
        }
        firstEventAdapter.close();

        firstEvent = firstEvents.get(0);

        TextView firstType = (TextView) this.findViewById(R.id.first_type);
        firstType.setText(eventType);

        if (global.selectedBaby.getGender().equals("boy")){
            firstType.setBackgroundColor(Color.rgb(51, 181, 229));
        }
        else {
            firstType.setBackgroundColor(Color.rgb(246, 96, 171));
        }

        TextView babyName = (TextView) this.findViewById(R.id.babyName);
        babyName.setText(firstEvent.getEventDescription());

        TextView eventLocation = (TextView) this.findViewById(R.id.first_location);
        eventLocation.setText("Location: " + firstEvent.getLocation());

        TextView eventGifts = (TextView) this.findViewById(R.id.first_witness);
        eventGifts.setText("Witness: " + firstEvent.getWitness());

        TextView eventNotes = (TextView) this.findViewById(R.id.first_notes);
        eventNotes.setText("Notes: " + firstEvent.getNotes());

        if (firstEvent.getPhotos() != null) {
            ImageView profileImage = (ImageView) this.findViewById(R.id.first_photo);
            Bitmap d = new BitmapDrawable(this.getResources(), firstEvent.getPhotos()).getBitmap();

            int nh = (int) (d.getHeight() * (256.0 / d.getWidth()));
            Bitmap scaled = Bitmap.createScaledBitmap(d, 256, nh, true);

            profileImage.setImageBitmap(scaled);
        }

    }

    public void editEvent(View v) {
        System.out.println("first ID " + firstEvent.getFirstID());
        System.out.println("event ID " + eventId);

        Intent i = new Intent(v.getContext(), CreateFirst.class);
        i.putExtra("type", eventType);
        i.putExtra("group", "First");
        i.putExtra("edit", true);
        i.putExtra("firstId", firstEvent.getFirstID());
        i.putExtra("eventId", eventId);
        startActivity(i);
    }
}