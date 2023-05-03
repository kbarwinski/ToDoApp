package pl.barwinski.todoapp.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import pl.barwinski.todoapp.R;
import pl.barwinski.todoapp.shared.dbhelpers.TaskDbHelper;
import pl.barwinski.todoapp.shared.dbhelpers.TaskRepository;
import pl.barwinski.todoapp.shared.dbhelpers.TaskSeeder;
import pl.barwinski.todoapp.shared.models.TaskModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<TaskModel> tasksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TaskSeeder seeder = new TaskSeeder(this);
        seeder.seedXTimes(3);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        TaskRepository taskRepository = new TaskRepository(this);
        this.tasksList = taskRepository.read();
        recyclerView.setAdapter(new TaskAdapter(tasksList));

        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public void goToForm(View view) {

    }
}