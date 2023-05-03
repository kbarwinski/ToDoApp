package pl.barwinski.todoapp.shared.dbhelpers;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

import pl.barwinski.todoapp.shared.contracts.TaskEntry;
import pl.barwinski.todoapp.shared.helpers.ContentValuesMapper;
import pl.barwinski.todoapp.shared.models.TaskModel;

public class TaskRepository {
    public SQLiteDatabase db;
    private final SQLiteOpenHelper dbHelper;

    private final String selection = TaskEntry._ID + "=?";

    private static String[] projection = ContentValuesMapper
            .getObjectFieldNames(new TaskModel());

    private static String[] selectionArgs = {"1"};
    public TaskRepository(Context context) {
        dbHelper = new TaskDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long create(ContentValues contentValues){
        Cursor cursor = db.query(
                TaskEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        int numEntries = cursor.getCount();
        contentValues.put(TaskEntry._ID, numEntries);

        return db.insert(TaskEntry.TABLE_NAME,null,contentValues);
    }

    @SuppressLint("Range")
    public ArrayList<TaskModel> read(){
        ArrayList<TaskModel> tasks = new ArrayList<>();
        Cursor cursor = db.query(
                TaskEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            TaskModel task = new TaskModel();
            task = ContentValuesMapper.mapCursorToObject(cursor, task.getClass());
            tasks.add(task);
        }

        return tasks;
    }

    public TaskModel read(long id){
        selectionArgs = new String[]{String.valueOf(id)};

        Cursor cursor = db.query(
                TaskEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            TaskModel task = new TaskModel();
            task = ContentValuesMapper.mapCursorToObject(cursor, task.getClass());
            return task;
        }

        return null;
    }

    public int update(ContentValues contentValues) {
        selectionArgs = new String[]{ String.valueOf(contentValues.get(TaskEntry._ID)) };

        return db.update(TaskEntry.TABLE_NAME, contentValues, selection, selectionArgs);
    }

    public int delete(long id) {
        selectionArgs = new String[`]{ String.valueOf(id) };

        return db.delete(TaskEntry.TABLE_NAME, selection, selectionArgs);
    }
}
