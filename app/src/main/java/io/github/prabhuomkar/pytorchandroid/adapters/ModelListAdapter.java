package io.github.prabhuomkar.pytorchandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

        // clickListener implements the action to switch to specific action related activity
        RecyclerViewClickListener clickListener = (view, position) -> {
            // Switch activity
        };

        return new ModelListAdapter.ModelView(modelItemView, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelListAdapter.ModelView holder, int position) {
        Model currentModel = modelList.get(position);
        holder.modelNameView.setText(currentModel.getName());
        holder.modelDescriptionView.setText(currentModel.getDescription());
        holder.modelPaperLinkView.setText(currentModel.getPaperLink());
        holder.modelSourceLinkView.setText(currentModel.getSourceLink());
    }

    @Override
    public int getItemCount() {
        return modelList == null ? 0 : modelList.size();
    }


    public class ModelView extends RecyclerView.ViewHolder implements View.OnClickListener {

        RecyclerViewClickListener clickListener;
        TextView modelNameView, modelDescriptionView, modelPaperLinkView, modelSourceLinkView;
        ImageView modelImageView;

        public ModelView(@NonNull View itemView, RecyclerViewClickListener listener) {
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
            clickListener = listener;
            itemView.setOnClickListener(this);
            modelPaperLinkView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(modelList.get(getAdapterPosition()).getPaperLink())));
                }
            });
            modelSourceLinkView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(modelList.get(getAdapterPosition()).getSourceLink())));
                }
            });
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }
}
