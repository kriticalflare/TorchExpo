package io.github.prabhuomkar.flare.helpers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;

import io.github.prabhuomkar.flare.Constants;
import io.github.prabhuomkar.flare.models.Model;
import io.github.prabhuomkar.flare.ui.playground.ImageClassificationActivity;
import io.github.prabhuomkar.flare.ui.playground.ImageClassificationCanvasActivity;
import io.github.prabhuomkar.flare.ui.playground.ImageSegmentationActivity;

public class DataHelper {

    public static ArrayList<Model> getModelListForTask(String taskName) {
        switch (taskName) {
            case Constants.TASK_IMAGE_CLASSIFICATION:
                return Constants.TASK_IMAGE_CLASSIFICATION_MODELS;
            case Constants.TASK_IMAGE_SEGMENTATION:
                return Constants.TASK_IMAGE_SEGMENTATION_MODELS;
        }
        return null;
    }

    public static Intent getRunnerIntentForModel(Context context, String playgroundActivity) {
        switch (playgroundActivity) {
            case Constants.PLAYGROUND_IMAGE_CLASSIFICATION:
                return new Intent(context, ImageClassificationActivity.class);
            case Constants.PLAYGROUND_IMAGE_CLASSIFICATION_CANVAS:
                return new Intent(context, ImageClassificationCanvasActivity.class);
            case Constants.PLAYGROUND_IMAGE_SEGMENTATION:
                return new Intent(context, ImageSegmentationActivity.class);
        }
        return null;
    }

    public static String getVersionString(Context context) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo =
                context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        return "Version: " + packageInfo.versionName;
    }
}
