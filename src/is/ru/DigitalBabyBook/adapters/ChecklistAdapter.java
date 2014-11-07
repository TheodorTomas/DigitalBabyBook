package is.ru.DigitalBabyBook.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import is.ru.DigitalBabyBook.DbHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by arnif on 11/7/14.
 */
public class ChecklistAdapter {

    SQLiteDatabase db;
    DbHelper dbHelper;
    Context context;

    public ChecklistAdapter(Context c) {
        context = c;
    }

    public ChecklistAdapter openToRead() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public ChecklistAdapter openToWrite() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public long insertChecklist(long babyId, String description, String type) {
        String[] cols = DbHelper.TableChecklistCols; // { "_id  0", "babyID   1", "description   2", "date   3", "type   4", "done   5"  };
        ContentValues contentValues = new ContentValues();

        contentValues.put(cols[1], babyId);
        contentValues.put(cols[2], description);
        contentValues.put(cols[4], type);

        openToWrite();
        long value = db.insert(DbHelper.ChecklistTable, null, contentValues);
        close();
        return value;
    }

    public long updateChecklist(int id, boolean done) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());

        String[] cols = DbHelper.TableChecklistCols;  // { "_id  0", "babyID   1", "description   2", "date   3", "type   4", "done   5"  };
        ContentValues contentValues = new ContentValues();

        contentValues.put(cols[3], date);
        contentValues.put(cols[5], done);

        openToWrite();
        long value = db.update(DbHelper.ChecklistTable, contentValues,
                cols[0] + "=" + id, null);
        close();
        return value;

    }

    public Cursor queryChecklist() {
        openToRead();
        Cursor cursor = db.query(DbHelper.ChecklistTable,
                DbHelper.TableChecklistCols, null, null, null, null, null);
        return cursor;
    }

    public Cursor queryChecklistByType(String type) {
        openToRead();
        String[] cols = DbHelper.TableChecklistCols;
        Cursor cursor = db.query(DbHelper.ChecklistTable,
                cols, cols[4] + "='" + type + "'", null, null, null, null);
        return cursor;
    }

    public void deleteAll() {
        Cursor cursor = queryChecklist();
        long rowId = cursor.getColumnIndexOrThrow("_id");

        if (cursor.moveToFirst()) {
            do {
                deleteRow(cursor.getLong((int) rowId));
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    public boolean deleteRow(long rowId) {
        return db.delete(DbHelper.ChecklistTable, rowId + "= _id", null) != 0;
    }

    public void recreate() {
        openToRead();
        db.execSQL(DbHelper.sqlDropTableChecklists);
        db.execSQL(DbHelper.sqlCreateTableChecklist);
    }

}
