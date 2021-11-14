/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author EG
 */
public class WeatherMainDTO {
    @SerializedName("temp")
    private double temperature;
    
    public double getTemperature() {
        return temperature;
    }
}
