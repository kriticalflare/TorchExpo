package io.github.prabhuomkar.flare;

import java.util.ArrayList;

import io.github.prabhuomkar.flare.models.Model;
import io.github.prabhuomkar.flare.models.Task;

public class Constants {
    // Global Defaults
    public static final String PLACEHOLDER_IMAGE_NAME = "pytorch_placeholder";
    public static final String BUTTON_STATE_DOWNLOAD = "Download";
    public static final String BUTTON_STATE_DOWNLOADING = "Downloading...";
    public static final String BUTTON_STATE_CANCEL = "Cancel";
    public static final String BUTTON_STATE_DONE = "Done";
    public static final String DOWNLOAD_PROGRESS_STATE_DONE = "100.00%";
    // Home Activity
    public static final String CONTACT_EMAIL = "prabhuomkar@pm.me";
    public static final String HELP_URL = "https://github.com/prabhuomkar/TorchExpo";
    // Errors
    public static final String ERR_NO_EMAIL_CLIENT = "Please select an email client";
    public static final String ERR_MODEL_NOT_FOUND = "Model not found";
    public static final String ERR_PERMISSION_TITLE = "Permission Required";
    public static final String ERR_PERMISSION_MESSAGE = "TorchExpo requires Camera " +
            "Permission to capture images for classification & Storage Permission to download " +
            "models";
    // Data for lists to show on UI
    public static final String TASK_IMAGE_CLASSIFICATION = "Image Classification";
    public static final String TASK_IMAGE_SEGMENTATION = "Image Segmentation";
    public static final ArrayList<Task> tasks = new ArrayList<Task>() {
        {
            add(new Task(TASK_IMAGE_CLASSIFICATION, "Image classification is the task of " +
                    "classifying an image into a class category. It is the most well-known " +
                    "computer vision task.", "task_image_classification"));
            add(new Task(TASK_IMAGE_SEGMENTATION, "Task of clustering parts of an image " +
                    "together which belong to the same object class. It is a form of pixel-level " +
                    "prediction.", "task_image_segmentation"));
        }
    };
    // Playground Activity Names
    public static final String PLAYGROUND_IMAGE_CLASSIFICATION = "ImageClassification";
    public static final String PLAYGROUND_IMAGE_CLASSIFICATION_CANVAS =
            "ImageClassificationCanvas";
    public static final String PLAYGROUND_IMAGE_SEGMENTATION = "ImageSegmentation";
    // Models specific to tasks
    public static final ArrayList<Model> TASK_IMAGE_CLASSIFICATION_MODELS = new ArrayList<Model>() {
        {
            add(new Model("MNIST CNN",
                    "Simple Convolutional Neural Networks for Yann LeCun's MNIST Dataset", "",
                    "https://github.com/pytorch/examples/tree/master/mnist",
                    "https://rebrand.ly/torchexpo-mnist-cnn",
                    "https://3qeqpr26caki16dnhd19sv6by6v-wpengine.netdna-ssl" +
                            ".com/wp-content/uploads/2019/02/Plot-of-a-Subset-of-Images-from-the-MNIST-Dataset.png",
                    PLAYGROUND_IMAGE_CLASSIFICATION_CANVAS, 5));
            add(new Model("ResNet-18",
                    "Next generation ResNets, more efficient and accurate",
                    "https://arxiv.org/abs/1512.03385",
                    "https://pytorch.org/hub/pytorch_vision_resnext/",
                    "https://rebrand.ly/torchexpo-resnet-18",
                    "https://pytorch.org/assets/images/resnet.png",
                    PLAYGROUND_IMAGE_CLASSIFICATION, 47));
            add(new Model("GoogleNet",
                    "GoogLeNet was based on a deep convolutional neural network " +
                            "architecture codenamed \"Inception\" which won ImageNet 2014.",
                    "https://arxiv.org/abs/1409.4842",
                    "https://pytorch.org/hub/pytorch_vision_googlenet/",
                    "https://rebrand.ly/torchexpo-googlenet",
                    "https://pytorch.org/assets/images/googlenet2.png",
                    PLAYGROUND_IMAGE_CLASSIFICATION, 27));
            add(new Model("AlexNet",
                    "The 2012 ImageNet winner achieved a top-5 error of 15.3%," +
                            " more than 10.8 percentage points lower than that of the runner up.",
                    "https://arxiv.org/abs/1404.5997",
                    "https://pytorch.org/hub/pytorch_vision_alexnet/",
                    "https://rebrand.ly/torchexpo-alexnet",
                    "https://pytorch.org/assets/images/alexnet2.png",
                    PLAYGROUND_IMAGE_CLASSIFICATION, 244));
        }
    };
    public static final ArrayList<Model> TASK_IMAGE_SEGMENTATION_MODELS = new ArrayList<Model>() {
        {
            add(new Model("DeeplabV3-ResNet101",
                    "DeepLabV3 model with a ResNet-101 backbone",
                    "https://arxiv.org/abs/1706.05587",
                    "https://pytorch.org/hub/pytorch_vision_deeplabv3_resnet101/",
                    "https://rebrand.ly/torchexpo-deeplabv3-resnet101",
                    "https://pytorch.org/assets/images/deeplab2.png",
                    PLAYGROUND_IMAGE_SEGMENTATION, 245));
            add(new Model("FCN-ResNet101",
                    "Fully-Convolutional Network model with a ResNet-101 backbone for Semantic Segmentation",
                    "https://arxiv.org/abs/1411.4038",
                    "https://pytorch.org/hub/pytorch_vision_fcn_resnet101/",
                    "https://rebrand.ly/torchexpo-fcn-resnet101",
                    "https://pytorch.org/assets/images/fcn2.png",
                    PLAYGROUND_IMAGE_SEGMENTATION, 218));
        }
    };
}
