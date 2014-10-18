package is.ru.DigitalBabyBook.Activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import is.ru.DigitalBabyBook.Global;
import is.ru.DigitalBabyBook.R;
import is.ru.DigitalBabyBook.adapters.BabyAdapter;
import is.ru.DigitalBabyBook.domain.Baby;

import java.util.Calendar;

/**
 * Created by arnif on 10/14/14.
 */
public class CreateBaby extends Activity {

    private Baby baby = new Baby();
    private Global global = Global.getInstance();
    private BabyAdapter mBA = new BabyAdapter( this );

    private Button pickDate;
    private Button addBaby;
    private TextView dateDisplay;
    private int mYear;
    private int mMonth;
    private int mDay;

    static final int DATE_DIALOG_ID = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //remove top bar
        setContentView(R.layout.create_baby);


        dateDisplay = (TextView) this.findViewById(R.id.dateDisplay);
        pickDate = (Button) this.findViewById(R.id.datePicker);


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

        addBaby = (Button) this.findViewById(R.id.addBaby);
        addBaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText babyName = (EditText) findViewById(R.id.babyName);
                if (babyName.getText().toString().equals("")){
                    String error = "Name is required!";
                    ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.RED);
                    SpannableStringBuilder ssbuilder = new SpannableStringBuilder(error);
                    ssbuilder.setSpan(fgcspan, 0, error.length(), 0);
                    babyName.setError(ssbuilder);
                }
                else{
                    addBaby(v);
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String gender = extras.getString("gender");

            TextView textView = (TextView) this.findViewById(R.id.babyGender);
            textView.setText(gender.toUpperCase());
            if (textView.getText().equals("BOY")){
                textView.setBackgroundColor(Color.rgb(51, 181, 229));
                textView.setTextColor(Color.WHITE);
            }
            else{
                textView.setBackgroundColor(Color.rgb(246, 96, 171));
                textView.setTextColor(Color.WHITE);
            }

            baby.setGender(gender);

        } else {
            try {
                throw new Exception("you shouldn't have come this far!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addBaby(View view) {

        Double weight, size;

        TextView babyName = (TextView) this.findViewById(R.id.babyName);
        TextView dateOfBirth = (TextView) this.findViewById(R.id.dateDisplay);
        TextView babyWeight = (TextView) this.findViewById(R.id.babyWeight);
        TextView babySize = (TextView) this.findViewById(R.id.babySize);
        TextView babyHairColor = (TextView) this.findViewById(R.id.babyHairColor);
        TextView placeOfBirth = (TextView) this.findViewById(R.id.placeOfBirth);

        if (babyWeight.getText().toString().equals("")){
            weight = 0.0;
        }
        else {
            weight = Double.parseDouble(babyWeight.getText().toString());
        }
        if (babySize.getText().toString().equals("")) {
            size = 0.0;
        }
        else{
            size = Double.parseDouble(babySize.getText().toString());
        }
        baby.setName(babyName.getText().toString());
        baby.setDateOfBirth(dateOfBirth.getText().toString());
        baby.setWeight(weight);
        baby.setSize(size);
        baby.setHairColor(babyHairColor.getText().toString());
        baby.setBirthLocation(placeOfBirth.getText().toString());

        global.selectedBaby = baby; //set the baby to global (later save to db)

        //(String name, String birthLocation, String gender, double size, double weight, String hairColor )
        long l = mBA.insertBaby(babyName.getText().toString(),baby.getDateOfBirth(), baby.getBirthLocation(), baby.getGender(), baby.getSize(), baby.getWeight(), baby.getHairColor());
        mBA.close();

        Intent i = new Intent(getBaseContext(), BabyHomeActivity.class);
        i.putExtra("babyId", l);

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