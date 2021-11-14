package facades;

import callables.ApiFetchCallable;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import pojos.CityWeatherInfo;
import dtos.CountryDTO;
import dtos.WeatherDTO;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.json.JsonObject;
import pojos.Country;
import pojos.Weather;

public class ServerFacade {


    private static EntityManagerFactory emf;
    private static ServerFacade instance;
    private Gson gson = new Gson();

    private ServerFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static ServerFacade getServerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ServerFacade();
        }
        return instance;
    }


    public CityWeatherInfo getDataFromTwoServers(String city) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool(); // Laver en ny "Thread Pool". Et objekt med en masse tråde, i som du kan gøre brug af for at køre din kode asynkront.

        String weatherHost = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=285a3bfd0ce31b7279715d65d5d6894d"; // Laver vores URL ud fra byen.
        String cityWeatherData = executor.submit(new ApiFetchCallable(weatherHost, "GET")).get(); // Laver et GET request til det ovenstående URL og venter på et svar. (Svaret er en JSON String)
        WeatherDTO weatherInfo = gson.fromJson(cityWeatherData, WeatherDTO.class); // Konverterer vores JSON string, som vi fik fra ovenstående metode til en WeatherDTO.
        
        String countryHost = "https://restcountries.com/v3.1/alpha/" + weatherInfo.getCountryCode(); // Laver vores URL ud fra den "countryCode" vi for fra vores WeatherDTO, som vi fik fra den anden API.
        String countryData = executor.submit(new ApiFetchCallable(countryHost, "GET")).get(); // Laver et GET request til ovenstående URL og venter på et svar. (Svaret er en JSON String)
        JsonArray countries = gson.fromJson(countryData, JsonArray.class); // Den JSON string vi fik ovenfra, er faktisk et JSON Array, med alle de lande, som har den samme lande kode.
        CountryDTO countryInfo = gson.fromJson(countries.get(0), CountryDTO.class); // Vi gå naivt ud fra at der kun findes et land med det samme lande kode, derfor tager vi bare det første land fra vores JsonArray og konvereterer landet (Som nu er et JsonObject) til CountryDTO.
        
        return new CityWeatherInfo(Weather.fromDTO(weatherInfo), Country.fromDTO(countryInfo)); // Vi returnerer her en POJO med vores weatherInfo og countryInfo. Denne POJO har en mere simpel struktur end vores DTOer, hvilket er derfor den bliver brugt i stedet.
    }

}
