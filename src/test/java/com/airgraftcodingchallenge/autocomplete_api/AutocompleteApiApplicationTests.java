package com.airgraftcodingchallenge.autocomplete_api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import com.airgraftcodingchallenge.controller.CityRepository;
import com.airgraftcodingchallenge.model.City;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AutocompleteApiApplicationTests {
	
	@Test
	public void testCitySearchNotInList() {
		
		String testName 		= "Tatooin";
		
		CityRepository cityRepo = new CityRepository(testName,0,0);
		
		ArrayList<City> citiesFound = new ArrayList<City>(); 
		citiesFound = cityRepo.getSuggestions();
		
		int foundCity = citiesFound.size();
		int expected = 0;
		assertEquals(expected, foundCity);
		
	}
	
	@Test
	public void testCitySearchNameOnly() {
		String testName	= "Zaranj";
		double expectedScore = 1;
		CityRepository cityRepo = new CityRepository(testName, 0, 0);
		
		ArrayList<City> citiesFound = new ArrayList<City>(); 
		citiesFound = cityRepo.getSuggestions();
		
		String foundCityName = citiesFound.get(0).getCityName();
		double foundScore = citiesFound.get(0).getScore();
		
		assertEquals(testName, foundCityName);
		assertEquals(expectedScore, foundScore, 0);		
	}
	
	@Test
	public void testCitySearchNameLatitudeAndLongitude() {
		String testName 		= "Zaranj";
		double testLatitude 	= 30.95962;
		double testLongitude 	= 61.86037;
		double expectedScore 	= 1;
		
		CityRepository cityRepo = new CityRepository(testName, testLatitude, testLongitude);
		
		ArrayList<City> citiesFound = new ArrayList<City>(); 
		citiesFound = cityRepo.getSuggestions();

		String foundCityName = citiesFound.get(0).getCityName();
		double foundScore = citiesFound.get(0).getScore();	
		
		assertEquals(testName, 		foundCityName);
		assertEquals(expectedScore, foundScore, 0);
	}
	
	@Test
	public void testCitySearchNameLatitudeAndLongitudeList() {
		//Will return a list of 2 cities
		String testName 		= "Sydney";
		double testLatitude 	= 46.5;
		double testLongitude 	= -60.2;
		
		CityRepository cityRepo = new CityRepository(testName, testLatitude, testLongitude);
		
		ArrayList<City> citiesFound = new ArrayList<City>(); 
		citiesFound = cityRepo.getSuggestions();
		
		String expectedCity1	= testName + ", NS, CA";
		String expectedCity2	= testName + ", 02, AU";
		double expectedScore1 		= 0.97;		//Hardcoded
		double expectedScore2 		= 0.25;		//Hardcoded	

		String foundCityName1 = citiesFound.get(0).getName();
		double foundScore1 = citiesFound.get(0).getScore();	
		
		String foundCityName2 = citiesFound.get(1).getName();
		double foundScore2 = citiesFound.get(1).getScore();	
		
		assertEquals(expectedCity1, 	foundCityName1);
		assertEquals(expectedScore1, 	foundScore1 ,0);
		
		assertEquals(expectedCity2,		foundCityName2);
		assertEquals(expectedScore2, 	foundScore2 ,0);		
	}
}
