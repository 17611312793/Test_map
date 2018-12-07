package com.zlt.test_map;

/**
 * Created by Lenovo on 2018-11-23.
 * 固定点：经纬度
 */

public class FixedPointBean {
    private double longitude;
    private double latitude;

    public FixedPointBean(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

}
