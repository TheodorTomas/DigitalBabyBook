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

    public String calculateAge(String dateOfBirth) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("M-d-yyyy", Locale.ENGLISH);

        Date date = dateFormat.parse(dateOfBirth);

        LocalDate birthdate = new LocalDate (date);
        LocalDate now = new LocalDate();
        Period periodWeek = new Period(birthdate, now, PeriodType.yearWeekDay());
        Period periodMonth = new Period(birthdate, now, PeriodType.yearMonthDay());

        String day = isPlural(periodMonth.getDays()) ? " day" : " days";
        String month = isPlural(periodMonth.getMonths()) ? " month" : " months";
        String week = isPlural(periodWeek.getWeeks()) ? " week" : " weeks";
        String year = isPlural(periodMonth.getYears()) ? " year" : " years";

        if (periodMonth.getMonths() < 3) {
            if (periodMonth.getMonths() == 0) {
                return periodMonth.getDays() + day;
            }
            day = isPlural(periodWeek.getDays()) ? " day" : " days";
            return periodWeek.getWeeks() + week + " and " + periodWeek.getDays() + day;
        }

        if (periodMonth.getYears() > 1) {
            return periodMonth.getYears() +  year + " and " + periodMonth.getMonths() + month;
        }
        week = isPlural(periodWeek.getWeeks() % periodMonth.getMonths()) ? " week" : " weeks";
        return periodMonth.getMonths()  + month + " and " + periodWeek.getWeeks() % periodMonth.getMonths() + week;
    }

    private boolean isPlural(int number) {
        return number <= 1;
    }
}
