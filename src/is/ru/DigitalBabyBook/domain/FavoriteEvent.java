package is.ru.DigitalBabyBook.domain;

/**
 * Created by Tommz on 20.10.2014.
 */
public class FavoriteEvent extends Event {


    public int favoriteID;
    private String type;
    private String date;
    private String photo;

    public FavoriteEvent() {
    }

    ;

    public FavoriteEvent(String type, String eventDescription, String date, String location, String photos, String notes, Baby baby) {
        super(type, eventDescription, date, location, photos, notes, baby);
    }



    public int getFavoriteID() {
        return favoriteID;
    }

    public void setFavoriteID(int favoriteID) {
        this.favoriteID = favoriteID;
    }
}
