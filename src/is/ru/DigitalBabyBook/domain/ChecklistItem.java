package is.ru.DigitalBabyBook.domain;

/**
 * Created by arnif on 11/7/14.
 */
public class ChecklistItem {

    private int id;
    private String description;
    private String type;
    private boolean done;

    public ChecklistItem() {

    }

    public ChecklistItem(String description, String type, boolean done) {
        this.description = description;
        this.type = type;
        this.done = done;
    }

    public ChecklistItem(int id, String description, String type, boolean done) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
