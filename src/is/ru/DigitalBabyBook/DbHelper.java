package is.ru.DigitalBabyBook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by arnif on 10/15/14.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "BABY_DB";
    public static final int DB_VERSION = 1;

    public static final String BabyTable = "babies";
    public static final String[] TableBabyCols = {"_id", "name", "dateOfBirth", "birthLocation", "gender", "size", "weight", "hairColor"};

    private static final String sqlCreateTableBabies = "CREATE TABLE babies(" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " name TEXT NOT NULL," +
            " dateOfBirth DATE NOT NULL," +
            " birthLocation TEXT, " +
            " gender TEXT NOT NULL, " +
            " size DOUBLE NULL, " +
            " weight DOUBLE NULL, " +
            " hairColor TEXT NULL " +
            ");";

    private static final String sqlDropTableBabies =
            "DROP TABLE IF EXISTS babies";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateTableBabies);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlDropTableBabies);
        onCreate(db);
    }
}
