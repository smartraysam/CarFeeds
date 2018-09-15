package com.softdev.smartraysam.carfeeds.Core;

import android.content.Context;

import com.softdev.smartraysam.carfeeds.model.carModel;

import java.util.List;





/**
 * Created by Smart Raysam on 15-09-2018.
 */

public class Presenter implements GetDataContract.Presenter, GetDataContract.onGetDataListener {
    private GetDataContract.View mGetDataView;
    private Intractor mIntractor;
    public Presenter(GetDataContract.View mGetDataView){
        this.mGetDataView = mGetDataView;
        mIntractor = new Intractor(this);
    }
    @Override
    public void getDataFromURL(Context context, String url, String msg) {
        mIntractor.initRetrofitCall(context,url);
        mGetDataView.onStart(msg);
    }

    @Override
    public void onSuccess(String message, List<carModel> allData) {
        mGetDataView.onGetDataSuccess(message, allData);
    }

    @Override
    public void onFailure(String message) {
        mGetDataView.onGetDataFailure(message);
    }
}
