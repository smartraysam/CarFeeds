package com.softdev.smartraysam.carfeeds.Core;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softdev.smartraysam.carfeeds.model.CarFeedsResponse;
import com.softdev.smartraysam.carfeeds.model.Cars;
import com.softdev.smartraysam.carfeeds.model.carModel;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Smart Raysam on 15-09-2018.
 */

public class Intractor implements GetDataContract.Interactor{
    private GetDataContract.onGetDataListener mOnGetDatalistener;
   private List<carModel> allcar= new ArrayList<>();

    public Intractor(GetDataContract.onGetDataListener mOnGetDatalistener){
        this.mOnGetDatalistener = mOnGetDatalistener;
    }
    @Override
    public void initRetrofitCall(Context context, String url) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        CarFeedsResponse request = retrofit.create(CarFeedsResponse.class);
        retrofit2.Call<Cars> call = request.getCars();
        call.enqueue(new retrofit2.Callback<Cars>() {
            @Override
            public void onResponse(retrofit2.Call<Cars> call, retrofit2.Response<Cars> response) {
               Cars jsonResponse = response.body();
                Log.v("RES",response.body().toString());
                allcar = jsonResponse.getCarModel();
                Log.d("Data", "Refreshed");
                mOnGetDatalistener.onSuccess("List Size: " + allcar.size(), allcar);
            }
            @Override
            public void onFailure(retrofit2.Call<Cars> call, Throwable t) {
                Log.v("Error",t.getMessage());
                mOnGetDatalistener.onFailure(t.getMessage());
            }
        });
    }
}
