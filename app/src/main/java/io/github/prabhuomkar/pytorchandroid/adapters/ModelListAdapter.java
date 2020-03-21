package io.github.prabhuomkar.pytorchandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.prabhuomkar.pytorchandroid.R;
import io.github.prabhuomkar.pytorchandroid.models.Model;

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
        Button modelDownloadButton;

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
            modelPaperLinkView.setOnClickListener(v -> context.startActivity(
                    new Intent(Intent.ACTION_VIEW,
                            Uri.parse(modelList.get(getAdapterPosition()).getPaperLink()))));
            modelSourceLinkView.setOnClickListener(v -> context.startActivity(
                    new Intent(Intent.ACTION_VIEW,
                            Uri.parse(modelList.get(getAdapterPosition()).getSourceLink()))));
            modelDownloadButton.setOnClickListener(v ->
                    Toast.makeText(context, "Downloading Model", Toast.LENGTH_SHORT).show());
        }
    }
}
