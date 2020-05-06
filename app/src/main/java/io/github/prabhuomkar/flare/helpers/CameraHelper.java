package io.github.prabhuomkar.flare.helpers;

import android.util.Size;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageAnalysisConfig;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;

import io.github.prabhuomkar.flare.engine.imageclassification.imagenet.Target;
import io.github.prabhuomkar.flare.ui.playground.customviews.AutoFitTextureView;

public class CameraHelper {

    public static Preview buildPreview(AutoFitTextureView textureView) {
        int imgWidth = textureView.getWidth();
        int imgHeight = textureView.getHeight();
        Size screen = new Size(imgWidth, imgHeight);
        PreviewConfig previewConfig = new PreviewConfig.Builder()
                .setTargetResolution(screen).build();
        Preview preview = new Preview(previewConfig);
        preview.setOnPreviewOutputUpdateListener(
                new Preview.OnPreviewOutputUpdateListener() {
                    @Override
                    public void onUpdated(@NonNull Preview.PreviewOutput output) {
                        ViewGroup parent = (ViewGroup) textureView.getParent();
                        parent.removeView(textureView);
                        parent.addView(textureView, 0);
                        textureView.setSurfaceTexture(output.getSurfaceTexture());
                    }
                });
        return preview;
    }

    public static ImageAnalysis buildImageAnalysis() {
        ImageAnalysisConfig imageAnalysisConfig = new ImageAnalysisConfig.Builder()
                .setTargetResolution(new Size(Target.IMAGE_WIDTH, Target.IMAGE_HEIGHT))
                .setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
                .build();
        return new ImageAnalysis(imageAnalysisConfig);
    }

}
