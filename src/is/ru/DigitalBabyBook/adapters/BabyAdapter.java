package is.ru.DigitalBabyBook.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import is.ru.DigitalBabyBook.DbHelper;

/**
 * Created by arnif on 10/15/14.
 */
public class BabyAdapter {

    SQLiteDatabase db;
    DbHelper dbHelper;
    Context context;

    public BabyAdapter(Context c) {
        context = c;
    }

    public BabyAdapter openToRead() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public BabyAdapter openToWrite() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public long insertBaby(String name, String dateOfBirth, String birthLocation, String gender, double size, double weight, String hairColor, String profilePicture ) {

        String[] cols = DbHelper.TableBabyCols; //"name 1", "dateOfBirth 2", "birthLocation 3", "gender 4", "size 5", "weight 6", "hairColor 7" , "profilePicture 8"
        ContentValues contentValues = new ContentValues();

        contentValues.put(cols[1], name);
        contentValues.put(cols[2], dateOfBirth);
        contentValues.put(cols[3], birthLocation);
        contentValues.put(cols[4], gender);
        contentValues.put(cols[5], size);
        contentValues.put(cols[6], weight);
        contentValues.put(cols[7], hairColor);
        contentValues.put(cols[8], profilePicture);

        openToWrite();
        long value = db.insert(DbHelper.BabyTable, null, contentValues);
        close();
        return value;
    }

    public Cursor queryBaby() {
        openToRead();
        Cursor cursor = db.query(DbHelper.BabyTable,
                DbHelper.TableBabyCols, null, null, null, null, null);
        return cursor;
    }

    public Cursor queryBaby(long babyId) {
        openToRead();
        String[] cols = DbHelper.TableBabyCols;
        Cursor cursor = db.query(DbHelper.BabyTable,
                cols, cols[0] + "=" + babyId, null, null, null, null);
        return cursor;
    }

    public void deleteAll() {
        Cursor cursor = queryBaby();
        long rowId = cursor.getColumnIndexOrThrow("_id");

        if (cursor.moveToFirst()) {
            do {
                deleteRow(cursor.getLong((int) rowId));
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    public boolean deleteRow(long rowId) {
        return db.delete(DbHelper.BabyTable, rowId + "= _id", null) != 0;
    }

}
