/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

/**
 *
 * @author EG
 */
public class CityWeatherInfo {
    private Weather weather;
    private Country country;
    
    public CityWeatherInfo(Weather weather, Country country) {
        this.weather = weather;
        this.country = country;
    }
    
    
    //Bruger ikke Getter metoderne fordi JSON tager feltvaraiblerne i stedet
    public Weather getWeather () {
        return weather;
    }
    
    public Country getCountry () {
        return country;
    }
}
