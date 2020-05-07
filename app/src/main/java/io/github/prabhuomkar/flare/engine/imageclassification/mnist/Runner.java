package io.github.prabhuomkar.flare.engine.imageclassification.mnist;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Pair;

import org.pytorch.IValue;
import org.pytorch.Module;
import org.pytorch.Tensor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.prabhuomkar.flare.Constants;
import io.github.prabhuomkar.flare.helpers.FileHelper;

public class Runner {

    public static List<Pair<Integer, Float>> mnist(Context context, Bitmap bitmap,
                                                   String modelName) throws IOException {
        List<Pair<Integer, Float>> scoresWithIdx = new ArrayList<>();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(width * height * 4);
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(),
                bitmap.getHeight());
        float[] floatArray = new float[width * height];
        for (int i = 0; i < pixels.length; i++) {
            int r = (pixels[i] >> 16 & 0xFF);
            int g = (pixels[i] >> 8 & 0xFF);
            int b = (pixels[i] & 0xFF);
            // Convert RGB to grayscale and normalize pixel value to [0..1]
            float normalizedPixelValue = (r + g + b) / 3.0f / 255.0f;
            byteBuffer.putFloat(normalizedPixelValue);
            floatArray[i] = normalizedPixelValue;
        }
        Tensor inputTensor = Tensor.fromBlob(floatArray, new long[]{1, 1, width, height});
        if (!FileHelper.checkIfFileExists(FileHelper.getAssetModelFilePath(context, modelName))) {
            throw new IOException(Constants.ERR_MODEL_NOT_FOUND);
        }
        Module module = Module.load(FileHelper.getAssetModelFilePath(context, modelName));
        Tensor outputTensor = module.forward(IValue.from(inputTensor)).toTensor();
        float[] scores = outputTensor.getDataAsFloatArray();
        for (int i = 0; i < scores.length; i++) scoresWithIdx.add(new Pair<>(i, scores[i]));
        Collections.sort(scoresWithIdx, (score1, score2) ->
                score2.second.compareTo(score1.second));
        return scoresWithIdx;
    }
}
