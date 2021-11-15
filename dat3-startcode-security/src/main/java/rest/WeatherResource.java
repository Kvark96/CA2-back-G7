/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pojos.CityWeatherInfo;
import dtos.UserDTO;
import dtos.WeatherDTO;
import facades.ServerFacade;
import facades.UserFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;

/**
 *
 * @author peter
 */
@Path("weather")
public class WeatherResource {
    
    //@Context
    //SecurityContext securityContext;

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final ServerFacade FACADE =  ServerFacade.getServerFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed("user")
    // GET request = @QueryParam
    // POST request = @FormParam
    public String getWeather(@QueryParam("city") String city) {
        try {
            CityWeatherInfo cityWeatherInfo = FACADE.getDataFromTwoServers(city);

            return GSON.toJson(cityWeatherInfo);
        } catch (Exception e) {
            return GSON.toJson(e);
        }
    }
}