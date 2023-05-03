package pl.barwinski.todoapp.shared.models;

public class AttachmentModel {
    private long id;

    private String name;
    private String path;
    private String type;

    private long task_id;
    private TaskModel task;

    public AttachmentModel() {
    }

}
