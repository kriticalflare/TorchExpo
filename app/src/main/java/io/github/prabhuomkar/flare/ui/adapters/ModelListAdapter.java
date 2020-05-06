package io.github.prabhuomkar.flare.ui.adapters;

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

import io.github.prabhuomkar.flare.Constants;
import io.github.prabhuomkar.flare.R;
import io.github.prabhuomkar.flare.helpers.DataHelper;
import io.github.prabhuomkar.flare.helpers.DownloadHelper;
import io.github.prabhuomkar.flare.helpers.FileHelper;
import io.github.prabhuomkar.flare.helpers.UIHelper;
import io.github.prabhuomkar.flare.models.Model;

public class ModelListAdapter extends RecyclerView.Adapter<ModelListAdapter.ModelView> {

    private String taskName;
    private List<Model> modelList;
    private Context context;

    public ModelListAdapter(String taskName, List<Model> modelList) {
        this.taskName = taskName;
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
        if (currentModel.getPaperLink().equals(""))
            holder.modelPaperLinkView.setVisibility(View.GONE);
        holder.modelSourceLinkView.setText(currentModel.getSourceLink());
        if (currentModel.getSourceLink().equals(""))
            holder.modelSourceLinkView.setVisibility(View.GONE);
        holder.modelSizeView.setText(context.getString(R.string.model_size,
                currentModel.getSize()));
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
        TextView modelNameView, modelDescriptionView, modelPaperLinkView, modelSourceLinkView,
                modelSizeView;
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
            modelSizeView = (TextView) itemView
                    .findViewById(R.id.list_model_item_size);
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
                String buttonText = ((Button) v).getText().toString();
                if (buttonText.equals(Constants.BUTTON_STATE_DOWNLOAD)) {
                    UIHelper.updateModelDownloadButton(itemView, Constants.BUTTON_STATE_DOWNLOADING);
                    DownloadHelper.downloadModel(context, itemView,
                            modelList.get(getAdapterPosition()).getDownloadLink(),
                            FileHelper.getDownloadDirPath(context),
                            fileName);
                } else if (buttonText.equals(Constants.BUTTON_STATE_CANCEL)) {
                    DownloadHelper.cancelDownload(fileName);
                }
            });
            modelRunButton.setOnClickListener(v -> {
                Intent playgroundIntent = DataHelper.getRunnerIntentForModel(context,
                        modelList.get(getAdapterPosition()).getPlaygroundActivity());
                Bundle bundle = new Bundle();
                bundle.putString("modelName", modelList.get(getAdapterPosition()).getName());
                playgroundIntent.putExtras(bundle);
                context.startActivity(playgroundIntent);
            });
        }
    }
}
