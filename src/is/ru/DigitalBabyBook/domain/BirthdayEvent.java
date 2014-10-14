package is.ru.DigitalBabyBook.domain;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by arnif on 10/14/14.
 */
public class BirthdayEvent extends Event { //and christmas ??

    public ArrayList<String> gifts;
    public Baby baby;

    //event description example (baby.name just had his/hers first birthday at age 3month and got List of gifts)
    public BirthdayEvent(String eventDescription, Date date, String location, ArrayList<Byte> photos, ArrayList<String> gifts, Baby baby) {
        super(eventDescription, date, location, photos, baby);
        this.gifts = gifts;
    }

    public ArrayList<String> getGifts() {
        return gifts;
    }

    public void setGifts(ArrayList<String> gifts) {
        this.gifts = gifts;
    }
}
