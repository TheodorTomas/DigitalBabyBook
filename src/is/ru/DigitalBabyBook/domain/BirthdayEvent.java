package is.ru.DigitalBabyBook.domain;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by arnif on 10/14/14.
 */
public class BirthdayEvent extends Event { //and christmas ??

    public String gifts;
    public Baby baby;

    //event description example (baby.name just had his/hers first birthday at age 3month and got List of gifts)
    public BirthdayEvent(String eventDescription, String date, String location, String photos, String gifts, Baby baby) {
        super(eventDescription, date, location, photos, baby);
        this.gifts = gifts;
    }

    public String getGifts() {
        return gifts;
    }

    public void setGifts(String gifts) {
        this.gifts = gifts;
    }
}
