package facades;

import callables.ApiFetchCallable;
import com.google.gson.Gson;
import dtos.WeatherDTO;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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


    public List<String> getDataFromTwoServers(String city) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        String _city = city.substring(5);
        String weatherHost = "https://api.openweathermap.org/data/2.5/weather?q=" + _city + "&units=metric&appid=285a3bfd0ce31b7279715d65d5d6894d";

        List<String> data = new ArrayList<>();

        Future<String> weatherFuture = executor.submit(new ApiFetchCallable(weatherHost));
        data.add(weatherFuture.get());

        WeatherDTO weatherInfo = gson.fromJson(weatherFuture.get(), WeatherDTO.class);
/*
        String countryName = weatherInfo.getCountry();
        String countryHost = "https://restcountries.com/v3.1/alpha/" + countryName;

        Future<String> cityFuture = executor.submit(new ApiFetchCallable(countryHost));
        data.add(cityFuture.get());
*/
        return data;
    }

}
