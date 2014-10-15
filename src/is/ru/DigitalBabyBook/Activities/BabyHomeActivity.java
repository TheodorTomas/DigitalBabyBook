package is.ru.DigitalBabyBook.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import is.ru.DigitalBabyBook.Global;
import is.ru.DigitalBabyBook.R;
import is.ru.DigitalBabyBook.adapters.BabyAdapter;
import is.ru.DigitalBabyBook.domain.Baby;

/**
 * Created by arnif on 10/14/14.
 */
public class BabyHomeActivity extends Activity {

    private Global global = Global.getInstance();
    private BabyAdapter mBA = new BabyAdapter( this );
    private SimpleCursorAdapter mCA;
    private Cursor mCursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //remove top bar
        setContentView(R.layout.baby_home);


        Baby baby = new Baby();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            long babyId = extras.getLong("babyId");
            if (babyId > -1) {
                mCursor = mBA.queryBaby(babyId);

                if (mCursor.moveToFirst()) {
                    do {
                        //"name 1", "dateOfBirth 2", "birthLocation 3", "gender 4", "size 5", "weight 6", "hairColor 7"
                        baby.setName(mCursor.getString(1));
                        baby.setDateOfBirth(mCursor.getString(2));
                        baby.setBirthLocation(mCursor.getString(3));
                        baby.setGender(mCursor.getString(4));
                        baby.setSize(mCursor.getDouble(5));
                        baby.setWeight(mCursor.getDouble(6));
                        baby.setHairColor(mCursor.getString(7));

                    } while(mCursor.moveToNext());

                }
                mBA.close();
            }

            TextView textView = (TextView) this.findViewById(R.id.home_babyName);
            textView.setText(baby.getName());
        }




        //TextView textView1 = (TextView) this.findViewById(R.id.home_babyAge);
        //textView1.setText(global.baby.getDateOfBirth());
    }

    public void goToEventForm(View view) {

        startActivity(new Intent(getBaseContext(), AddEventFormActivity.class));

    }

    public void deleteBabies(View view) {
        final BabyHomeActivity babyHomeActivity = this;
        //thx http://stackoverflow.com/questions/2257963/how-to-show-a-dialog-to-confirm-that-the-user-wishes-to-exit-an-android-activity
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Reset Babies")
                .setMessage("Are you sure you want to reset your babies?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BabyAdapter babyAdapter = new BabyAdapter(babyHomeActivity);
                        babyAdapter.deleteAll();

                        startActivity(new Intent(getBaseContext(),MainActivity.class));
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}