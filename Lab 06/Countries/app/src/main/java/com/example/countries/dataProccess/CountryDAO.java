package com.example.countries.dataProccess;

public class CountryDAO
{
    private String rank;
    private String country;
    private String population;
    private String flag;

    public CountryDAO()
    {}
    public CountryDAO(String rank, String country, String population, String flag) {
        this.rank = rank;
        this.country = country;
        this.population = population;
        this.flag = flag;
    }

    public String getRank() {
        return rank;
    }

    public String getCountry() {
        return country;
    }

    public String getPopulation() {
        return population;
    }

    public String getFlag() {
        return flag;
    }
}

