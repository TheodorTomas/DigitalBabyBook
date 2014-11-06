package is.ru.DigitalBabyBook.Activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.*;
import is.ru.DigitalBabyBook.Global;
import is.ru.DigitalBabyBook.R;
import is.ru.DigitalBabyBook.adapters.EventAdapter;
import is.ru.DigitalBabyBook.adapters.FavoriteEventAdapter;
import is.ru.DigitalBabyBook.domain.FavoriteEvent;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Tommz on 20.10.2014.
 */
public class CreateFavorite extends Activity {

    private FavoriteEvent event = new FavoriteEvent();
    private Global global = Global.getInstance();
    private FavoriteEventAdapter favoriteEventAdapter = new FavoriteEventAdapter(this);
    private EventAdapter eventAdapter = new EventAdapter(this);

    private ImageView pickDate;
    private TextView dateDisplay;
    private int mYear;
    private int mMonth;
    private int mDay;
    private String group;
    private String type;
    private boolean edit = false;
    private int favoriteID;
    private int eventId;
    private String tempDescription;
    private boolean valid;

    static final int DATE_DIALOG_ID = 0;
    private static int LOAD_IMAGE_RESULTS = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //remove top bar
        setContentView(R.layout.add_favorite);

        Bundle extras = getIntent().getExtras();

        group = extras.getString("group");
        type = extras.getString("type");

        edit = extras.getBoolean("edit");
        favoriteID = extras.getInt("favoriteId");
        eventId = extras.getInt("eventId");

        dateDisplay = (TextView) this.findViewById(R.id.holiday_dateDisplay);
        pickDate = (ImageView) this.findViewById(R.id.holiday_datePicker);


        pickDate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                showDialog(DATE_DIALOG_ID);
            }
        });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        updateDisplay();

        TextView favoriteType = (TextView) this.findViewById(R.id.favoriteType);
        favoriteType.setText(type);
        Button addFavorite = (Button) this.findViewById(R.id.addFavorite);
        addFavorite.setText("Add " + type);

        if (global.selectedBaby.getGender().equals("boy")){
            favoriteType.setBackgroundColor(Color.rgb(51, 181, 229));
            addFavorite.setBackground(getResources().getDrawable(R.drawable.boybtnholiday));
        }
        else {
            favoriteType.setBackgroundColor(Color.rgb(246, 96, 171));
            addFavorite.setBackground(getResources().getDrawable(R.drawable.girlbtnholiday));
        }
        dateDisplay.setText("Date");
        dateDisplay.setTextColor(Color.rgb(173, 173, 173));

        if (edit) {
            changeToEditMode();
        }

    }
    public void addFavorite(View view) {
        // store group, type and additional input
        EditText name = (EditText) this.findViewById(R.id.name);

        if (valid && !(name.getText().toString().equals(""))) {
            TextView dateOfHoliday = (TextView) this.findViewById(R.id.holiday_dateDisplay);

            TextView notes = (TextView) this.findViewById(R.id.eventDescription);

            event.setType(type);
            event.setDate(dateOfHoliday.getText().toString());
            event.setName(name.getText().toString());
            event.setNotes(notes.getText().toString());
            event.setBaby(global.selectedBaby);

            tempDescription = createDescription(dateOfHoliday.getText().toString());
            event.setEventDescription(tempDescription);

            if (edit) {
                //edit event
                System.out.println("edit the event");
                System.out.println("Event id " + eventId );
                System.out.println("favorite id " + favoriteID);
                long updateEventId = eventAdapter.updateEvent(
                        eventId,
                        global.selectedBaby.getId(),
                        event.getEventDescription(),
                        event.getDate(),
                        event.getPhotos(),
                        event.getType());
                eventAdapter.close();

                long updateFavoriteId = favoriteEventAdapter.updateFavorite(
                        favoriteID,
                        global.selectedBaby.getId(),
                        eventId,
                        event.getEventDescription(),
                        event.getDate(),
                        event.getPhotos(),
                        event.getNotes(),
                        event.getName());
                favoriteEventAdapter.close();

                System.out.println("update event ID " + updateEventId );
                System.out.println("update favorite ID " + updateFavoriteId);
            } else {
                //add to eventDatabase
                long eventID = eventAdapter.insertEvent(
                        global.selectedBaby.getId(),
                        tempDescription,
                        event.getDate(),
                        event.getPhotos(),
                        event.getType());
                eventAdapter.close();


                //add to db
                long l = favoriteEventAdapter.insertFavorite(
                        global.selectedBaby.getId(),
                        eventID,
                        event.getEventDescription(),
                        event.getDate(),
                        event.getPhotos(),
                        event.getNotes(),
                        event.getName());
                        favoriteEventAdapter.close();
            }


            Intent i = new Intent(getBaseContext(), BabyHomeActivity.class);
            i.putExtra("babyId", global.selectedBaby.getId());

            startActivity(i);
        }
        else{
            if (!valid) {
                Toast.makeText(getApplicationContext(), "Please Insert Date", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Please insert Name", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void addPhoto(View view) {
        // Create the Intent for Image Gallery.
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        // Start new activity with the LOAD_IMAGE_RESULTS to handle back the results when image is picked from the Image Gallery.
        startActivityForResult(i, LOAD_IMAGE_RESULTS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Here we need to check if the activity that was triggers was the Image Gallery.
        // If it is the requestCode will match the LOAD_IMAGE_RESULTS value.
        // If the resultCode is RESULT_OK and there is some data we know that an image was picked.
        if (requestCode == LOAD_IMAGE_RESULTS && resultCode == RESULT_OK && data != null) {
            // Let's read picked image data - its URI
            Uri pickedImage = data.getData();
            // Let's read picked image path using content resolver
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));


            event.setPhotos(imagePath);

            // Now we need to set the GUI ImageView data with data read from the picked file.
            //image.setImageBitmap(BitmapFactory.decodeFile(imagePath));

            // At the end remember to close the cursor or you will end with the RuntimeException!
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.add_holiday_photo);
            Bitmap d = new BitmapDrawable(getResources(),event.getPhotos()).getBitmap();

            int nh = (int) (d.getHeight() * (256.0 / d.getWidth()));
            Bitmap scaled = Bitmap.createScaledBitmap(d, 256, nh, true);

            imageView.setImageBitmap(scaled);
        }
    }

    private void changeToEditMode() {
        valid = true;
        System.out.println("EventID " + eventId);
        System.out.println("favoriteID " + favoriteID);
        Button addFavorite = (Button) this.findViewById(R.id.addFavorite);
        addFavorite.setText("Edit " + type);
        ArrayList<FavoriteEvent> favoriteEvents = new ArrayList<FavoriteEvent>();
        FavoriteEvent h;
        Cursor mCursor = favoriteEventAdapter.queryFavoriteById(favoriteID);
        if (mCursor.moveToFirst()) {
            do {
                //  "babyID  1", "eventID  2", "description 3", "date  4", "photo 5", "notes 6"
                h = new FavoriteEvent();
                h.setFavoriteID(mCursor.getInt(0));
                h.setEventDescription(mCursor.getString(3));
                h.setDate(mCursor.getString(4));
                h.setPhotos(mCursor.getString(5));
                h.setNotes(mCursor.getString(6));

                favoriteEvents.add(h);

            } while (mCursor.moveToNext());
        }
        favoriteEventAdapter.close();

        h = favoriteEvents.get(0);

        System.out.println(h.getEventDescription());

        TextView notes = (TextView) this.findViewById(R.id.eventDescription);
        TextView dateOfHoliday = (TextView) this.findViewById(R.id.holiday_dateDisplay);

        notes.setText(h.getNotes());
        dateOfHoliday.setText(h.getDate());

        if (h.getPhotos() != null) {
            ImageView imageView = (ImageView) findViewById(R.id.add_holiday_photo);
            Bitmap d = new BitmapDrawable(getResources(),h.getPhotos()).getBitmap();

            int nh = (int) (d.getHeight() * (256.0 / d.getWidth()));
            Bitmap scaled = Bitmap.createScaledBitmap(d, 256, nh, true);

            imageView.setImageBitmap(scaled);
        }
    }

    private String createDescription(String dateOfHoliday) {
        return global.selectedBaby.getName() + " favorite " + type + " is " + event.getName() +
                " at age " + global.calculateAge(global.selectedBaby.getDateOfBirth(), dateOfHoliday);
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }

    private void updateDisplay() {

        dateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mMonth + 1).append("-")
                        .append(mDay).append("-")
                        .append(mYear).append(" "));
        dateDisplay.setTextColor(Color.BLACK);
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {

                    String s = monthOfYear + 1 + "-" + dayOfMonth + "-" + year;

                    if (global.validateDateInput(global.selectedBaby.getDateOfBirth(), s)) {
                        mYear = year;
                        mMonth = monthOfYear;
                        mDay = dayOfMonth;
                        updateDisplay();
                        valid = true;
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Date, your baby was born " + global.selectedBaby.getDateOfBirth() , Toast.LENGTH_SHORT).show();
                    }




                }
            };

}
