package pl.barwinski.todoapp.shared.contracts;

public final class TaskContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private TaskContract() {}

    /* Inner class that defines the table contents */

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TaskEntry.TABLE_NAME + " (" +
                    TaskEntry._ID + " INTEGER PRIMARY KEY," +
                    TaskEntry.COLUMN_NAME_TITLE + " TEXT," +
                    TaskEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    TaskEntry.COLUMN_NAME_CREATION_DATE + " DATE," +
                    TaskEntry.COLUMN_NAME_DUE_DATE + " DATE," +
                    TaskEntry.COLUMN_NAME_IS_DONE + " BOOLEAN," +
                    TaskEntry.COLUMN_NAME_NOTIFY_ME + " BOOLEAN," +
                    TaskEntry.COLUMN_NAME_CATEGORY_ID + " INTEGER)";

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TaskEntry.TABLE_NAME;
}

