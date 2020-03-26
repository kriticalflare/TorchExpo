package io.github.prabhuomkar.flare.helpers;

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
}
