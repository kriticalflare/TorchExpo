package io.github.prabhuomkar.pytorchandroid.datasets.imagenet;

import android.content.Context;
import android.util.Pair;

import androidx.camera.core.ImageProxy;

import org.pytorch.IValue;
import org.pytorch.Module;
import org.pytorch.Tensor;
import org.pytorch.torchvision.TensorImageUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.prabhuomkar.pytorchandroid.helpers.FileHelper;

public class Runner {

    public static List<Pair<Integer, Float>> imageNet(Context context, ImageProxy imageProxy,
                                                      int rotationDegrees,
                                                      String modelName) {
        List<Pair<Integer, Float>> scoresWithIdx = new ArrayList<>();
        Tensor inputTensor = TensorImageUtils.imageYUV420CenterCropToFloat32Tensor(
                imageProxy.getImage(),
                rotationDegrees, Target.IMAGE_WIDTH, Target.IMAGE_HEIGHT,
                TensorImageUtils.TORCHVISION_NORM_MEAN_RGB,
                TensorImageUtils.TORCHVISION_NORM_MEAN_RGB);
        Module module = Module.load(FileHelper.getAssetModelFilePath(context, modelName));
        Tensor outputTensor = module.forward(IValue.from(inputTensor)).toTensor();
        float[] scores = outputTensor.getDataAsFloatArray();
        for (int i = 0; i < scores.length; i++) scoresWithIdx.add(new Pair<>(i, scores[i]));
        Collections.sort(scoresWithIdx, (score1, score2) ->
                score2.second.compareTo(score1.second));
        return scoresWithIdx;
    }
}
