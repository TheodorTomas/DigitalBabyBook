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
        String returnMe = "";
        //Checks if the baby isn't born yet and changes the return string accordingly
        if(birthdate.isAfter(now)){
            returnMe = "Expected in ";
        }
        Period periodWeek = new Period(birthdate, now, PeriodType.yearWeekDay());
        Period periodMonth = new Period(birthdate, now, PeriodType.yearMonthDay());

        String day = isPlural(Math.abs(periodMonth.getDays())) ? " day" : " days";
        String month = isPlural(Math.abs(periodMonth.getMonths())) ? " month" : " months";
        String week = isPlural(Math.abs(periodWeek.getWeeks())) ? " week" : " weeks";
        String year = isPlural(Math.abs(periodMonth.getYears())) ? " year" : " years";

        if (Math.abs(periodMonth.getYears()) >= 1) {
            if(Math.abs(periodMonth.getMonths()) != 0) {
                return returnMe + Math.abs(periodMonth.getYears()) + year + " and " + Math.abs(periodMonth.getMonths()) + month;
            }
            else{
                return returnMe + Math.abs(periodMonth.getYears()) + year;
            }
        }
        if (Math.abs(periodMonth.getMonths()) < 3) {
            if (Math.abs(periodMonth.getMonths()) == 0) {
                return returnMe + Math.abs(periodMonth.getDays()) + day;
            }
            day = isPlural(Math.abs(periodWeek.getDays())) ? " day" : " days";
            return returnMe + Math.abs(periodWeek.getWeeks()) + week + " and " + Math.abs(periodWeek.getDays()) + day;
        }
        week = isPlural(Math.abs(periodWeek.getWeeks()) % Math.abs(periodMonth.getMonths())) ? " week" : " weeks";
        return returnMe +Math.abs(periodMonth.getMonths())  + month + " and " + Math.abs(periodWeek.getWeeks()) % Math.abs(periodMonth.getMonths()) + week;
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
        return number <= 1 || number == 0;
    }
}
