package com.softdev.smartraysam.carfeeds.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import com.softdev.smartraysam.carfeeds.R

class FeedsViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var mName: TextView
    var mAddress: TextView
    var mEngine: TextView
    var mFuel: TextView
    var mVin: TextView
    var mExt: TextView
    var mInt: TextView
    var mXcoord: TextView
    var mYcoord: TextView
    var mZcoord: TextView

    init {
        this.mAddress = v.findViewById(R.id.textAddress)
        this.mEngine = v.findViewById(R.id.textEnginetyep)
        this.mFuel = v.findViewById(R.id.textFuel)
        this.mVin = v.findViewById(R.id.textVin)
        this.mName = v.findViewById(R.id.textName)
        this.mExt = v.findViewById(R.id.textExterior)
        this.mInt = v.findViewById(R.id.textInterior)
        this.mXcoord = v.findViewById(R.id.textXCoodinate)
        this.mYcoord = v.findViewById(R.id.textYCoordinate)
        this.mZcoord = v.findViewById(R.id.textZCoordinate)
    }
}
