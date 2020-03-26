package io.github.prabhuomkar.flare.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.prabhuomkar.flare.R;
import io.github.prabhuomkar.flare.helpers.FileHelper;
import io.github.prabhuomkar.flare.helpers.FragmentHelper;
import io.github.prabhuomkar.flare.models.Task;
import io.github.prabhuomkar.flare.ui.fragments.TaskFragment;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskView> {

    private List<Task> taskList;
    private Context context;

    public TaskListAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskListAdapter.TaskView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = (AppCompatActivity) parent.getContext();
        View taskItemView = LayoutInflater.from(context)
                .inflate(R.layout.list_task_item, parent, false);

        // clickListener implements the action to switch to specific action related activity
        RecyclerViewClickListener clickListener = (view, position) -> {
            FragmentHelper.switchFragment(TaskFragment.newInstance(taskList.get(position)),
                    ((AppCompatActivity) context).getSupportFragmentManager(), true);
        };

        return new TaskListAdapter.TaskView(taskItemView, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListAdapter.TaskView holder, int position) {
        Task currentTask = taskList.get(position);
        holder.taskNameView.setText(currentTask.getName());
        holder.taskDescriptionView.setText(currentTask.getDescription());
        holder.taskImageView.setImageResource(FileHelper.getImageResourceId(
                context, currentTask.getImageLink()));
    }

    @Override
    public int getItemCount() {
        return taskList == null ? 0 : taskList.size();
    }


    public class TaskView extends RecyclerView.ViewHolder implements View.OnClickListener {

        RecyclerViewClickListener clickListener;
        TextView taskNameView, taskDescriptionView;
        ImageView taskImageView;

        public TaskView(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            taskImageView = (ImageView) itemView
                    .findViewById(R.id.list_task_item_image);
            taskNameView = (TextView) itemView
                    .findViewById(R.id.list_task_item_name);
            taskDescriptionView = (TextView) itemView
                    .findViewById(R.id.list_task_item_description);
            clickListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }
}
