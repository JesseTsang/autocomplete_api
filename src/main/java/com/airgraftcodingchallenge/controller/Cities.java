package com.airgraftcodingchallenge.controller;

import java.util.ArrayList;

import com.airgraftcodingchallenge.model.City;

public class Cities {
    public ArrayList<City> suggestion = new ArrayList<City>();
	
	public Cities(String queryCityName, double latitude, double longitude) {
		CityRepository cityRepo = new CityRepository(queryCityName, latitude, longitude);
	
		suggestion = cityRepo.getSuggestions();
	}
}
