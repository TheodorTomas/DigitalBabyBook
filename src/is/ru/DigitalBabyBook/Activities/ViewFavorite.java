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
import is.ru.DigitalBabyBook.adapters.FavoriteEventAdapter;
import is.ru.DigitalBabyBook.domain.FavoriteEvent;

import java.util.ArrayList;

/**
 * Created by arnif on 11/5/14.
 */
public class ViewFavorite extends Activity {

    private Global global = Global.getInstance();
    private FavoriteEventAdapter favoriteEventAdapter= new FavoriteEventAdapter(this);
    private FavoriteEvent favoriteEvent;
    private String eventType;
    private int eventId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_favorite);

        Bundle extras = getIntent().getExtras();

        eventType = extras.getString("eventType");
        eventId = extras.getInt("eventId");

        ArrayList<FavoriteEvent> favoriteEvents = new ArrayList<FavoriteEvent>();
        FavoriteEvent f;

        System.out.println("Event id " + eventId);
        Cursor mCursor = favoriteEventAdapter.queryFavoriteByEventId(eventId);
        //Cursor mCursor = holidayEventAdapter.queryHoliday();

        if (mCursor.moveToFirst()) {
            do {
                //  "babyID  1", "eventID  2", "description 3", "date  4",  "photo 5", , "notes  6"
                f = new FavoriteEvent();
                f.setFavoriteID(mCursor.getInt(0));
                f.setEventDescription(mCursor.getString(3));
                f.setDate(mCursor.getString(4));
                f.setPhotos(mCursor.getString(5));
                f.setNotes(mCursor.getString(6));
                f.setName(mCursor.getString(7));

                favoriteEvents.add(f);

            } while (mCursor.moveToNext());
        }

        favoriteEventAdapter.close();

        favoriteEvent = favoriteEvents.get(0);

        TextView favoriteType = (TextView) this.findViewById(R.id.favorite_type);
        favoriteType.setText(eventType);

        if (global.selectedBaby.getGender().equals("boy")){
            favoriteType.setBackgroundColor(Color.rgb(51, 181, 229));
        }
        else {
            favoriteType.setBackgroundColor(Color.rgb(246, 96, 171));
        }

        TextView babyName = (TextView) this.findViewById(R.id.babyName);
        babyName.setText(favoriteEvent.getEventDescription());

        TextView favoriteName = (TextView) this.findViewById(R.id.favorite_name); //name
        favoriteName.setText("Name: " + favoriteEvent.getName());

        TextView eventNotes = (TextView) this.findViewById(R.id.favorite_notes);
        eventNotes.setText("Notes: " + favoriteEvent.getNotes());

        if (favoriteEvent.getPhotos() != null) {
            ImageView profileImage = (ImageView) this.findViewById(R.id.favorite_photo);
            Bitmap d = new BitmapDrawable(this.getResources(), favoriteEvent.getPhotos()).getBitmap();

            int nh = (int) (d.getHeight() * (256.0 / d.getWidth()));
            Bitmap scaled = Bitmap.createScaledBitmap(d, 256, nh, true);

            profileImage.setImageBitmap(scaled);
        }
    }

    public void editEvent(View v) {
        System.out.println("first ID " + favoriteEvent.getFavoriteID());
        System.out.println("event ID " + eventId);

        Intent i = new Intent(v.getContext(), CreateFavorite.class);
        i.putExtra("type", eventType);
        i.putExtra("group", "Favorite");
        i.putExtra("edit", true);
        i.putExtra("favoriteId", favoriteEvent.getFavoriteID());
        i.putExtra("eventId", eventId);
        startActivity(i);
    }
}