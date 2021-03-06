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
    public static final String[] TableBabyCols = {"_id", "name", "dateOfBirth", "birthLocation", "gender", "size", "weight", "hairColor", "profilePicture"};

    public static final String HolidayEventTable = "holidayEvents";
    public static final String[] TableHolidayEventCols = {"_id", "babyID", "eventID", "description", "date", "location", "photo", "gifts", "notes" };

    public static final String FirstEventTable = "firstEvents";
    public static final String[] TableFirstEventCols = {"_id", "babyID", "eventID", "description", "date", "location", "photo",  "notes", "witness"};

    public static final String FavoriteEventTable = "favoriteEvents";
    public static final String[] TableFavoriteEventCols = {"_id", "babyID", "eventID", "description", "date", "photo", "notes", "name"};

    public static final String EventTable = "events";
    public static final String[] TableEventCols = { "_id", "babyID", "description", "date", "photo", "type" };

    public static final String ChecklistTable = "checklists";
    public static final String[] TableChecklistCols = { "_id", "babyID", "description", "date", "type", "done" };

    private static final String sqlCreateTableEvent = "CREATE TABLE events(" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " babyID INT NOT NULL, " +
            " description TEXT NOT NULL, " +
            " date DATE NOT NULL, " +
            " photo TEXT NULL, " +
            " type TEXT NULL, " +
            " FOREIGN KEY(babyID) REFERENCES babies(_id) " +
            ");";

    private static final String sqlCreateTableBabies = "CREATE TABLE babies(" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " name TEXT NOT NULL," +
            " dateOfBirth DATE NOT NULL," +
            " birthLocation TEXT, " +
            " gender TEXT NOT NULL, " +
            " size DOUBLE NULL, " +
            " weight DOUBLE NULL, " +
            " hairColor TEXT NULL, " +
            " profilePicture TEXT NULL " +
            ");";

    private static final String sqlCreateTableHolidayEvents = "CREATE TABLE holidayEvents(" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " babyID INTEGER NOT NULL," +
            " eventID INTEGER NOT NULL, " +
            " description TEXT NOT NULL, " +
            " date DATE NOT NULL," +
            " location TEXT NULL, " +
            " photo TEXT NULL, " +
            " gifts TEXT NULL, " +
            " notes TEXT NULL, " +
            " FOREIGN KEY(babyID) REFERENCES babies(_id), " +
            " FOREIGN KEY(eventID) REFERENCES events(_id) " +
            ");";

    private static final String sqlCreateTableFirstEvents = "CREATE TABLE firstEvents(" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " babyID INTEGER NOT NULL," +
            " eventID INTEGER NOT NULL, " +
            " description TEXT NOT NULL, " +
            " date DATE NOT NULL," +
            " location TEXT NULL, " +
            " photo TEXT NULL, " +
            " notes TEXT NULL, " +
            " witness TEXT NULL, " +
            " FOREIGN KEY(babyID) REFERENCES babies(_id), " +
            " FOREIGN KEY(eventID) REFERENCES events(_id) " +
            ");";

    private static final String sqlCreateTableFavoriteEvents = "CREATE TABLE favoriteEvents(" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " babyID INTEGER NOT NULL," +
            " eventID INTEGER NOT NULL, " +
            " description TEXT NOT NULL, " +
            " date DATE NOT NULL," +
            " photo TEXT NULL, " +
            " notes TEXT NULL, " +
            " name TEXT NULL, " +
            " FOREIGN KEY(babyID) REFERENCES babies(_id), " +
            " FOREIGN KEY(eventID) REFERENCES events(_id) " +
            ");";

    public static final String sqlCreateTableChecklist = "CREATE TABLE checklists(" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " babyID INT NOT NULL, " +
            " description TEXT NOT NULL, " +
            " date DATE NULL, " +
            " type TEXT NOT NULL, " +
            " done boolean DEFAULT FALSE, " +
            " FOREIGN KEY(babyID) REFERENCES babies(_id) " +
            ");";

    private static final String sqlDropTableEvents =
            "DROP TABLE IF EXISTS events";

    private static final String sqlDropTableBabies =
            "DROP TABLE IF EXISTS babies";

    private static final String sqlDropTableHolidayEvents =
            "DROP TABLE IF EXISTS holidayEvents";

    private static final String sqlDropTableFirstEvents =
            "DROP TABLE IF EXISTS firstEvents";

    private static final String sqlDropTableFavoriteEvents =
            "DROP TABLE IF EXISTS favoriteEvents";

    public static final String sqlDropTableChecklists =
            "DROP TABLE IF EXISTS checklists";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateTableBabies);
        db.execSQL(sqlCreateTableEvent);
        db.execSQL(sqlCreateTableHolidayEvents);
        db.execSQL(sqlCreateTableFirstEvents);
        db.execSQL(sqlCreateTableFavoriteEvents);
        db.execSQL(sqlCreateTableChecklist);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlDropTableHolidayEvents);
        db.execSQL(sqlDropTableFirstEvents);
        db.execSQL(sqlDropTableFavoriteEvents);
        db.execSQL(sqlDropTableEvents);
        db.execSQL(sqlDropTableChecklists);
        db.execSQL(sqlDropTableBabies);
        onCreate(db);
    }
}
