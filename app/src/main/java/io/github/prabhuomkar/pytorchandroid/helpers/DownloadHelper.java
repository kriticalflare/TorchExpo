package io.github.prabhuomkar.pytorchandroid.helpers;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.downloader.Error;
import com.downloader.OnDownloadListener;
import com.downloader.PRDownloader;

public class DownloadHelper {

    public static int downloadModel(Context context, View view,
                                    String downloadUrl, String downloadLocation, String fileName) {
        return PRDownloader.download(downloadUrl, downloadLocation, fileName)
                .setTag(fileName)
                .build()
                .setOnCancelListener(() -> {
                    UIHelper.updateModelDownloadButton(view, "Download");
                })
                .setOnProgressListener(progress -> {
                    UIHelper.updateModelDownloadButton(view, "Cancel");
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        Toast.makeText(context, "Model File: " + fileName +
                                " downloaded successfully", Toast.LENGTH_SHORT).show();
                        UIHelper.updateModelDownloadButton(view, "Done");
                        Log.v(getClass().getName(), "onDownloadComplete");
                    }

                    @Override
                    public void onError(Error error) {
                        Toast.makeText(context, "Some error. Try again!",
                                Toast.LENGTH_SHORT).show();
                        Log.v(getClass().getName(), "onError");
                    }
                });
    }

    public static void cancelDownload(String downloadTag) {
        PRDownloader.cancel(downloadTag);
        PRDownloader.cleanUp(64);
    }
}
