package io.github.prabhuomkar.flare.ui.playground;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import io.github.prabhuomkar.flare.R;
import io.github.prabhuomkar.flare.helpers.UIHelper;

public class ImageSegmentationActivity extends AppCompatActivity {

    String modelName;
    Boolean modelExists = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_segmentation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        UIHelper.updateActionBar(this, "Image Segmentation", true);
        modelName = Objects.requireNonNull(getIntent().getExtras()).getString("modelName");
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
