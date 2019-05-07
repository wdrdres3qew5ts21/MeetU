/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.model;

import org.elasticsearch.common.geo.GeoPoint;

/**
 *
 * @author wdrdr
 */
public class Location {

    private String detail;

    private String subDistrict;

    private String district;

    private String province;

    private String country;
    
    private GeoDetail geopoint;

    public String getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public GeoDetail getGeopoint() {
        return geopoint;
    }

    public void setGeopoint(GeoDetail geopoint) {
        this.geopoint = geopoint;
    }

    @Override
    public String toString() {
        return "Location{" + "detail=" + detail + ", subDistrict=" + subDistrict + ", district=" + district + ", province=" + province + ", country=" + country + ", geopoint=" + geopoint + '}';
    }
    
   

}
