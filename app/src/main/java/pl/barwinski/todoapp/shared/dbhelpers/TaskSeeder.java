package pl.barwinski.todoapp.shared.dbhelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import pl.barwinski.todoapp.shared.contracts.TaskEntry;
import pl.barwinski.todoapp.shared.helpers.ContentValuesMapper;
import pl.barwinski.todoapp.shared.helpers.RNGenerator;
import pl.barwinski.todoapp.shared.models.TaskModel;

public class TaskSeeder {
    private Context ctx;
    private View view;
    private TaskRepository taskRepository;

    public TaskSeeder(Context ctx) {
        this.ctx = ctx;
        this.taskRepository = new TaskRepository(ctx);
    }

    public TaskSeeder(View v){
        this.view = v;
        this.ctx = v.getContext();
        this.taskRepository = new TaskRepository(ctx);
    }

    public void seedRandom(){
        TaskModel task = new TaskModel();
        task.title = RNGenerator.getRandomTask();
        task.description = RNGenerator.getRandomAnimalColor();
        task.is_done = RNGenerator.getRandomBoolean();
        task.notify_me = RNGenerator.getRandomBoolean();
        task.category_id = RNGenerator.getRandomInt(1, 5);
        task.creation_date = RNGenerator.getRandomDateFromNow();
        task.due_date = RNGenerator.getRandomDateFromAfter(task.creation_date);

        ContentValues values = ContentValuesMapper.mapObjectToContentValues(task);
        taskRepository.create(values);
    }

    public void seedXTimes(int x){
        for(int i = 0; i < x; i++){
            this.seedRandom();
        }
    }
}
