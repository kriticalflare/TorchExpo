package io.github.prabhuomkar.pytorchandroid.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.prabhuomkar.pytorchandroid.R;
import io.github.prabhuomkar.pytorchandroid.helpers.DownloadHelper;
import io.github.prabhuomkar.pytorchandroid.helpers.FileHelper;
import io.github.prabhuomkar.pytorchandroid.models.Model;
import io.github.prabhuomkar.pytorchandroid.playground.ImageClassificationActivity;

public class ModelListAdapter extends RecyclerView.Adapter<ModelListAdapter.ModelView> {

    private List<Model> modelList;
    private Context context;

    public ModelListAdapter(List<Model> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ModelListAdapter.ModelView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = (AppCompatActivity) parent.getContext();
        View modelItemView = LayoutInflater.from(context)
                .inflate(R.layout.list_model_item, parent, false);
        return new ModelListAdapter.ModelView(modelItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelListAdapter.ModelView holder, int position) {
        Model currentModel = modelList.get(position);
        holder.modelNameView.setText(currentModel.getName());
        holder.modelDescriptionView.setText(currentModel.getDescription());
        holder.modelPaperLinkView.setText(currentModel.getPaperLink());
        holder.modelSourceLinkView.setText(currentModel.getSourceLink());
        if (FileHelper.checkIfFileExists(FileHelper.getAssetModelFilePath(context,
                currentModel.getName()))) {
            holder.modelDownloadButton.setVisibility(View.GONE);
        }
        // TODO: Load model architecture images using Picasso
    }

    @Override
    public int getItemCount() {
        return modelList == null ? 0 : modelList.size();
    }


    public class ModelView extends RecyclerView.ViewHolder {

        RecyclerViewClickListener clickListener;
        TextView modelNameView, modelDescriptionView, modelPaperLinkView, modelSourceLinkView;
        ImageView modelImageView;
        Button modelDownloadButton, modelRunButton;

        public ModelView(@NonNull View itemView) {
            super(itemView);
            modelImageView = (ImageView) itemView
                    .findViewById(R.id.list_model_item_image);
            modelNameView = (TextView) itemView
                    .findViewById(R.id.list_model_item_name);
            modelDescriptionView = (TextView) itemView
                    .findViewById(R.id.list_model_item_description);
            modelPaperLinkView = (TextView) itemView
                    .findViewById(R.id.list_model_item_paperlink);
            modelSourceLinkView = (TextView) itemView
                    .findViewById(R.id.list_model_item_sourcelink);
            modelDownloadButton = (Button) itemView
                    .findViewById(R.id.list_model_item_download);
            modelRunButton = (Button) itemView
                    .findViewById(R.id.list_model_item_run);

            // Click listeners
            modelPaperLinkView.setOnClickListener(v -> context.startActivity(
                    new Intent(Intent.ACTION_VIEW, Uri.parse(
                            modelList.get(getAdapterPosition()).getPaperLink()))));
            modelSourceLinkView.setOnClickListener(v -> context.startActivity(
                    new Intent(Intent.ACTION_VIEW, Uri.parse(
                            modelList.get(getAdapterPosition()).getSourceLink()))));
            modelDownloadButton.setOnClickListener(v -> {
                String fileName = FileHelper.getAssetFileNameForModel(
                        modelList.get(getAdapterPosition()).getName());
                if (((Button) v).getText().equals("Cancel")) {
                    DownloadHelper.cancelDownload(fileName);
                } else {
                    DownloadHelper.downloadModel(context, itemView,
                            modelList.get(getAdapterPosition()).getDownloadLink(),
                            FileHelper.getDownloadDirPath(context),
                            fileName);
                }
            });
            modelRunButton.setOnClickListener(v -> {
                Intent playgroundIntent = new Intent(context, ImageClassificationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("modelName", modelList.get(getAdapterPosition()).getName());
                playgroundIntent.putExtras(bundle);
                context.startActivity(playgroundIntent);
            });
        }
    }
}
