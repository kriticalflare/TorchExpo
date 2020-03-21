package io.github.prabhuomkar.pytorchandroid.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import io.github.prabhuomkar.pytorchandroid.Constants;
import io.github.prabhuomkar.pytorchandroid.R;
import io.github.prabhuomkar.pytorchandroid.ui.adapters.TaskListAdapter;
import io.github.prabhuomkar.pytorchandroid.helpers.UIHelper;

public class HomeFragment extends Fragment {

    private TaskListAdapter taskListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);
        Context context = homeView.getContext();

        taskListAdapter = new TaskListAdapter(Constants.tasks);
        UIHelper.setupRecyclerView(homeView, context, R.id.list_tasks, false, taskListAdapter);

        return homeView;
    }
}
