package is.ru.DigitalBabyBook.domain;

/**
 * Created by arnif on 10/14/14.
 */
public class Baby {

    private int id;
    private String name;
    private String dateOfBirth;
    private String birthLocation;
    private String gender;
    private double size;
    private double weight;
    private String hairColor;
    private String profilePicture;

    public Baby() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthLocation() {
        return birthLocation;
    }

    public void setBirthLocation(String birthLocation) {
        this.birthLocation = birthLocation;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Baby(String name, String dateOfBirth, String birthLocation, String gender, double size, double weight, String hairColor, String profilePicture) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.birthLocation = birthLocation;
        this.gender = gender;
        this.size = size;
        this.weight = weight;
        this.hairColor = hairColor;
        this.profilePicture = profilePicture;
    }


}
