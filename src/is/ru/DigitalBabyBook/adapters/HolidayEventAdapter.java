package is.ru.DigitalBabyBook.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import is.ru.DigitalBabyBook.DbHelper;

/**
 * Created by arnif on 10/19/14.
 */
public class HolidayEventAdapter {

    SQLiteDatabase db;
    DbHelper dbHelper;
    Context context;

    public HolidayEventAdapter(Context c) {
        context = c;
    }

    public HolidayEventAdapter openToRead() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public HolidayEventAdapter openToWrite() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public long insertHoliday(int babyID, long eventID, String description, String date, String location, String photo, String gifts, String notes ) {

        String[] cols = DbHelper.TableHolidayEventCols; //  "babyID  1", "eventID  2", "description 3", "date  4", "location 5", "photo 6", "gifts 7", "notes  8"
        ContentValues contentValues = new ContentValues();

        contentValues.put(cols[1], babyID);
        contentValues.put(cols[2], eventID);
        contentValues.put(cols[3], description);
        contentValues.put(cols[4], date);
        contentValues.put(cols[5], location);
        contentValues.put(cols[6], photo);
        contentValues.put(cols[7], gifts);
        contentValues.put(cols[8], notes);

        openToWrite();
        long value = db.insert(DbHelper.HolidayEventTable, null, contentValues);
        close();
        return value;
    }

    public long updateHoliday(int holidayId, int babyID, long eventID, String description, String date, String location, String photo, String gifts, String notes ) {
        String[] cols = DbHelper.TableHolidayEventCols; //  "babyID  1", "eventID  2", "description 3", "date  4", "location 5", "photo 6", "gifts 7", "notes  8"
        ContentValues contentValues = new ContentValues();

        contentValues.put(cols[1], babyID);
        contentValues.put(cols[2], eventID);
        contentValues.put(cols[3], description);
        contentValues.put(cols[4], date);
        contentValues.put(cols[5], location);
        contentValues.put(cols[6], photo);
        contentValues.put(cols[7], gifts);
        contentValues.put(cols[8], notes);

        openToWrite();
        long value = db.update(DbHelper.HolidayEventTable, contentValues,
                cols[0] + "=" + holidayId, null);
        close();
        return value;
    }

    public Cursor queryHoliday() {
        openToRead();
        Cursor cursor = db.query(DbHelper.HolidayEventTable,
                DbHelper.TableHolidayEventCols, null, null, null, null, null);
        return cursor;
    }

    public Cursor queryHolidayByBabyId(long babyId) {
        openToRead();
        String[] cols = DbHelper.TableHolidayEventCols;
        Cursor cursor = db.query(DbHelper.HolidayEventTable,
                cols, cols[1] + "=" + babyId, null, null, null, null);
        return cursor;
    }

    public Cursor queryHolidayById(long id) {
        openToRead();
        String[] cols = DbHelper.TableHolidayEventCols;
        Cursor cursor = db.query(DbHelper.HolidayEventTable,
                cols, cols[0] + "=" + id, null, null, null, null);
        return cursor;
    }

    public Cursor queryHolidayByEventId(int eventID) {
        openToRead();
        String[] cols = DbHelper.TableHolidayEventCols;
        Cursor cursor = db.query(DbHelper.HolidayEventTable,
                cols, cols[2] + "=" + eventID, null, null, null, null);
        return cursor;
    }

    public void deleteAll() {
        Cursor cursor = queryHoliday();
        long rowId = cursor.getColumnIndexOrThrow("_id");

        if (cursor.moveToFirst()) {
            do {
                deleteRow(cursor.getLong((int) rowId));
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    public boolean deleteRow(long rowId) {
        return db.delete(DbHelper.HolidayEventTable, rowId + "= _id", null) != 0;
    }

}
