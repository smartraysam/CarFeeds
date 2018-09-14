package com.softdev.smartraysam.carfeeds.model;


import android.os.Parcel;
import android.os.Parcelable;
public class carModel implements Parcelable {
    private String name;
    private String address;
    private String engineType;
    private String vin;
    private int fuel;
    private String exterior;
    private String interior;
    private double coordinateX;
    private double coordinateY;
    private long id;


    public carModel(long id, String name, String address,String engineType, String vin, String exterior, String interior, int fuel, double coordinateX, double coordinateY) {
        this.id=id;
        this.name =name;
        this.address = address;
        this.engineType = engineType;
        this.vin=vin;
        this.exterior =exterior;
        this.interior=interior;
        this.fuel=fuel;
        this.coordinateX=coordinateX;
        this.coordinateY=coordinateY;


    }


    public String getAddress() {
        return address;
    }
    public Double getCoordinateX() {
        return coordinateX;
    }
    public Long getID() {
        return id;
    }
    public Double getCoordinateY() {
        return coordinateY;
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

    public void setName(String name) {
        this.name = name;
    }

    public int getFuel() {
        return fuel;
    }
    public String getVin() {
        return vin;
    }

   public  carModel (Parcel in) {
        id = in.readLong();
        name = in.readString();
        address = in.readString();
        engineType = in.readString();
        vin = in.readString();
        fuel = in.readInt();
        exterior = in.readString();
        interior = in.readString();
        coordinateX = in.readDouble();
        coordinateY = in.readDouble();
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
        dest.writeDouble(this.coordinateX);
        dest.writeDouble(this.coordinateY);

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

