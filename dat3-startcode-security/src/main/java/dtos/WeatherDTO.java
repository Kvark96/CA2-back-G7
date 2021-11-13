/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author peter
 */
public class WeatherDTO {
    
    private String temperature;
    //private String wind;
    private String name;
    private String id;
    private String timezone;
    private String country;

    public String getCountry() {
        return country;
    }
}
