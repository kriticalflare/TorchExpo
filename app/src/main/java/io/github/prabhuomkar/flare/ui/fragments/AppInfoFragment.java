package io.github.prabhuomkar.flare.ui.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;

import io.github.prabhuomkar.flare.R;
import io.github.prabhuomkar.flare.helpers.DataHelper;

public class AppInfoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View appInfoView = inflater.inflate(R.layout.fragment_app_info, container, false);

        TextView licensesView = (TextView) appInfoView.findViewById(R.id.app_info_licenses);
        TextView versionView = (TextView) appInfoView.findViewById(R.id.app_info_version);

        try {
            versionView.setText(DataHelper.getVersionName(appInfoView.getContext()));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        licensesView.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), OssLicensesMenuActivity.class));
        });

        return appInfoView;
    }
}
