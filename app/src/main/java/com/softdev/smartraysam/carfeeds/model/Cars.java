package com.softdev.smartraysam.carfeeds.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;



/**
 * Created by Smart Raysam on 15-09-2018.
 */

public class Cars {
    @SerializedName("placemarks")
    private List<carModel> car = null;

    public List<carModel> getCarModel() {
            return car;
        }
        public void setCar(List<carModel> car) {
            this.car = car;
        }
    }

