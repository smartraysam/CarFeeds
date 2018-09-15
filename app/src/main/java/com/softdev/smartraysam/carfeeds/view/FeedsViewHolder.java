package com.softdev.smartraysam.carfeeds.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.softdev.smartraysam.carfeeds.R;
public class FeedsViewHolder extends RecyclerView.ViewHolder {
    public TextView mName,mAddress,mEngine,mFuel,mVin,mExt,mInt,mXcoord,mYcoord ,mZcoord;
    public FeedsViewHolder(View v){
        super(v);
        this.mAddress =v.findViewById(R.id.textAddress);
        this.mEngine = v.findViewById(R.id.textEnginetyep);
        this.mFuel =v.findViewById(R.id.textFuel);
        this.mVin = v.findViewById(R.id.textVin);
        this.mName = v.findViewById(R.id.textName);
        this.mExt = v.findViewById(R.id.textExterior);
        this.mInt = v.findViewById(R.id.textInterior);
        this.mXcoord = v.findViewById(R.id.textXCoodinate);
        this.mYcoord = v.findViewById(R.id.textYCoordinate);
        this.mZcoord = v.findViewById(R.id.textZCoordinate);
    }
}
