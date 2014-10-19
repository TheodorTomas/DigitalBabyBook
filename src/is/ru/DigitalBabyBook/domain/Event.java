package is.ru.DigitalBabyBook.domain;

/**
 * Created by arnif on 10/14/14.
 */
public class Event {

    private String type;
    private String eventDescription;
    private String date;
    private String location;
    private String photos;
    private String notes;
    private Baby baby;

    public Event() {};

    public Event(String type, String eventDescription, String date, String location, String photos, String notes, Baby baby) {
        this.type = type;
        this.eventDescription = eventDescription;
        this.date = date;
        this.location = location;
        this.photos = photos;
        this.notes = notes;
        this.baby = baby;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}
