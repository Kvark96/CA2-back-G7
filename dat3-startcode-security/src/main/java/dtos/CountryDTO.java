/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author EG
 */
public class CountryDTO {
    private CountryNameDTO name;
    private int population;
    private String[] continents;
    private String[] capital;


    public String getName() {
        return name.getCommon();
    }

    public String getOfficialName() {
        return name.getOfficial();
    }

    public int getPopulation() {
        return population;
    }

    public String[] getContinents() {
        return continents;
    }

    public String[] getCapital() {
        return capital;
    }
}
