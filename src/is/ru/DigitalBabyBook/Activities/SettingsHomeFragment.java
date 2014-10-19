package is.ru.DigitalBabyBook.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import is.ru.DigitalBabyBook.R;

/**
 * Created by sindrisigurjonsson on 19/10/14.
 */
public class SettingsHomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.settings_home_tab, container, false);

        return V;
    }
}