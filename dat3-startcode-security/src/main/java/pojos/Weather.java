/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import dtos.WeatherDTO;

/**
 *
 * @author EG
 */
public class Weather {
    private int id;
    private String city;
    private String countryCode;
    private int timezone;
    private double temperature;
    
    public Weather(int id, String city, String countryCode, int timezone, double temperature) {
        this.id = id;
        this.city = city;
        this.countryCode = countryCode;
        this.timezone = timezone;
        this.temperature = temperature;
    }
    
    public int getId() {
        return id;
    }
    
    public String getCity() {
        return city;
    }
    
    public String getCountryCode() {
        return countryCode;
    }
    
    public int getTimezone() {
        return timezone;
    }
    
    public double getTemperature() {
        return temperature;
    }
    
    public static Weather fromDTO(WeatherDTO dto) {
        return new Weather(dto.getId(), dto.getCity(), dto.getCountryCode(), dto.getTimezone(), dto.getTemperature());
    }
}
