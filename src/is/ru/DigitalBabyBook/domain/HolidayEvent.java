package is.ru.DigitalBabyBook.domain;

/**
 * Created by arnif on 10/14/14.
 */
public class HolidayEvent extends Event { //and christmas ??

    public String gifts;
    public Baby baby;
    private int holiDayID;

    public HolidayEvent() {};

    //event description example (baby.name just had his/hers first birthday at age 3month and got List of gifts)
    public HolidayEvent(String type, String eventDescription, String date, String location, String photos, String gifts, String notes, Baby baby) {
        super(type, eventDescription, date, location, photos, notes, baby);
        this.gifts = gifts;
    }

    public String getGifts() {
        return gifts;
    }

    public void setGifts(String gifts) {
        this.gifts = gifts;
    }

    public void setHoliDayID(int holiDayID) {
        this.holiDayID = holiDayID;
    }

    public int getHoliDayID() {
        return holiDayID;
    }
}
