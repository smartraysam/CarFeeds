package com.softdev.smartraysam.carfeeds.model


import android.os.Parcel
import android.os.Parcelable

import java.util.ArrayList
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Smart Raysam on 15-09-2018.
 */

class carModel : Parcelable {
    @SerializedName("name")
    @Expose
    var name: String? = null
        private set
    @SerializedName("address")
    @Expose
    var address: String? = null
        private set
    @SerializedName("engineType")
    @Expose
    var engineType: String? = null
        private set
    @SerializedName("vin")
    @Expose
    var vin: String? = null
        private set
    @SerializedName("fuel")
    @Expose
    var fuel: Int = 0
        private set
    @SerializedName("exterior")
    @Expose
    var exterior: String? = null
        private set
    @SerializedName("interior")
    @Expose
    var interior: String? = null
        private set
    @SerializedName("coordinates")
    @Expose
    var coordinates: List<Double>
    private var id: Long = 0


    constructor(name: String, address: String, engineType: String, vin: String, exterior: String, interior: String, fuel: Int, coordinates: List<Double>) {
        this.name = name
        this.address = address
        this.engineType = engineType
        this.vin = vin
        this.exterior = exterior
        this.interior = interior
        this.fuel = fuel
        this.coordinates = coordinates


    }
    constructor(`in`: Parcel) {
        name = `in`.readString()
        address = `in`.readString()
        engineType = `in`.readString()
        vin = `in`.readString()
        fuel = `in`.readInt()
        exterior = `in`.readString()
        interior = `in`.readString()
        coordinates = ArrayList()
        `in`.readList(coordinates, null)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeString(this.name)
        dest.writeString(this.address)
        dest.writeString(this.engineType)
        dest.writeString(this.vin)
        dest.writeInt(this.fuel)
        dest.writeString(this.exterior)
        dest.writeString(this.interior)
        dest.writeList(this.coordinates)
    }

    companion object  CREATOR: Parcelable.Creator<carModel> {
            override fun createFromParcel(`in`: Parcel): carModel {
                return carModel(`in`)
            }

            override fun newArray(size: Int): Array<carModel?> {
                return arrayOfNulls(size)
            }

    }
}

