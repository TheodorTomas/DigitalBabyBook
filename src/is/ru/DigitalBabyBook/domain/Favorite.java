package is.ru.DigitalBabyBook.domain;

/**
 * Created by Tommz on 20.10.2014.
 */
public class Favorite {


    public Favorite(String type, String date, String photo) {
        this.type = type;
        this.date = date;
        this.photo = photo;
    }

    private String type;
    private String date;
    private String photo;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


}
