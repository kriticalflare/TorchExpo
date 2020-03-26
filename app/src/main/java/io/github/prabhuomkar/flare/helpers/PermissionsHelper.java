package io.github.prabhuomkar.flare.helpers;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import io.github.prabhuomkar.flare.Constants;

public class PermissionsHelper {

    private static final String[] PERMISSIONS_LIST = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int PERMISSION_REQUEST_CODE = 1;

    public static void getPermissions(AppCompatActivity appCompatActivity) {
        Context context = appCompatActivity.getApplicationContext();
        for (int i = 0; i < PERMISSIONS_LIST.length; i++) {
            if (ContextCompat.checkSelfPermission(context, PERMISSIONS_LIST[i])
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(appCompatActivity, PERMISSIONS_LIST,
                        PERMISSION_REQUEST_CODE);
            }
        }
    }

    public static boolean hasAllPermissions(AppCompatActivity appCompatActivity) {
        Context context = appCompatActivity.getApplicationContext();
        for (int i = 0; i < PERMISSIONS_LIST.length; i++) {
            if (ContextCompat.checkSelfPermission(context, PERMISSIONS_LIST[i])
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void checkPermissions(AppCompatActivity appCompatActivity,
                                        int requestCode,
                                        @NonNull String[] permissions,
                                        @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length == 3 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED) {
            } else if (grantResults.length == 3) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        appCompatActivity);
                builder.setMessage(Constants.ERR_PERMISSION_MESSAGE)
                        .setTitle(Constants.ERR_PERMISSION_TITLE)
                        .setCancelable(false)
                        .setPositiveButton("GRANT PERMISSION", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package",
                                        appCompatActivity.getApplication().getPackageName(), null);
                                intent.setData(uri);
                                ((Activity) appCompatActivity).startActivityForResult(intent,
                                        PERMISSION_REQUEST_CODE);
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
    }

}
