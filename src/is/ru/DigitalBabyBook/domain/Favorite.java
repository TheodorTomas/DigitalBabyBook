package is.ru.DigitalBabyBook.domain;

/**
 * Created by Tommz on 19.10.2014.
 */
public class Favorite {

    private String type;

    public Favorite(String type, String date, String photo) {
        this.type = type;
        Date = date;
        this.photo = photo;
    }

    private String Date;
    private String photo;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


}
