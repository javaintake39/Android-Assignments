package com.example.countrylistview.dataProccess;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {

    ArrayList<CountryDAO> data;

    public ArrayList<CountryDAO> JsonProcess(String jsonFile) {

        data = new ArrayList<>();
        try {
            JSONObject mainObject = new JSONObject(jsonFile);
            JSONArray jsonArray = mainObject.getJSONArray("worldpopulation");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject mainObjectArray = jsonArray.getJSONObject(i);

                CountryDAO encap = new CountryDAO
                        (
                                mainObjectArray.getString(KeyTags.rankKey),
                                mainObjectArray.getString(KeyTags.countryKey),
                                mainObjectArray.getString(KeyTags.populationKey),
                                mainObjectArray.getString(KeyTags.flagKey)
                        );
                data.add(encap);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<CountryDAO> getlist()
    {
        return data;
    }
}