package io.github.prabhuomkar.pytorchandroid.helpers;

import java.util.ArrayList;

import io.github.prabhuomkar.pytorchandroid.Constants;
import io.github.prabhuomkar.pytorchandroid.models.Model;

public class DataHelper {

    public static ArrayList<Model> getModelListForTask(String taskName) {
        switch (taskName) {
            case Constants.TASK_IMAGE_CLASSIFICATION:
                return Constants.TASK_IMAGE_CLASSIFICATION_MODELS;
        }
        return null;
    }
}
