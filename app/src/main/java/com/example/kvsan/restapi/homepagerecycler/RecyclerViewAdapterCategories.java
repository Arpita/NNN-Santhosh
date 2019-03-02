package com.example.kvsan.restapi.homepagerecycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kvsan.restapi.R;
import com.example.kvsan.restapi.datamodel.DataModel;
import com.example.kvsan.restapi.secondscreen.VideoActivity;

import java.util.List;


public class RecyclerViewAdapterCategories extends RecyclerView.Adapter<RecyclerViewAdapterCategories.CategoriesViewHolder> {
    private Context context;
    private List<DataModel> dataModelList;

    public RecyclerViewAdapterCategories(Context context, List<DataModel> dataModelList) {
        this.context = context;
        this.dataModelList = dataModelList;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorieslayout, parent, false);
        CategoriesViewHolder scheduledVisitViewHolder = new CategoriesViewHolder(view);
        return scheduledVisitViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapterCategories.CategoriesViewHolder holder, final int position) {
        final DataModel dataModel = dataModelList.get(position);
        holder.name.setText(dataModel.getCategoryName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra("categoryname", dataModel.getCategoryName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public CategoriesViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.categorytvid);
        }
    }
}

