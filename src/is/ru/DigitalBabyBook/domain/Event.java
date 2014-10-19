package is.ru.DigitalBabyBook.domain;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by arnif on 10/14/14.
 */
public class Event {

    public String eventDescription;
    public String date;
    public String location;
    public String photos;
    public Baby baby;

    public Event(String eventDescription, String date, String location, String photos, Baby baby) {
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

    public String  getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }


}
