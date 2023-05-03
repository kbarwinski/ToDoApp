package pl.barwinski.todoapp.shared.contracts;

import android.provider.BaseColumns;

public class TaskEntry implements BaseColumns {
    public static final String TABLE_NAME = "task";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_CREATION_DATE = "creation_date";
    public static final String COLUMN_NAME_DUE_DATE = "due_date";
    public static final String COLUMN_NAME_IS_DONE = "is_done";
    public static final String COLUMN_NAME_NOTIFY_ME = "notify_me";
    public static final String COLUMN_NAME_CATEGORY_ID = "category_id";
}
