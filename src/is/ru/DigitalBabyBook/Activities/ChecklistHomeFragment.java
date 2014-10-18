package is.ru.DigitalBabyBook.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import is.ru.DigitalBabyBook.R;

/**
 * Created by arnif on 10/18/14.
 */
public class ChecklistHomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.checklist_home_tab, container, false);

        return V;
    }
}
