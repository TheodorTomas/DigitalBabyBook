package is.ru.DigitalBabyBook.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import is.ru.DigitalBabyBook.Global;
import is.ru.DigitalBabyBook.R;
import is.ru.DigitalBabyBook.domain.Baby;

/**
 * Created by arnif on 10/14/14.
 */
public class CreateBaby extends Activity {

    private Baby baby = new Baby();
    private Global global = Global.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //remove top bar
        setContentView(R.layout.create_baby);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String gender = extras.getString("gender");

            TextView textView = (TextView) this.findViewById(R.id.babyGender);
            textView.setText(gender);

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
        TextView babyName = (TextView) this.findViewById(R.id.babyName);
        //TextView dateOfBirth = (TextView) this.findViewById(R.id.dateOfbirth);
        //TextView babyWeight = (TextView) this.findViewById(R.id.babyWeight);
        //TextView babySize = (TextView) this.findViewById(R.id.babySize);
        //TextView babyHairColor = (TextView) this.findViewById(R.id.babyHairColor);
        //TextView placeOfBirth = (TextView) this.findViewById(R.id.placeOfBirth);

        //Double weight = Double.parseDouble(babyWeight.getText().toString());
        //Double size = Double.parseDouble(babySize.getText().toString());
        baby.setName(babyName.getText().toString());
        //baby.setDateOfBirth((String) dateOfBirth.getText());
        //baby.setWeight(weight);
        //baby.setSize(size);
        //baby.setHairColor((String) babyHairColor.getText());
        //baby.setBirthLocation((String) placeOfBirth.getText());

        global.selectedBaby = baby; //set the baby to global (later save to db)
        startActivity(new Intent(getBaseContext(), BabyHomeActivity.class));
    }
}