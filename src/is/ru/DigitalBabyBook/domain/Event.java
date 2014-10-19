package is.ru.DigitalBabyBook.domain;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by arnif on 10/14/14.
 */
public class Event {

    public String eventDescription;
    public Date date;
    public String location;
    public ArrayList<Byte> photos;
    public Baby baby;

    public Event(String eventDescription, Date date, String location, ArrayList<Byte> photos, Baby baby) {
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

    public Date  getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Byte> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Byte> photos) {
        this.photos = photos;
    }


}
