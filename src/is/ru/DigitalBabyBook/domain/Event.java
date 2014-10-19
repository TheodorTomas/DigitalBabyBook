package is.ru.DigitalBabyBook.domain;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by arnif on 10/14/14.
 */
public class Event {

    public String eventDescription;
    public /*Date*/String date;
    public String location;
    public /*ArrayList<Byte>*/String photos;
    public Baby baby;

    public Event(String eventDescription, /*Date*/String date, String location, /*ArrayList<Byte>*/ String photos, Baby baby) {
        this.eventDescription = eventDescription;
        this.date = date;
        this.location = location;
        this.photos = photos;
        this.baby = baby;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public /*Date */String getDate() {
        return date;
    }

    public void setDate(/*Date*/String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public /*ArrayList<Byte>*/String getPhotos() {
        return photos;
    }

    public void setPhotos(/*ArrayList<Byte>*/String photos) {
        this.photos = photos;
    }


}
