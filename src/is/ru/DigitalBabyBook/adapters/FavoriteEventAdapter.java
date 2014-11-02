package is.ru.DigitalBabyBook.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import is.ru.DigitalBabyBook.DbHelper;

/**
 * Created by Tommz on 1.11.2014.
 */
public class FavoriteEventAdapter {
    SQLiteDatabase db;
    DbHelper dbHelper;
    Context context;

    public FavoriteEventAdapter(Context c) {
        context = c;
    }

    public FavoriteEventAdapter openToRead() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public FavoriteEventAdapter openToWrite() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public long insertFavorite(int babyID, long eventID, String description, String date, String photo, String notes) {

        String[] cols = DbHelper.TableFavoriteEventCols; //  "babyID  1", "eventID  2", "description 3", "date 4", "photo 4", "notes 5"
        ContentValues contentValues = new ContentValues();

        contentValues.put(cols[1], babyID);
        contentValues.put(cols[2], eventID);
        contentValues.put(cols[3], description);
        contentValues.put(cols[4], date);
        contentValues.put(cols[5], photo);
        contentValues.put(cols[6], notes);

        openToWrite();
        long value = db.insert(DbHelper.FavoriteEventTable, null, contentValues);
        close();
        return value;
    }
    public long updateFavorite(int favoriteId, int babyID, long eventID, String description, String date,  String photo , String notes) {
        String[] cols = DbHelper.TableFavoriteEventCols; //  "babyID  1", "eventID  2", "description 3", "date  4",  "photo 5", , "notes  6"
        ContentValues contentValues = new ContentValues();

        contentValues.put(cols[1], babyID);
        contentValues.put(cols[2], eventID);
        contentValues.put(cols[3], description);
        contentValues.put(cols[4], date);
        contentValues.put(cols[5], photo);
        contentValues.put(cols[6], notes);

        openToWrite();
        long value = db.update(DbHelper.FavoriteEventTable, contentValues,
                cols[0] + "=" + favoriteId, null);
        close();
        return value;
    }
    public Cursor queryFavorite() {
        openToRead();
        Cursor cursor = db.query(DbHelper.FavoriteEventTable,
                DbHelper.TableFavoriteEventCols, null, null, null, null, null);
        return cursor;
    }

    public Cursor queryFavorite(long babyId) {
        openToRead();
        String[] cols = DbHelper.TableFavoriteEventCols;
        Cursor cursor = db.query(DbHelper.FavoriteEventTable,
                cols, cols[1] + "=" + babyId, null, null, null, null);
        return cursor;
    }
    public Cursor queryFavoriteById(long id) {
        openToRead();
        String[] cols = DbHelper.TableFavoriteEventCols;
        Cursor cursor = db.query(DbHelper.FavoriteEventTable,
                cols, cols[0] + "=" + id, null, null, null, null);
        return cursor;
    }
    public Cursor queryFavoriteByEventId(int eventID) {
        openToRead();
        String[] cols = DbHelper.TableFavoriteEventCols;
        Cursor cursor = db.query(DbHelper.FavoriteEventTable,
                cols, cols[2] + "=" + eventID, null, null, null, null);
        return cursor;
    }
    public void deleteAll() {
        Cursor cursor = queryFavorite();
        long rowId = cursor.getColumnIndexOrThrow("_id");

        if (cursor.moveToFirst()) {
            do {
                deleteRow(cursor.getLong((int) rowId));
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    public boolean deleteRow(long rowId) {
        return db.delete(DbHelper.FavoriteEventTable, rowId + "= _id", null) != 0;
    }
}
