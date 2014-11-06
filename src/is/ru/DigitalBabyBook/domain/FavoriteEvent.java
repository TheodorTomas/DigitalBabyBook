package is.ru.DigitalBabyBook.domain;

/**
 * Created by Tommz on 20.10.2014.
 */
public class FavoriteEvent extends Event {


    public int favoriteID;



    public String name;

    public FavoriteEvent() {
    }

    public FavoriteEvent(String type, String eventDescription, String date, String location, String photos, String notes,String name, Baby baby) {
        super(type, eventDescription, date, location, photos, notes, baby);
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFavoriteID() {
        return favoriteID;
    }

    public void setFavoriteID(int favoriteID) {
        this.favoriteID = favoriteID;
    }
}
