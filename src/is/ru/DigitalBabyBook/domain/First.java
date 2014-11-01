package is.ru.DigitalBabyBook.domain;

/**
 * Created by Tommz on 20.10.2014.
 */
public class First {

    private String type;
    private String Date;
    private String location;
    private String witness;
    private String photos;

    public First(String type, String date, String location, String witness, String photos) {
        this.type = type;
        Date = date;
        this.location = location;
        this.witness = witness;
        this.photos = photos;
    }





    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
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
        return photos;
    }

    public void setPhoto(String photo) {
        this.photos = photo;
    }

}
