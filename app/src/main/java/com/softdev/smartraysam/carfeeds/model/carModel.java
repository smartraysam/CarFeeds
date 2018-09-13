package com.softdev.smartraysam.carfeeds.model;


import java.io.Serializable;

public class carModel  implements Serializable {
    private String name;
    private String address;
    private String engineType;
    private String vin;
    private int fuel;
    private String exterior;
    private String interior;
    private Double coordinateX;
    private Double coordinateY;
    private Long id;


    public void setAddress(String address) {
        this.address= address;
    }
    public String getAddress() {
        return  address;
    }

    public void setCoordinateX(Double coordinateX) {
        this.coordinateX=  coordinateX;
    }
    public Double getCoordinateX() {
        return  coordinateX;
    }

    public void setID(Long id) {
        this.id=id;
    }
    public Long  getID() {
        return  id;
    }


    public void setCoordinateY(Double coordinateY) {
        this.coordinateY=  coordinateY;
    }
    public Double getCoordinateY() {
        return  coordinateY;
    }

    public void setEngineType(String engineType) {
        this.engineType= engineType;
    }
    public String getEngineType() {
        return  engineType;
    }
    public void setExterior(String exterior) {
        this.exterior= exterior;
    }
    public String getExterior() {
        return  exterior;
    }

    public void setInterior(String interior) {
        this.interior=interior;
    }
    public String getInterior() {
        return  interior;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }


    public void setFuel(int fuel) {
        this.fuel=fuel;
    }

    public int getFuel() {
        return fuel;
    }

    public void setVin(String vin) {
        this.vin=vin;
    }
    public String getVin() {
        return vin;
    }


}
