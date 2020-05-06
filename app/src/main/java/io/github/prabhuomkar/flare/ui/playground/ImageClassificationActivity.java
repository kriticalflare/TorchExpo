package io.github.prabhuomkar.flare.ui.playground;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.lifecycle.LifecycleOwner;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;

import io.github.prabhuomkar.flare.Constants;
import io.github.prabhuomkar.flare.R;
import io.github.prabhuomkar.flare.engine.imageclassification.imagenet.Runner;
import io.github.prabhuomkar.flare.engine.imageclassification.imagenet.Target;
import io.github.prabhuomkar.flare.helpers.CameraHelper;
import io.github.prabhuomkar.flare.helpers.PermissionsHelper;
import io.github.prabhuomkar.flare.helpers.UIHelper;
import io.github.prabhuomkar.flare.ui.playground.customviews.AutoFitTextureView;

public class ImageClassificationActivity extends AppCompatActivity {

    TextView resultClass1, resultClass2, resultClass3, resultScore1, resultScore2,
            resultScore3, modelNameView;
    AutoFitTextureView textureView;
    String modelName;
    Boolean modelExists = true;
    List<Pair<Integer, Float>> scoresWithIdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_classification);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        UIHelper.updateActionBar(this, "Image Classification", true);
        modelName = Objects.requireNonNull(getIntent().getExtras()).getString("modelName");

        modelNameView = (TextView) findViewById(R.id.image_classifier_model_name);
        resultClass1 = (TextView) findViewById(R.id.image_classifier_result_1_class);
        resultClass2 = (TextView) findViewById(R.id.image_classifier_result_2_class);
        resultClass3 = (TextView) findViewById(R.id.image_classifier_result_3_class);
        resultScore1 = (TextView) findViewById(R.id.image_classifier_result_1_score);
        resultScore2 = (TextView) findViewById(R.id.image_classifier_result_2_score);
        resultScore3 = (TextView) findViewById(R.id.image_classifier_result_3_score);
        textureView = (AutoFitTextureView) findViewById(R.id.image_classifier_texture);

        modelNameView.setText(modelName);

        if (PermissionsHelper.hasAllPermissions(ImageClassificationActivity.this)) {
            startCamera();
        } else {
            PermissionsHelper.getPermissions(ImageClassificationActivity.this);
            Toast.makeText(ImageClassificationActivity.this, Constants.ERR_PERMISSION_TITLE,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        finish();
        super.onPause();
    }

    private void startCamera() {
        CameraX.unbindAll();
        Preview preview = CameraHelper.buildPreview(textureView);
        ImageAnalysis imageAnalysis = CameraHelper.buildImageAnalysis();
        imageAnalysis.setAnalyzer(Executors.newSingleThreadExecutor(),
                (imageProxy, rotationDegrees) -> {
                    try {
                        scoresWithIdx = Runner.imageNet(ImageClassificationActivity.this,
                                imageProxy, rotationDegrees, modelName);
                    } catch (IOException e) {
                        e.printStackTrace();
                        modelExists = false;
                    }
                    ImageClassificationActivity.this.runOnUiThread(() -> {
                        if (!modelExists) {
                            Toast.makeText(ImageClassificationActivity.this,
                                    Constants.ERR_MODEL_NOT_FOUND, Toast.LENGTH_LONG).show();
                            finish();
                        }
                        ImageClassificationActivity.this.updateUIWithResults(scoresWithIdx);
                    });
                });
        CameraX.bindToLifecycle((LifecycleOwner) this, imageAnalysis, preview);
    }

    @SuppressLint("DefaultLocale")
    private void updateUIWithResults(List<Pair<Integer, Float>> scoresWithIdx) {
        if (scoresWithIdx != null) {
            Pair<Integer, Float> first = scoresWithIdx.get(0),
                    second = scoresWithIdx.get(1),
                    third = scoresWithIdx.get(2);
            resultScore1.setText(String.format("%.2f", first.second));
            resultScore2.setText(String.format("%.2f", second.second));
            resultScore3.setText(String.format("%.2f", third.second));
            resultClass1.setText(Target.IMAGENET_TARGET_CLASSES[first.first]);
            resultClass2.setText(Target.IMAGENET_TARGET_CLASSES[second.first]);
            resultClass3.setText(Target.IMAGENET_TARGET_CLASSES[third.first]);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
