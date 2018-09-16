package com.softdev.smartraysam.carfeeds.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup

import com.softdev.smartraysam.carfeeds.R
import com.softdev.smartraysam.carfeeds.model.carModel
import com.softdev.smartraysam.carfeeds.util.OnItemClickListener
import com.softdev.smartraysam.carfeeds.view.FeedsViewHolder


class FeedRecycleViewAdapter(private val ctx: Context, var feedLists: List<carModel>, internal var listener: OnItemClickListener) : RecyclerView.Adapter<FeedsViewHolder>() {
    private val inflater: LayoutInflater
    init {
        inflater = LayoutInflater.from(ctx)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsViewHolder {
        val view = inflater.inflate(R.layout.car_items, parent, false)
        val holder = FeedsViewHolder(view)
        view.setOnClickListener { v -> listener.onItemClick(v, holder.adapterPosition) }
        return holder
    }

    override fun onBindViewHolder(holder: FeedsViewHolder, position: Int) {
        val cModel = feedLists[position]
        holder.mName.text = cModel.name
        holder.mAddress.text = Html.fromHtml("<b>Address:</b> " + cModel.address)
        holder.mEngine.text = Html.fromHtml("<b>Engine Type:</b> " + cModel.engineType)
        holder.mVin.text = Html.fromHtml("<b>VIN:</b> " + cModel.vin)
        holder.mFuel.text = Html.fromHtml("<b>Fuel:</b> " + cModel.fuel.toString())
        holder.mExt.text = Html.fromHtml("<b>Exterior:</b> " + cModel.exterior)
        holder.mInt.text = Html.fromHtml("<b>Interior:</b> " + cModel.interior)
        holder.mXcoord.text = Html.fromHtml("<b>Coordinate:</b> " + cModel.coordinates[0].toString())
        holder.mYcoord.text = cModel.coordinates[1].toString()
        holder.mZcoord.text = cModel.coordinates[2].toString()
    }

    override fun getItemCount(): Int {
        return feedLists.size
    }

}
