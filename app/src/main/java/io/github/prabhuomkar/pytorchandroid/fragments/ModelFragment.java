package io.github.prabhuomkar.pytorchandroid.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import io.github.prabhuomkar.pytorchandroid.R;
import io.github.prabhuomkar.pytorchandroid.models.Model;

public class ModelFragment extends Fragment {
    private static final String ARG_NAME = "name";
    private static final String ARG_DESCRIPTION = "description";
    private static final String ARG_PAPERLINK = "paperLink";
    private static final String ARG_SOURCELINK = "sourceLink";
    private static final String ARG_DOWNLOADLINK = "downloadLink";
    private static final String ARG_IMAGELINK = "imageLink";
    TextView nameView, descriptionView, paperLinkView, sourceLinkView;
    ImageView imageView;
    private Model mModel;

    public static ModelFragment newInstance(Model model) {
        ModelFragment fragment = new ModelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, model.getName());
        args.putString(ARG_DESCRIPTION, model.getDescription());
        args.putString(ARG_PAPERLINK, model.getPaperLink());
        args.putString(ARG_SOURCELINK, model.getSourceLink());
        args.putString(ARG_DOWNLOADLINK, model.getDownloadLink());
        args.putString(ARG_IMAGELINK, model.getImageLink());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mModel = new Model(getArguments().getString(ARG_NAME),
                    getArguments().getString(ARG_DESCRIPTION),
                    getArguments().getString(ARG_PAPERLINK),
                    getArguments().getString(ARG_SOURCELINK),
                    getArguments().getString(ARG_DOWNLOADLINK),
                    getArguments().getString(ARG_IMAGELINK));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View modelView = inflater.inflate(R.layout.fragment_model, container, false);
        Context context = modelView.getContext();


        return modelView;
    }
}
