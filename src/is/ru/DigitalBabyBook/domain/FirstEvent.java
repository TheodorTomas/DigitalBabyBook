package is.ru.DigitalBabyBook.domain;

/**
 * Created by Tommz on 20.10.2014.
 */
public class FirstEvent extends Event {

    public String witness;
    public int firstID;

    public FirstEvent() {
    }

    public FirstEvent(String type, String eventDescription, String date, String location, String photos,   String notes, String witness, Baby baby) {
        super(type, eventDescription, date, location, photos, notes, baby);
        this.witness = witness;
    }


    public String getWitness() {
        return witness;
    }

    public void setWitness(String witness) {
        this.witness = witness;
    }

    public int getFirstID() {
        return firstID;
    }

    public void setFirstID(int firstID) {
        this.firstID = firstID;
    }
}
