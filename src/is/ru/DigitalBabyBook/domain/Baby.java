package is.ru.DigitalBabyBook.domain;

/**
 * Created by arnif on 10/14/14.
 */
public class Baby {

    public int id;
    public String name;
    public String dateOfBirth;
    public String birthLocation;
    public String gender;
    public double size;
    public double weight;
    public String hairColor;



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

    public Baby(String name, String dateOfBirth, String birthLocation, String gender, double size, double weight, String hairColor) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.birthLocation = birthLocation;
        this.gender = gender;
        this.size = size;
        this.weight = weight;
        this.hairColor = hairColor;
    }


}
