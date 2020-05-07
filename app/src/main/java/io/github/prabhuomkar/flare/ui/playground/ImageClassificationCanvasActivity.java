package io.github.prabhuomkar.flare.ui.playground;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import io.github.prabhuomkar.flare.Constants;
import io.github.prabhuomkar.flare.R;
import io.github.prabhuomkar.flare.engine.imageclassification.mnist.Runner;
import io.github.prabhuomkar.flare.helpers.UIHelper;
import io.github.prabhuomkar.flare.ui.playground.customviews.DrawingCanvasView;

public class ImageClassificationCanvasActivity extends AppCompatActivity {

    TextView resultClass1, resultClass2, resultClass3, resultScore1, resultScore2,
            resultScore3, modelNameView;
    Button classifyButton, resetButton;
    DrawingCanvasView drawingCanvasView;
    String modelName;
    Boolean modelExists = true;
    List<Pair<Integer, Float>> scoresWithIdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_classification_canvas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        UIHelper.updateActionBar(this, "Image Classification", true);
        modelName = Objects.requireNonNull(getIntent().getExtras()).getString("modelName");

        classifyButton = (Button) findViewById(R.id.image_classifier_canvas_classify);
        resetButton = (Button) findViewById(R.id.image_classifier_canvas_reset);
        modelNameView = (TextView) findViewById(R.id.image_classifier_model_name);
        resultClass1 = (TextView) findViewById(R.id.image_classifier_result_1_class);
        resultClass2 = (TextView) findViewById(R.id.image_classifier_result_2_class);
        resultClass3 = (TextView) findViewById(R.id.image_classifier_result_3_class);
        resultScore1 = (TextView) findViewById(R.id.image_classifier_result_1_score);
        resultScore2 = (TextView) findViewById(R.id.image_classifier_result_2_score);
        resultScore3 = (TextView) findViewById(R.id.image_classifier_result_3_score);
        drawingCanvasView = (DrawingCanvasView) findViewById(R.id.image_drawing_canvas);

        resetButton.setOnClickListener(v -> {
            drawingCanvasView.invalidate();
            drawingCanvasView.resetCanvas();
            drawingCanvasView.destroyDrawingCache();
        });
        classifyButton.setOnClickListener(v -> {
            drawingCanvasView.buildDrawingCache();
            Bitmap canvasBitmap = bitmapToGrayScale(drawingCanvasView.getDrawingCache());
            Bitmap scaledCanvasBitmap = Bitmap.createScaledBitmap(
                    canvasBitmap, 28, 28, false);
            startClassification(scaledCanvasBitmap);
        });
        modelNameView.setText(modelName);
    }

    @Override
    protected void onPause() {
        finish();
        super.onPause();
    }

    private Bitmap bitmapToGrayScale(Bitmap bitmap) {
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        Bitmap bitmapGrayScale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapGrayScale);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(colorMatrixColorFilter);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bitmapGrayScale;
    }

    private void startClassification(Bitmap bitmap) {
        try {
            scoresWithIdx = Runner.mnist(ImageClassificationCanvasActivity.this,
                    bitmap, modelName);
        } catch (IOException e) {
            e.printStackTrace();
            modelExists = false;
        }
        ImageClassificationCanvasActivity.this.runOnUiThread(() -> {
            if (!modelExists) {
                Toast.makeText(ImageClassificationCanvasActivity.this,
                        Constants.ERR_MODEL_NOT_FOUND, Toast.LENGTH_LONG).show();
                finish();
            }
            ImageClassificationCanvasActivity.this.updateUIWithResults(scoresWithIdx);
        });
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
            resultClass1.setText("Digit " + first.first);
            resultClass2.setText("Digit " + second.first);
            resultClass3.setText("Digit " + third.first);
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
