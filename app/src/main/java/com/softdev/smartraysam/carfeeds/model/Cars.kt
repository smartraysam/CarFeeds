package com.softdev.smartraysam.carfeeds.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Smart Raysam on 15-09-2018.
 */

class Cars {
    @SerializedName("placemarks")
    var carModel: List<carModel>? = null
        private set

    fun setCar(car: List<carModel>) {
        this.carModel = car
    }
}

