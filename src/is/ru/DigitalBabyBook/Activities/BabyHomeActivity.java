package is.ru.DigitalBabyBook.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.view.Window;
import is.ru.DigitalBabyBook.Global;
import is.ru.DigitalBabyBook.R;
import is.ru.DigitalBabyBook.adapters.*;
import is.ru.DigitalBabyBook.domain.Baby;

/**
 * Created by arnif on 10/14/14.
 */
public class BabyHomeActivity extends FragmentActivity {

    private Global global = Global.getInstance();
    private BabyAdapter mBA = new BabyAdapter( this );
    private Cursor mCursor;


    private FragmentTabHost mTabHost;

    @Override
    public void onBackPressed() {
//        moveTaskToBack(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //remove top bar
        setContentView(R.layout.baby_tabs);

        if (global.selectedBaby == null) {
            Baby baby = new Baby();

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                long babyId = extras.getLong("babyId");
                if (babyId > -1) {
                    mCursor = mBA.queryBaby(babyId);

                    if (mCursor.moveToFirst()) {
                        do {
                            //"name 1", "dateOfBirth 2", "birthLocation 3", "gender 4", "size 5", "weight 6", "hairColor 7", "profilePicture 8"
                            baby.setName(mCursor.getString(1));
                            baby.setDateOfBirth(mCursor.getString(2));
                            baby.setBirthLocation(mCursor.getString(3));
                            baby.setGender(mCursor.getString(4));
                            baby.setSize(mCursor.getDouble(5));
                            baby.setWeight(mCursor.getDouble(6));
                            baby.setHairColor(mCursor.getString(7));
                            baby.setProfilePicture(mCursor.getString(8));

                        } while(mCursor.moveToNext());

                    }
                    mBA.close();
                    global.selectedBaby = baby;
                }
            }

        }

        //thx http://stackoverflow.com/questions/17227855/tabhost-with-fragments-and-fragmentactivity
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("Home"),
                BabyHomeFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("Event"),
                EventListFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("Checklist"),
                ChecklistHomeFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab4").setIndicator("", getResources().getDrawable(R.drawable.ic_action_settings)),
                SettingsHomeFragment.class, null);

    }

    //.setIndicator(tabView).setContent(R.id.edit_species_tab);

    public void deleteBabies(View view) {
        //TODO this should be in settings
        final BabyHomeActivity babyHomeActivity = this;
        //thx http://stackoverflow.com/questions/2257963/how-to-show-a-dialog-to-confirm-that-the-user-wishes-to-exit-an-android-activity
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Reset baby")
                .setMessage("Are you sure you want to reset your baby?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BabyAdapter babyAdapter = new BabyAdapter(babyHomeActivity);
                        HolidayEventAdapter holidayEventAdapter = new HolidayEventAdapter(babyHomeActivity);
                        EventAdapter eventAdapter = new EventAdapter(babyHomeActivity);
                        FirstEventAdapter firstEventAdapter = new FirstEventAdapter(babyHomeActivity);
                        FavoriteEventAdapter favoriteEventAdapter = new FavoriteEventAdapter(babyHomeActivity);
                        ChecklistAdapter checklistAdapter = new ChecklistAdapter(babyHomeActivity);
                        babyAdapter.deleteAll();
                        holidayEventAdapter.deleteAll();
                        firstEventAdapter.deleteAll();
                        favoriteEventAdapter.deleteAll();
                        eventAdapter.deleteAll();
                        checklistAdapter.recreate();

                        startActivity(new Intent(getBaseContext(), MainActivity.class));
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    public void editBaby(View view) {
        Intent i = new Intent(getBaseContext(), CreateBaby.class);
        i.putExtra("edit", true);
        i.putExtra("gender", global.selectedBaby.getGender());
        startActivity(i);
    }
}