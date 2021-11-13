/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.UserDTO;
import dtos.WeatherDTO;
import facades.ServerFacade;
import facades.UserFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed("user")
    public String getWeather(String city) {
        try {
            List<String> wDTO = FACADE.getDataFromTwoServers(city);
            return GSON.toJson(wDTO);
        } catch (Exception e) {
            return GSON.toJson(e);
        }
    }
}