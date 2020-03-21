package io.github.prabhuomkar.pytorchandroid;

import java.util.ArrayList;

import io.github.prabhuomkar.pytorchandroid.models.Model;
import io.github.prabhuomkar.pytorchandroid.models.Task;

public class Constants {
    // Global Defaults
    public static final String PLACEHOLDER_IMAGE_NAME = "pytorch_placeholder";
    // Home Activity
    public static final String CONTACT_EMAIL = "prabhuomkar@pm.me";
    public static final String HELP_URL = "https://github.com/prabhuomkar/pytorch-android";
    // Errors
    public static final String ERR_NO_EMAIL_CLIENT = "Please select an email client";
    // Data for lists to show on UI
    public static final String TASK_IMAGE_CLASSIFICATION = "Image Classification";
    public static final ArrayList<Task> tasks = new ArrayList<Task>() {
        {
            add(new Task(TASK_IMAGE_CLASSIFICATION, "Image classification is the task of " +
                    "classifying an image into a class category. It is the most well-known " +
                    "computer vision task.", "task_image_classification"));
        }
    };
    // Models specific to tasks
    public static final ArrayList<Model> TASK_IMAGE_CLASSIFICATION_MODELS = new ArrayList<Model>() {
        {
            add(new Model("AlexNet", "The 2012 ImageNet winner achieved a top-5 error of 15.3%," +
                    " more than 10.8 percentage points lower than that of the runner up.",
                    "https://arxiv.org/abs/1404.5997", "https://pytorch.org/hub/pytorch_vision_alexnet/",
                    "", "https://pytorch.org/assets/images/alexnet2.png"));
            add(new Model("ResNet-18", "Next generation ResNets, more efficient and accurate",
                    "https://arxiv.org/abs/1512.03385", "https://pytorch.org/hub/pytorch_vision_resnext/",
                    "", "https://pytorch.org/assets/images/resnet.png"));
            add(new Model("GoogleNet", "GoogLeNet was based on a deep convolutional neural network " +
                    "architecture codenamed \"Inception\" which won ImageNet 2014.",
                    "https://arxiv.org/abs/1409.4842", "https://pytorch.org/hub/pytorch_vision_googlenet/",
                    "", "https://pytorch.org/assets/images/googlenet2.png"));
        }
    };
}
