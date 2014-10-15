package is.ru.DigitalBabyBook.Activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import is.ru.DigitalBabyBook.R;
import is.ru.DigitalBabyBook.adapters.BabyAdapter;

public class MainActivity extends Activity {

    private BabyAdapter mBA = new BabyAdapter( this );
    private SimpleCursorAdapter mCA;
    private Cursor mCursor;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //remove top bar
        setContentView(R.layout.main);

        mCursor = mBA.queryBaby();
        long babyId = -1;
        if (mCursor.moveToFirst()) {
            do {
                babyId =  mCursor.getLong(0);
            }while(mCursor.moveToNext());
        }
        if (babyId > -1) {
            Intent i = new Intent(getBaseContext(), BabyHomeActivity.class);
            i.putExtra("babyId", babyId);
            startActivity(i);
        }
    }

    public void createBaby(View view) {
        Button button = (Button) view;
        Intent i = new Intent(getBaseContext(), CreateBaby.class);
        int id = button.getId();
        if (id == R.id.btn_boy) {
            //intent with boy
            i.putExtra("gender", "boy");
        } else {
            //intent with girl
            i.putExtra("gender", "girl");
        }

        startActivity(i);

    }
}
