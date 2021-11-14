/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import dtos.CountryDTO;

/**
 *
 * @author EG
 */
public class Country {
    private String name;
    private String officialName;
    private int population;
    private String[] continents;
    private String []capital;
    
    public Country(String name, String officialName, int population, String[] continents, String[] capital) {
        this.name = name;
        this.officialName = officialName;
        this.population = population;
        this.continents = continents;
        this.capital = capital;
    }
    
    public String getName() {
        return name;
    }
    
    public String getOfficialName() {
        return officialName;
    }
    
    public int getPopulation() {
        return population;
    }
    
    public String[] getContinents() {
        return continents;
    }
    
    public static Country fromDTO(CountryDTO dto) {
        return new Country(dto.getName(), dto.getOfficialName(), dto.getPopulation(), dto.getContinents(), dto.getCapital());
    }
}
