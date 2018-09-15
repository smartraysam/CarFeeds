package com.softdev.smartraysam.carfeeds.Core;

import android.content.Context;

import com.softdev.smartraysam.carfeeds.model.carModel;

import java.util.List;



/**
 * Created by Smart Raysam on 15-09-2018.
 */

public interface GetDataContract {
    interface View{
        void onGetDataSuccess(String message, List<carModel> list);
        void onGetDataFailure(String message);
        void onStart(String message);
    }
    interface Presenter{
        void getDataFromURL(Context context, String url, String msg);
    }
    interface Interactor{
        void initRetrofitCall(Context context, String url);

    }
    interface onGetDataListener{
        void onSuccess(String message, List<carModel> list);
        void onFailure(String message);
    }
}
