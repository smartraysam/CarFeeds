package com.softdev.smartraysam.carfeeds.contract

import android.content.Context

import com.softdev.smartraysam.carfeeds.model.carModel


/**
 * Created by Smart Raysam on 15-09-2018.
 */

interface GetDataContract {
    interface View {
        fun onGetDataSuccess(message: String, list: List<carModel>)
        fun onGetDataFailure(message: String)
        fun onStart(message: String)
    }

    interface Presenter {
        fun getDataFromURL(context: Context, url: String, msg: String)
    }

    interface Interactor {
        fun initRetrofitCall(context: Context, url: String)

    }

    interface onGetDataListener {
        fun onSuccess(message: String, list: List<carModel>)
        fun onFailure(message: String)
    }
}
