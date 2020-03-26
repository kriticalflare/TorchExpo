package io.github.prabhuomkar.flare.helpers;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;

import java.io.File;
import java.util.Objects;

import io.github.prabhuomkar.flare.Constants;

public class FileHelper {

    public static String getAssetModelFilePath(Context context, String modelName) {
        String assetFileName = getAssetFileNameForModel(modelName);
        return getDownloadDirPath(context).concat("/" + assetFileName);
    }

    public static String getAssetFileNameForModel(String modelName) {
        return modelName.toLowerCase().replaceAll("[ -]", "_") + ".pt";
    }

    public static int getImageResourceId(Context context, String resourceName) {
        Resources res = context.getResources();
        int validResId = res.getIdentifier(resourceName, "drawable", context.getPackageName());
        return (validResId != 0) ? validResId :
                res.getIdentifier(Constants.PLACEHOLDER_IMAGE_NAME, "drawable",
                        context.getPackageName());
    }

    public static String getDownloadDirPath(Context context) {
        return Objects.requireNonNull(context.getApplicationContext()
                .getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)).toString();
    }

    public static boolean checkIfFileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

}
