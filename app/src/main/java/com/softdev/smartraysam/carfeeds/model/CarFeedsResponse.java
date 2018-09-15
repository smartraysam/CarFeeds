package com.softdev.smartraysam.carfeeds.model;

import retrofit2.Call;
import retrofit2.http.GET;



/**
 * Created by Smart Raysam on 15-09-2018.
 */

public interface CarFeedsResponse {
    @GET("/wunderbucket/locations.json")
    Call<Cars> getCars();
}
