package is.ru.DigitalBabyBook.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import is.ru.DigitalBabyBook.DbHelper;

/**
 * Created by arnif on 11/1/14.
 */
public class EventAdapter {

    SQLiteDatabase db;
    DbHelper dbHelper;
    Context context;

    public EventAdapter(Context c) {
        context = c;
    }

    public EventAdapter openToRead() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public EventAdapter openToWrite() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public long insertEvent(int babyID, String description, String date, String photo, String type) {
        //"babyID", "description", "date", "photo", "type"

        String[] cols = DbHelper.TableEventCols;   //"babyID  1", "description  2", "date  3", "photo  4", "type 5"
        ContentValues contentValues = new ContentValues();

        contentValues.put(cols[1], babyID);
        contentValues.put(cols[2], description);
        contentValues.put(cols[3], date);
        contentValues.put(cols[4], photo);
        contentValues.put(cols[5], type);

        openToWrite();
        long value = db.insert(DbHelper.EventTable, null, contentValues);
        close();
        return value;
    }


    public Cursor queryEvent() {
        openToRead();
        Cursor cursor = db.query(DbHelper.EventTable,
                DbHelper.TableEventCols, null, null, null, null, null);
        return cursor;
    }

    public Cursor queryEvent(long eventID) {
        openToRead();
        String[] cols = DbHelper.TableEventCols;
        Cursor cursor = db.query(DbHelper.EventTable,
                cols, cols[0] + "=" + eventID, null, null, null, null);
        return cursor;
    }

    public Cursor queryEventByBabyID(long babyID) {
        openToRead();
        String[] cols = DbHelper.TableEventCols;
        Cursor cursor = db.query(DbHelper.EventTable,
                cols, cols[1] + "=" + babyID, null, null, null, null);
        return cursor;
    }

    public void deleteAll() {
        Cursor cursor = queryEvent();
        long rowId = cursor.getColumnIndexOrThrow("_id");

        if (cursor.moveToFirst()) {
            do {
                deleteRow(cursor.getLong((int) rowId));
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    public boolean deleteRow(long rowId) {
        return db.delete(DbHelper.EventTable, rowId + "= _id", null) != 0;
    }

}
