package com.softdev.smartraysam.carfeeds.util;

import android.view.View;

import com.softdev.smartraysam.carfeeds.model.Cars;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by Smart Raysam on 15-09-2018.
 */
public interface OnItemClickListener {
    void onItemClick(View v, int position);
}
