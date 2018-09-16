package com.softdev.smartraysam.carfeeds.interactor

import android.content.Context
import android.util.Log

import com.google.gson.GsonBuilder
import com.softdev.smartraysam.carfeeds.contract.GetDataContract
import com.softdev.smartraysam.carfeeds.model.CarFeedsResponse
import com.softdev.smartraysam.carfeeds.model.Cars
import com.softdev.smartraysam.carfeeds.model.carModel

import java.util.ArrayList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Smart Raysam on 15-09-2018.
 */

class Intractor(private val mOnGetDatalistener: GetDataContract.onGetDataListener) : GetDataContract.Interactor {
    private var allcar: List<carModel>? = ArrayList()
    override fun initRetrofitCall(context: Context, url: String) {
        val gson = GsonBuilder()
                .setLenient()
                .create()
        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        val request = retrofit.create(CarFeedsResponse::class.java)
        val call = request.cars
        call.enqueue(object : retrofit2.Callback<Cars> {
            override fun onResponse(call: retrofit2.Call<Cars>, response: retrofit2.Response<Cars>) {
                val jsonResponse = response.body()
                Log.v("RES", response.body().toString())
                allcar = jsonResponse.carModel
                Log.d("Data", "Refreshed")
                mOnGetDatalistener.onSuccess("List Size: " + allcar!!.size, allcar!!)
            }

            override fun onFailure(call: retrofit2.Call<Cars>, t: Throwable) {
                Log.v("Error", t.message)
                mOnGetDatalistener.onFailure(t.message.toString())
            }
        })
    }
}
