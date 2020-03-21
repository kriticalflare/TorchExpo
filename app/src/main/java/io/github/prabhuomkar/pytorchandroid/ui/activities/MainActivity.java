package io.github.prabhuomkar.pytorchandroid.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.github.prabhuomkar.pytorchandroid.Constants;
import io.github.prabhuomkar.pytorchandroid.R;
import io.github.prabhuomkar.pytorchandroid.helpers.FragmentHelper;
import io.github.prabhuomkar.pytorchandroid.helpers.UIHelper;
import io.github.prabhuomkar.pytorchandroid.ui.fragments.AppInfoFragment;
import io.github.prabhuomkar.pytorchandroid.ui.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UIHelper.setCustomActionBar(MainActivity.this);
        FragmentHelper.switchFragment(new HomeFragment(), getSupportFragmentManager(), false);
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
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{Constants.CONTACT_EMAIL});
            try {
                startActivity(Intent.createChooser(emailIntent, "Contact PyTorch Android"));
            } catch (android.content.ActivityNotFoundException e) {
                Toast.makeText(MainActivity.this, Constants.ERR_NO_EMAIL_CLIENT, Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.action_help) {
            // Open GitHub Repository
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.HELP_URL)));
        } else if (id == R.id.action_app_info) {
            // Open App Info
            FragmentHelper.switchFragment(new AppInfoFragment(), getSupportFragmentManager(), true);
        }

        return super.onOptionsItemSelected(item);
    }

}
