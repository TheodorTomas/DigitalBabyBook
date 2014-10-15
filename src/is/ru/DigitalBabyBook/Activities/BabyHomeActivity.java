package is.ru.DigitalBabyBook.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import is.ru.DigitalBabyBook.Global;
import is.ru.DigitalBabyBook.R;

/**
 * Created by arnif on 10/14/14.
 */
public class BabyHomeActivity extends Activity {

    private Global global = Global.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //remove top bar
        setContentView(R.layout.baby_home);


        TextView textView = (TextView) this.findViewById(R.id.home_babyName);
        textView.setText(global.selectedBaby.getName());

        //TextView textView1 = (TextView) this.findViewById(R.id.home_babyAge);
        //textView1.setText(global.baby.getDateOfBirth());
    }
}