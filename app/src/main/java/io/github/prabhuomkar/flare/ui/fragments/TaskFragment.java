package io.github.prabhuomkar.flare.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import io.github.prabhuomkar.flare.R;
import io.github.prabhuomkar.flare.helpers.DataHelper;
import io.github.prabhuomkar.flare.helpers.UIHelper;
import io.github.prabhuomkar.flare.models.Task;
import io.github.prabhuomkar.flare.ui.adapters.ModelListAdapter;

public class TaskFragment extends Fragment {
    private static final String ARG_NAME = "name";
    private static final String ARG_DESCRIPTION = "description";
    private static final String ARG_IMAGELINK = "imageLink";

    private Task mTask;
    private ModelListAdapter modelListAdapter;

    public static TaskFragment newInstance(Task task) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, task.getName());
        args.putString(ARG_DESCRIPTION, task.getDescription());
        args.putString(ARG_IMAGELINK, task.getImageLink());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTask = new Task(getArguments().getString(ARG_NAME),
                    getArguments().getString(ARG_DESCRIPTION),
                    getArguments().getString(ARG_IMAGELINK));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UIHelper.updateActionBar(getActivity(), mTask.getName(), true);
        View taskView = inflater.inflate(R.layout.fragment_task, container, false);
        Context context = taskView.getContext();

        String taskName = mTask.getName();
        modelListAdapter =
                new ModelListAdapter(taskName, DataHelper.getModelListForTask(taskName));
        UIHelper.setupRecyclerView(taskView, context, R.id.list_models, false, modelListAdapter);

        return taskView;
    }
}
