package is.ru.DigitalBabyBook.Activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import is.ru.DigitalBabyBook.Global;
import is.ru.DigitalBabyBook.R;
import is.ru.DigitalBabyBook.adapters.HolidayEventAdapter;
import is.ru.DigitalBabyBook.domain.BirthdayEvent;

import java.util.Calendar;

/**
 * Created by Tommz on 18.10.2014.
 */
public class CreateHoliday extends Activity {


    private BirthdayEvent event;
    private Global global = Global.getInstance();
    private HolidayEventAdapter mHEA = new HolidayEventAdapter(this);

    private ImageView pickDate;
    private TextView dateDisplay;
    private int mYear;
    private int mMonth;
    private int mDay;

    static final int DATE_DIALOG_ID = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //remove top bar
        setContentView(R.layout.add_holiday);

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

    }
    public void addHoliday(View view) {
        // store group, type and additional input
        TextView notes = (TextView) this.findViewById(R.id.eventDescription);
        TextView dateOfHoliday = (TextView) this.findViewById(R.id.holiday_dateDisplay);
        TextView location = (TextView) this.findViewById(R.id.location);
        TextView photos = (TextView) this.findViewById(R.id.photos);
        TextView gifts = (TextView) this.findViewById(R.id.gifts);
//        TextView baby = (TextView) this.findViewById(R.id.baby); ???

        Bundle extras = getIntent().getExtras();

        String group = extras.getString("group");
        String type = extras.getString("type");
        String tempDescription = global.selectedBaby.getName() + " had a great " + type;

        event = new BirthdayEvent(
                type,
                tempDescription,
                dateOfHoliday.toString(),
                location.toString(),
                photos.toString(),
                gifts.toString(),
                notes.toString(),
                global.selectedBaby
        );

        //add to db
        long l = mHEA.insertHoliday(
                global.selectedBaby.getId(),
                event.getType(),
                event.getEventDescription(),
                event.getDate(),
                event.getLocation(),
                event.getPhotos(),
                event.getGifts(),
                event.getNotes());
        mHEA.close();

        Intent i = new Intent(getBaseContext(), BabyHomeActivity.class);
        i.putExtra("babyId", global.selectedBaby.getId());

        startActivity(i);


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
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };

}