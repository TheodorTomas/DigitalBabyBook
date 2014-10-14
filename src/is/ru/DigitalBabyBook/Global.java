package is.ru.DigitalBabyBook;

import is.ru.DigitalBabyBook.domain.Baby;

/**
 * Created by arnif on 10/14/14.
 */
public class Global {

    public Baby baby;

    private static Global mInstance = new Global();

    public static Global getInstance() {
        return mInstance;
    }
}
