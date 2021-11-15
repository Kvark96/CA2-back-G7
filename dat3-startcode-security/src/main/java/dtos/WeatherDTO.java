/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author peter
 */
public class WeatherDTO {
    private int id;
    private String name;
    private int timezone;
    
    private WeatherMainDTO main;
    private WeatherSysDTO sys;

    public int getId() {
        return id;
    }


    
    public String getCity() {
        return name;
    }
    
    public String getCountryCode() {
        return sys.getCountry();
    }
    
    public int getTimezone() {
        return timezone;
    }
    
    public double getTemperature() {
        return main.getTemperature();
    }
}
