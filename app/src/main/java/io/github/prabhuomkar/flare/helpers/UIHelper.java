package io.github.prabhuomkar.flare.helpers;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import io.github.prabhuomkar.flare.Constants;
import io.github.prabhuomkar.flare.R;

public class UIHelper {

    public static void updateActionBar(FragmentActivity fragmentActivity, String title,
                                       boolean showBackButton) {
        ActionBar actionBar = ((AppCompatActivity) fragmentActivity).getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(title);
        actionBar.setDisplayHomeAsUpEnabled(showBackButton);
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

    public static void updateModelDownloadProgress(View view, String percentage) {
        TextView progressView =
                (TextView) view.findViewById(R.id.list_model_item_download_progress);
        if (percentage.equals(Constants.DOWNLOAD_PROGRESS_STATE_DONE)) {
            progressView.setVisibility(View.GONE);
        } else {
            progressView.setVisibility(View.VISIBLE);
            progressView.setText(percentage);
        }
    }

}
