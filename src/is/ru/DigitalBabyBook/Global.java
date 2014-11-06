package is.ru.DigitalBabyBook;

import is.ru.DigitalBabyBook.domain.Baby;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by arnif on 10/14/14.
 */
public class Global {

    public Baby selectedBaby;

    private static Global mInstance = new Global();

    public static Global getInstance() {
        return mInstance;
    }

    public String calculateAge(String dateOfBirth, String from)  {
        SimpleDateFormat dateFormat = new SimpleDateFormat("M-d-yyyy", Locale.ENGLISH);


        Date date = null;
        Date now1 = null;
        try {
            date = dateFormat.parse(dateOfBirth);
            if (from != null) {
                now1 = dateFormat.parse(from);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LocalDate birthdate = new LocalDate (date);
        LocalDate now;
        if (from != null) {
            now = new LocalDate(now1);
        } else {
            now = new LocalDate();
        }
        Period periodWeek = new Period(birthdate, now, PeriodType.yearWeekDay());
        Period periodMonth = new Period(birthdate, now, PeriodType.yearMonthDay());

        String day = isPlural(periodMonth.getDays()) ? " day" : " days";
        String month = isPlural(periodMonth.getMonths()) ? " month" : " months";
        String week = isPlural(periodWeek.getWeeks()) ? " week" : " weeks";
        String year = isPlural(periodMonth.getYears()) ? " year" : " years";

        if (periodMonth.getYears() > 1) {
            return periodMonth.getYears() +  year + " and " + periodMonth.getMonths() + month;
        }
        if (periodMonth.getMonths() < 3) {
            if (periodMonth.getMonths() == 0) {
                return periodMonth.getDays() + day;
            }
            day = isPlural(Math.abs(periodWeek.getDays())) ? " day" : " days";
            return periodWeek.getWeeks() + week + " and " + Math.abs(periodWeek.getDays()) + day;
        }
        week = isPlural(periodWeek.getWeeks() % periodMonth.getMonths()) ? " week" : " weeks";
        return periodMonth.getMonths()  + month + " and " + periodWeek.getWeeks() % periodMonth.getMonths() + week;
    }


    public boolean validateDateInput(String dateOfBirth, String selectedDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("M-d-yyyy", Locale.ENGLISH);

        Date date = null;
        Date now1 = null;
        try {
            date = dateFormat.parse(dateOfBirth);
            if (selectedDate != null) {
                now1 = dateFormat.parse(selectedDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LocalDate birthdate = new LocalDate (date);
        LocalDate now;
        if (selectedDate != null) {
            now = new LocalDate(now1);
        } else {
            now = new LocalDate();
        }
        if (birthdate.isBefore(now)) {
            return true;
        }
        return false;
    }

    private boolean isPlural(int number) {
        return number <= 1;
    }
}
