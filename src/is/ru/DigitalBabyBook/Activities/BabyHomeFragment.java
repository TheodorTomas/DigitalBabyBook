package is.ru.DigitalBabyBook.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import is.ru.DigitalBabyBook.Global;
import is.ru.DigitalBabyBook.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

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
            age = calculateAge(global.selectedBaby.getDateOfBirth());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TextView babyAge = (TextView) V.findViewById(R.id.home_babyAge);
        babyAge.setText(age);


        return V;
    }

    private String calculateAge(String dateOfBirth) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("M-d-yyyy", Locale.ENGLISH);
        Calendar cal = Calendar.getInstance();

        System.out.println(dateFormat.format(cal.getTime()));

        Date date = dateFormat.parse(dateOfBirth);

        System.out.println(dateFormat.format(date.getTime()));

        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(date);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(cal.getTime());

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

        System.out.println(diffYear);
        System.out.println(diffMonth);

        final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;

        int diffInDays = (int) ((endCalendar.get(Calendar.DAY_OF_YEAR) - startCalendar.get(Calendar.DAY_OF_YEAR))/ DAY_IN_MILLIS );

        System.out.println(diffInDays);

        return "3month";
    }


}
