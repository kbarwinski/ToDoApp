package pl.barwinski.todoapp.main;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.barwinski.todoapp.R;
import pl.barwinski.todoapp.shared.models.TaskModel;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<TaskModel> taskList;

    public TaskAdapter(List<TaskModel> taskList) {
        this.taskList = taskList;
    }

    // Constructor and other methods ...

    public static class TaskViewHolder  extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView dueDateTextView;
        public CheckBox doneCheckBox;
        public CheckBox notifyMeCheckBox;

        public TaskViewHolder(View view) {
            super(view);

            titleTextView = view.findViewById(R.id.titleTextView);
            descriptionTextView = view.findViewById(R.id.descriptionTextView);
            dueDateTextView = view.findViewById(R.id.dueDateTextView);
            doneCheckBox = view.findViewById(R.id.doneCheckBox);
            notifyMeCheckBox = view.findViewById(R.id.notifyMeCheckBox);
        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view
        View view = View.inflate(parent.getContext(), R.layout.task_item, null);
        // Create the ViewHolder
        TaskViewHolder holder = new TaskViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        TaskModel task = taskList.get(position);

        // Bind the data to the views
        holder.titleTextView.setText(task.title);
        holder.descriptionTextView.setText(task.description);
        holder.dueDateTextView.setText(task.due_date.toString());
        holder.doneCheckBox.setChecked(task.is_done);
        holder.notifyMeCheckBox.setChecked(task.notify_me);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
