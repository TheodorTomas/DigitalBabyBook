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
import is.ru.DigitalBabyBook.adapters.HolidayEventAdapter;
import is.ru.DigitalBabyBook.domain.HolidayEvent;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Tommz on 18.10.2014.
 */
public class CreateHoliday extends Activity {


    private HolidayEvent event = new HolidayEvent();
    private Global global = Global.getInstance();
    private HolidayEventAdapter holidayEventAdapter = new HolidayEventAdapter(this);
    private EventAdapter eventAdapter = new EventAdapter(this);

    private ImageView pickDate;
    private TextView dateDisplay;
    private int mYear;
    private int mMonth;
    private int mDay;
    private String group;
    private String type;
    private boolean edit = false;
    private int holidayID;
    private int eventId;
    private String tempDescription;
    private boolean valid;

    static final int DATE_DIALOG_ID = 0;
    private static int LOAD_IMAGE_RESULTS = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //remove top bar
        setContentView(R.layout.add_holiday);

        Bundle extras = getIntent().getExtras();

        group = extras.getString("group");
        type = extras.getString("type");
        edit = extras.getBoolean("edit");
        holidayID = extras.getInt("holidayId");
        eventId = extras.getInt("eventId");

        valid = false;
        dateDisplay = (TextView) this.findViewById(R.id.holiday_dateDisplay);
        pickDate = (ImageView) this.findViewById(R.id.holiday_datePicker);

        pickDate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                showDialog(DATE_DIALOG_ID);
            }
        });
        dateDisplay.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                showDialog(DATE_DIALOG_ID);
            }
        });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        updateDisplay();

        TextView holidayType = (TextView) this.findViewById(R.id.holidayType);
        holidayType.setText(type);
        Button addHoliday = (Button) this.findViewById(R.id.addHoliday);
        addHoliday.setText("Add " + type);

        if (global.selectedBaby.getGender().equals("boy")){
            holidayType.setBackgroundColor(Color.rgb(51, 181, 229));
            addHoliday.setBackground(getResources().getDrawable(R.drawable.boybtnholiday));
        }
        else {
            holidayType.setBackgroundColor(Color.rgb(246, 96, 171));
            addHoliday.setBackground(getResources().getDrawable(R.drawable.girlbtnholiday));
        }
        dateDisplay.setText("Date");
        dateDisplay.setTextColor(Color.rgb(173, 173, 173));


        if (edit) {
            changeToEditMode();
        }
    }
    public void addHoliday(View view) {
        // store group, type and additional input
        if (valid) {
            TextView notes = (TextView) this.findViewById(R.id.eventDescription);
            TextView dateOfHoliday = (TextView) this.findViewById(R.id.holiday_dateDisplay);
            TextView location = (TextView) this.findViewById(R.id.location);
            TextView gifts = (TextView) this.findViewById(R.id.gifts);


            tempDescription = createDescription(dateOfHoliday.getText().toString());

            event.setType(type);
            event.setEventDescription(tempDescription);
            event.setDate(dateOfHoliday.getText().toString());
            event.setLocation(location.getText().toString());
            event.setGifts(gifts.getText().toString());
            event.setNotes(notes.getText().toString());
            event.setBaby(global.selectedBaby);

            if (edit) {
                //edit event
                System.out.println("edit the event");
                System.out.println("Event id " + eventId );
                System.out.println("Holiday id " + holidayID);
                long updateEventId = eventAdapter.updateEvent(
                        eventId,
                        global.selectedBaby.getId(),
                        event.getEventDescription(),
                        event.getDate(),
                        event.getPhotos(),
                        event.getType());
                eventAdapter.close();

                long updateHolidayId = holidayEventAdapter.updateHoliday(
                        holidayID,
                        global.selectedBaby.getId(),
                        eventId,
                        event.getEventDescription(),
                        event.getDate(),
                        event.getLocation(),
                        event.getPhotos(),
                        event.getGifts(),
                        event.getNotes());
                holidayEventAdapter.close();

                System.out.println("update event ID " + updateEventId );
                System.out.println("update holiday ID " + updateHolidayId);
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
                long l = holidayEventAdapter.insertHoliday(
                        global.selectedBaby.getId(),
                        eventID,
                        event.getEventDescription(),
                        event.getDate(),
                        event.getLocation(),
                        event.getPhotos(),
                        event.getGifts(),
                        event.getNotes());
                holidayEventAdapter.close();
            }


            Intent i = new Intent(getBaseContext(), BabyHomeActivity.class);
            i.putExtra("babyId", global.selectedBaby.getId());

            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(), "Please Insert Date", Toast.LENGTH_SHORT).show();
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
        System.out.println("holidayID " + holidayID);
        Button addHoliday = (Button) this.findViewById(R.id.addHoliday);
        addHoliday.setText("Edit " + type);
        ArrayList<HolidayEvent> holidayEvents = new ArrayList<HolidayEvent>();
        HolidayEvent h;
        Cursor mCursor = holidayEventAdapter.queryHolidayById(holidayID);
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

        h = holidayEvents.get(0);

        System.out.println(h.getEventDescription());

        TextView notes = (TextView) this.findViewById(R.id.eventDescription);
        TextView dateOfHoliday = (TextView) this.findViewById(R.id.holiday_dateDisplay);
        TextView location = (TextView) this.findViewById(R.id.location);
        TextView gifts = (TextView) this.findViewById(R.id.gifts);

        notes.setText(h.getNotes());
        dateOfHoliday.setText(h.getDate());
        location.setText(h.getLocation());
        gifts.setText(h.getGifts());

        if (h.getPhotos() != null) {
            ImageView imageView = (ImageView) findViewById(R.id.add_holiday_photo);
            Bitmap d = new BitmapDrawable(getResources(),h.getPhotos()).getBitmap();

            int nh = (int) (d.getHeight() * (256.0 / d.getWidth()));
            Bitmap scaled = Bitmap.createScaledBitmap(d, 256, nh, true);

            imageView.setImageBitmap(scaled);
        }
    }

    private String createDescription(String dateOfHoliday) {
        return global.selectedBaby.getName() + " had a great " + type +
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