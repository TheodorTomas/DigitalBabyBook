package is.ru.DigitalBabyBook.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import is.ru.DigitalBabyBook.R;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //remove top bar
        setContentView(R.layout.main);
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
