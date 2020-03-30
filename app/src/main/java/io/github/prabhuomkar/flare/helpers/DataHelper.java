package io.github.prabhuomkar.flare.helpers;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;

import io.github.prabhuomkar.flare.Constants;
import io.github.prabhuomkar.flare.models.Model;

public class DataHelper {

    public static ArrayList<Model> getModelListForTask(String taskName) {
        switch (taskName) {
            case Constants.TASK_IMAGE_CLASSIFICATION:
                return Constants.TASK_IMAGE_CLASSIFICATION_MODELS;
        }
        return null;
    }

    public static String getVersionName(Context context) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo =
                context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        return "Version: " + packageInfo.versionName;
    }
}
