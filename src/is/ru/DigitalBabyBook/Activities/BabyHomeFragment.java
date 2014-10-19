package is.ru.DigitalBabyBook.Activities;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import is.ru.DigitalBabyBook.Global;
import is.ru.DigitalBabyBook.R;

import java.text.ParseException;

/**
 * Created by arnif on 10/18/14.
 */
public class BabyHomeFragment extends Fragment {

    private static Global global = Global.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.baby_home_tab, container, false);

        TextView babyName = (TextView) V.findViewById(R.id.home_babyName);
        babyName.setText(global.selectedBaby.getName());

        String age = null;
        try {
            age = global.calculateAge(global.selectedBaby.getDateOfBirth());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (global.selectedBaby.getProfilePicture() != null) {
            ImageView profileImage = (ImageView) V.findViewById(R.id.home_babyProfilePicture);
            Bitmap d = new BitmapDrawable(V.getResources(), global.selectedBaby.getProfilePicture()).getBitmap();

            int nh = (int) (d.getHeight() * (256.0 / d.getWidth()));
            Bitmap scaled = Bitmap.createScaledBitmap(d, 256, nh, true);

            profileImage.setImageBitmap(scaled);
        }

        TextView babyAge = (TextView) V.findViewById(R.id.home_babyAge);
        babyAge.setText(age + " old");

        return V;
    }




}
