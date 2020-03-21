package io.github.prabhuomkar.pytorchandroid.playground;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import io.github.prabhuomkar.pytorchandroid.R;
import io.github.prabhuomkar.pytorchandroid.helpers.UIHelper;

public class ImageClassificationActivity extends AppCompatActivity {

    TextView resultClass1, resultClass2, resultClass3, resultScore1, resultScore2, resultScore3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_classification);
        UIHelper.setCustomActionBar(ImageClassificationActivity.this);
        String modelName = Objects.requireNonNull(getIntent().getExtras()).getString("modelName");
        resultClass1 = (TextView) findViewById(R.id.image_classifier_result_1_class);
        resultClass2 = (TextView) findViewById(R.id.image_classifier_result_2_class);
        resultClass3 = (TextView) findViewById(R.id.image_classifier_result_3_class);
        resultScore1 = (TextView) findViewById(R.id.image_classifier_result_1_score);
        resultScore2 = (TextView) findViewById(R.id.image_classifier_result_2_score);
        resultScore3 = (TextView) findViewById(R.id.image_classifier_result_3_score);
    }

}
