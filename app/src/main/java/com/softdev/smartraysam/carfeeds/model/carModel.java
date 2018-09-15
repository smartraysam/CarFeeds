package com.softdev.smartraysam.carfeeds.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Smart Raysam on 15-09-2018.
 */

public class carModel implements Parcelable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("engineType")
    @Expose
    private String engineType;
    @SerializedName("vin")
    @Expose
    private String vin;
    @SerializedName("fuel")
    @Expose
    private int fuel;
    @SerializedName("exterior")
    @Expose
    private String exterior;
    @SerializedName("interior")
    @Expose
    private String interior;
    @SerializedName("coordinates")
    @Expose
    public List<Double> coordinates;
    private long id;



    public carModel(long id, String name, String address, String engineType, String vin, String exterior, String interior, int fuel, List<Double>coordinates) {
        this.id=id;
        this.name =name;
        this.address = address;
        this.engineType = engineType;
        this.vin=vin;
        this.exterior =exterior;
        this.interior=interior;
        this.fuel=fuel;
        this.coordinates=coordinates;


    }

    public String getAddress() {
        return address;
    }
    public Long getID() {
        return id;
    }

    public String getEngineType() {
        return engineType;
    }

    public String getExterior() {
        return exterior;
    }

    public String getInterior() {
        return interior;
    }

    public String getName() {
        return name;
    }
    public  List<Double> getCoordinates(){
        return coordinates;
    }
    public int getFuel() {
        return fuel;
    }
    public String getVin() {
        return vin;
    }

    public carModel(Parcel in) {
        id = in.readLong();
        name = in.readString();
        address = in.readString();
        engineType = in.readString();
        vin = in.readString();
        fuel = in.readInt();
        exterior = in.readString();
        interior = in.readString();
        coordinates= new ArrayList<Double>();
        in.readList(coordinates,null);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeString(this.engineType);
        dest.writeString(this.vin);
        dest.writeInt(this.fuel);
        dest.writeString(this.exterior);
        dest.writeString(this.interior);
        dest.writeList(this.coordinates);
    }

    public static final Creator<carModel> CREATOR = new Creator<carModel>() {
        @Override
        public carModel createFromParcel(Parcel in) {
            return new carModel(in);
        }

        @Override
        public carModel[] newArray(int size) {
            return new carModel[size];
        }
    };
}

