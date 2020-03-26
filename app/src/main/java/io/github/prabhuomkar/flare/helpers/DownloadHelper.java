package io.github.prabhuomkar.flare.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.downloader.Error;
import com.downloader.OnDownloadListener;
import com.downloader.PRDownloader;

import io.github.prabhuomkar.flare.Constants;

public class DownloadHelper {

    public static int downloadModel(Context context, View view,
                                    String downloadUrl, String downloadLocation, String fileName) {
        return PRDownloader.download(downloadUrl, downloadLocation, fileName)
                .setTag(fileName)
                .build()
                .setOnCancelListener(() -> {
                    UIHelper.updateModelDownloadButton(view, Constants.BUTTON_STATE_DOWNLOAD);
                    UIHelper.updateModelDownloadProgress(view, Constants.DOWNLOAD_PROGRESS_STATE_DONE);
                })
                .setOnProgressListener(progress -> {
                    @SuppressLint("DefaultLocale") String percentage =
                            String.format("%.2f", ((double) progress.currentBytes /
                                    (double) progress.totalBytes) * 100.0) + "%";
                    UIHelper.updateModelDownloadProgress(view, percentage);
                    UIHelper.updateModelDownloadButton(view, Constants.BUTTON_STATE_CANCEL);
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        Toast.makeText(context, "Model File: " + fileName +
                                " downloaded successfully", Toast.LENGTH_SHORT).show();
                        UIHelper.updateModelDownloadButton(view, Constants.BUTTON_STATE_DONE);
                        UIHelper.updateModelDownloadProgress(view, Constants.DOWNLOAD_PROGRESS_STATE_DONE);
                    }

                    @Override
                    public void onError(Error error) {
                        Toast.makeText(context, "Some error. Try again!",
                                Toast.LENGTH_SHORT).show();
                        UIHelper.updateModelDownloadButton(view, Constants.BUTTON_STATE_DOWNLOAD);
                        UIHelper.updateModelDownloadProgress(view, Constants.DOWNLOAD_PROGRESS_STATE_DONE);
                    }
                });
    }

    public static void cancelDownload(String downloadTag) {
        PRDownloader.cancel(downloadTag);
        PRDownloader.cleanUp(64);
    }
}
