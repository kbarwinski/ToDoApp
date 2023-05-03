package pl.barwinski.todoapp.shared.models;

import java.util.Date;

public class TaskModel {
    public long _id;
    public String title;
    public String description;

    public Date creation_date;
    public Date due_date;

    public boolean is_done;
    public boolean notify_me;
    public long category_id;

    public TaskModel() {
    }
}
