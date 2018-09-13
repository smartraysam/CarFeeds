package com.softdev.smartraysam.carfeeds.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdev.smartraysam.carfeeds.R;
import com.softdev.smartraysam.carfeeds.model.carModel;
import com.softdev.smartraysam.carfeeds.view.FeedsViewHolder;

import java.util.ArrayList;

public class FeedRecycleViewAdapter extends RecyclerView.Adapter<FeedsViewHolder> {
    private LayoutInflater inflater;
    public  ArrayList<carModel>feedLists;
    private Context ctx;
    OnItemClickListener listener;

    public interface OnItemClickListener {
        public void onItemClick(View v, int position);
    }

    public FeedRecycleViewAdapter(Context ctx,  ArrayList<carModel> feedLists, OnItemClickListener listener) {
        inflater = LayoutInflater.from(ctx);
        this.feedLists =feedLists;
        this.listener=listener;
        this.ctx = ctx;
    }


    @Override
    public FeedsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.car_items, parent, false);
        final FeedsViewHolder holder = new FeedsViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final FeedsViewHolder holder, final int position) {
        carModel cModel = feedLists.get(position);
            holder.mName.setText(cModel.getName());
            holder.mAddress.setText(Html.fromHtml("<b>Address:</b> "+cModel.getAddress()));
            holder.mEngine.setText(Html.fromHtml("<b>Engine Type:</b> "+cModel.getEngineType()));
            holder.mVin.setText(Html.fromHtml("<b>VIN:</b> "+cModel.getVin()));
            holder.mFuel.setText(Html.fromHtml("<b>Fuel:</b> "+String.valueOf(cModel.getFuel())));
            holder.mExt.setText(Html.fromHtml("<b>Exterior:</b> "+cModel.getExterior()));
            holder.mInt.setText(Html.fromHtml("<b>Interior:</b> "+cModel.getInterior()));
            holder.mXcoord.setText(Html.fromHtml("<b>Coordinate:</b> "+String .valueOf(cModel.getCoordinateX())));
            holder.mYcoord.setText(String.valueOf(cModel.getCoordinateY()));

    }
    @Override
    public int getItemCount() {
        return feedLists.size();
    }

}
