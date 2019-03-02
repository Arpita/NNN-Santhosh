package com.example.kvsan.restapi.secondscreen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kvsan.restapi.R;
import com.example.kvsan.restapi.datamodel.DataModel;

import java.util.List;


public class RecyclerViewAdapterVideos extends RecyclerView.Adapter<RecyclerViewAdapterVideos.CategoriesViewHolder> {
    private Context context;
    private List<DataModel> dataModelList;

    public RecyclerViewAdapterVideos(Context context, List<DataModel> dataModelList) {
        this.context = context;
        this.dataModelList = dataModelList;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.videoslayout, parent, false);
        CategoriesViewHolder scheduledVisitViewHolder = new CategoriesViewHolder(view);
        return scheduledVisitViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapterVideos.CategoriesViewHolder holder, final int position) {
        final DataModel dataModel = dataModelList.get(position);
        holder.name.setText(dataModel.getCategoryName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

