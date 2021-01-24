package com.airgraftcodingchallenge.controller;

import java.util.ArrayList;

import com.airgraftcodingchallenge.model.City;

public class CitySearchPercentageCloseness implements Search{
	private static final double NAME_LENGTH_TREHSOLD = 0.5;
	
	public CitySearchPercentageCloseness() {}
	
	public ArrayList<City> search(ArrayList<City> cities,String cityToSearch){
		ArrayList<City> potentialCityList = new ArrayList<City>();
		double percentNameMatch = 0.0;
		double percentNameASCIIMatch = 0.0;
		
		//Filter all cities for names that matches at least 50% of cityToSearch name length
		for(City city : cities) {
			String cityName = city.getCityName().toLowerCase();
			String cityNameASCII = city.getcityNameASCII().toLowerCase();

			cityToSearch = cityToSearch.toLowerCase();

			if (cityName.contains(cityToSearch) || cityNameASCII.contains(cityToSearch)){
				percentNameMatch = ((double)cityToSearch.length() / (double)cityName.length());
				percentNameASCIIMatch = ((double)cityToSearch.length() / (double)cityNameASCII.length());
				
				if (percentNameMatch >= NAME_LENGTH_TREHSOLD || percentNameASCIIMatch >= NAME_LENGTH_TREHSOLD) {
					potentialCityList.add(city); 
				}
			}
		}
		return potentialCityList;		
	}
}