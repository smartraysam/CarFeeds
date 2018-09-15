package com.softdev.smartraysam.carfeeds.model;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ashish on 06-02-2017.
 */

public interface CarFeedsResponse {
    @GET("/wunderbucket/locations.json")
    Call<Cars> getCars();
}
