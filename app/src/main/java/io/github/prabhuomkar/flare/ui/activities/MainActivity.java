package io.github.prabhuomkar.flare.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import io.github.prabhuomkar.flare.Constants;
import io.github.prabhuomkar.flare.R;
import io.github.prabhuomkar.flare.helpers.FragmentHelper;
import io.github.prabhuomkar.flare.helpers.PermissionsHelper;
import io.github.prabhuomkar.flare.ui.fragments.AppInfoFragment;
import io.github.prabhuomkar.flare.ui.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentHelper.switchFragment(new HomeFragment(), getSupportFragmentManager(), false);
        PermissionsHelper.getPermissions(MainActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_contact) {
            // Send an email to Authors/Maintainers
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", Constants.CONTACT_EMAIL, null));
            try {
                startActivity(Intent.createChooser(emailIntent, "Contact TorchExpo"));
            } catch (android.content.ActivityNotFoundException e) {
                Toast.makeText(MainActivity.this, Constants.ERR_NO_EMAIL_CLIENT, Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.action_help) {
            // Open GitHub Repository
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.HELP_URL)));
        } else if (id == R.id.action_app_info) {
            // Open App Info
            FragmentHelper.switchFragment(new AppInfoFragment(), getSupportFragmentManager(), true);
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionsHelper.checkPermissions(MainActivity.this,
                requestCode, permissions, grantResults);
    }

}
