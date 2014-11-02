package is.ru.DigitalBabyBook.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import is.ru.DigitalBabyBook.DbHelper;

/**
 * Created by Tommz on 1.11.2014.
 */
public class FirstEventAdapter {


    SQLiteDatabase db;
    DbHelper dbHelper;
    Context context;

    public FirstEventAdapter(Context c) {
        context = c;
    }

    public FirstEventAdapter openToRead() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public FirstEventAdapter openToWrite() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public long insertFirst(int babyID, long eventID, String description, String date, String location, String photo,String notes, String witness) {

        String[] cols = DbHelper.TableFirstEventCols; //  "babyID  1", "eventID  2", "description 3", "date  4", "location 5", "photo 6","notes 7" "witness 8"
        ContentValues contentValues = new ContentValues();

        contentValues.put(cols[1], babyID);
        contentValues.put(cols[2], eventID);
        contentValues.put(cols[3], description);
        contentValues.put(cols[4], date);
        contentValues.put(cols[5], location);
        contentValues.put(cols[6], photo);
        contentValues.put(cols[7], notes);
        contentValues.put(cols[8], witness);

        openToWrite();
        long value = db.insert(DbHelper.FirstEventTable, null, contentValues);
        close();
        return value;
    }
    public long updateFirst(int firstId, int babyID, long eventID, String description, String date, String location, String photo,String notes, String witness ) {
        String[] cols = DbHelper.TableFirstEventCols; //  "babyID  1", "eventID  2", "description 3", "date  4", "location 5", "photo 6", "notes 7", "witness  8"
        ContentValues contentValues = new ContentValues();

        contentValues.put(cols[1], babyID);
        contentValues.put(cols[2], eventID);
        contentValues.put(cols[3], description);
        contentValues.put(cols[4], date);
        contentValues.put(cols[5], location);
        contentValues.put(cols[6], photo);
        contentValues.put(cols[7], notes);
        contentValues.put(cols[8], witness);

        openToWrite();
        long value = db.update(DbHelper.FirstEventTable, contentValues,
                cols[0] + "=" + firstId, null);
        close();
        return value;
    }
    public Cursor queryFirst() {
        openToRead();
        Cursor cursor = db.query(DbHelper.FirstEventTable,
                DbHelper.TableFirstEventCols, null, null, null, null, null);
        return cursor;
    }

    public Cursor queryFirst(long babyId) {
        openToRead();
        String[] cols = DbHelper.TableFirstEventCols;
        Cursor cursor = db.query(DbHelper.FirstEventTable,
                cols, cols[1] + "=" + babyId, null, null, null, null);
        return cursor;
    }
    public Cursor queryFirstByBabyID(long babyID) {
        openToRead();
        String[] cols = DbHelper.TableFirstEventCols;
        Cursor cursor = db.query(DbHelper.FirstEventTable,
                cols, cols[1] + "=" + babyID, null, null, null, null);
        return cursor;
    }
    public Cursor queryFirstById(long id) {
        openToRead();
        String[] cols = DbHelper.TableFirstEventCols;
        Cursor cursor = db.query(DbHelper.FirstEventTable,
                cols, cols[0] + "=" + id, null, null, null, null);
        return cursor;
    }
    public Cursor queryFirstByEventId(int eventID) {
        openToRead();
        String[] cols = DbHelper.TableFirstEventCols;
        Cursor cursor = db.query(DbHelper.FirstEventTable,
                cols, cols[2] + "=" + eventID, null, null, null, null);
        return cursor;
    }
    public void deleteAll() {
        Cursor cursor = queryFirst();
        long rowId = cursor.getColumnIndexOrThrow("_id");

        if (cursor.moveToFirst()) {
            do {
                deleteRow(cursor.getLong((int) rowId));
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    public boolean deleteRow(long rowId) {
        return db.delete(DbHelper.FirstEventTable, rowId + "= _id", null) != 0;
    }

}
