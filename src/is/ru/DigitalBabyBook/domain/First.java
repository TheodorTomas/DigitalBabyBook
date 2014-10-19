package is.ru.DigitalBabyBook.domain;

/**
 * Created by Tommz on 19.10.2014.
 */
public class First {

    private String type;

    public First(String photo, String type, String date, String location, String witness) {
        this.photo = photo;
        this.type = type;
        this.date = date;
        this.location = location;
        this.witness = witness;
    }

    private String date;
    private String location;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
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

    public String getWitness() {
        return witness;
    }

    public void setWitness(String witness) {
        this.witness = witness;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    private String witness;
    private String photo;
}
