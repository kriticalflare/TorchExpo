package io.github.prabhuomkar.pytorchandroid.helpers;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.annotation.IdRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import io.github.prabhuomkar.pytorchandroid.R;

public class UIHelper {

    public static void setCustomActionBar(AppCompatActivity appCompatActivity) {
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        Objects.requireNonNull(actionBar)
                .setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.custom_action_bar);
    }

    public static void setupRecyclerView(View rootView, Context context, @IdRes int id,
                                         boolean isHorizontal, RecyclerView.Adapter adapter) {
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(id);
        RecyclerView.LayoutManager layoutManager = (isHorizontal) ? new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false) :
                new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        if (!isHorizontal) {
            recyclerView.addItemDecoration(new DividerItemDecoration(context,
                    DividerItemDecoration.VERTICAL));
        }
    }

    public static void updateModelDownloadButton(View view, String buttonText) {
        Button downloadButton = (Button) view.findViewById(R.id.list_model_item_download);
        if (buttonText.equals("Done")) {
            downloadButton.setVisibility(View.GONE);
        } else {
            downloadButton.setText(buttonText);
        }
    }

}
