package com.example.countrylistview.Fragments;

import com.example.countrylistview.dataProccess.CountryDAO;

public interface Communicator
{
    public void sendData(CountryDAO countryDAO);
}
