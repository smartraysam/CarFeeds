package com.softdev.smartraysam.carfeeds.Presenter

import android.content.Context
import com.softdev.smartraysam.carfeeds.contract.GetDataContract
import com.softdev.smartraysam.carfeeds.interactor.Intractor

import com.softdev.smartraysam.carfeeds.model.carModel

/**
 * Created by Smart Raysam on 15-09-2018.
 */

class Presenter(private val mGetDataView: GetDataContract.View) : GetDataContract.Presenter, GetDataContract.onGetDataListener {
    private val mIntractor: Intractor

    init {
        mIntractor = Intractor(this)
    }

    override fun getDataFromURL(context: Context, url: String, msg: String) {
        mIntractor.initRetrofitCall(context, url)
        mGetDataView.onStart(msg)
    }

    override fun onSuccess(message: String, allData: List<carModel>) {
        mGetDataView.onGetDataSuccess(message, allData)

    }

    override fun onFailure(message: String) {
        mGetDataView.onGetDataFailure(message)
    }
}
