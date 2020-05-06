package io.github.prabhuomkar.flare.ui.playground;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import io.github.prabhuomkar.flare.R;
import io.github.prabhuomkar.flare.helpers.UIHelper;
import io.github.prabhuomkar.flare.ui.playground.customviews.DrawingCanvasView;

public class ImageClassificationCanvasActivity extends AppCompatActivity {

    TextView resultClass1, resultClass2, resultClass3, resultScore1, resultScore2,
            resultScore3, modelNameView;
    Button classifyButton, resetButton;
    DrawingCanvasView drawingCanvasView;
    String modelName;
    Boolean modelExists = true;

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
            drawingCanvasView.resetCanvas();
        });
        classifyButton.setOnClickListener(v -> {

        });
        modelNameView.setText(modelName);
    }

    @Override
    protected void onPause() {
        finish();
        super.onPause();
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
