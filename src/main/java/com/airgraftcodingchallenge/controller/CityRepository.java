package com.airgraftcodingchallenge.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import com.airgraftcodingchallenge.model.City;
import com.airgraftcodingchallenge.utils.CanadianProvinceUtil;
import com.airgraftcodingchallenge.utils.CityCompareName;
import com.airgraftcodingchallenge.utils.CityCompareScore;
import com.airgraftcodingchallenge.utils.MathUtils;

public class CityRepository {
	
	private static final String FILE_PATH_GITHUB 				= "https://raw.githubusercontent.com/JesseTsang/autocomplete_api/master/src/main/resources/cities15000.txt";
	private static final double COEFF_SCORE_CITY_ONLY 			= 0.8;
	private static final double WEIGHT_SCORE_CITY_NAME_MATCHES 	= 0.25;
	private static final double WEIGHT_SCORE_CITY_LAT_LONG 		= 0.75;
	private static final double COEFF_SCORE_CITY 				= 0.9;
	
	private ArrayList<City> cities = new ArrayList<City>();
	private String cityToSearch;
	private double latitude;
	private double longitude;
	private Search citySearch;
	
	public CityRepository(String cityToSearch, double latitude, double longitude) {
		this.cityToSearch 	= cityToSearch;
		this.latitude 		= latitude;
		this.longitude 		= longitude;
		
		//Load cities data from the file (cities15000.txt)
		try {
			readCityList(cities); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		citySearch = new CitySearchPercentageCloseness();			
	}
	
	
	public ArrayList<City> getSuggestions(){
		//cities = cities from data file
		//cityToSearch = search parameter 
		//Filter all cities for names that matches at least 50% of cityToSearch name length
		ArrayList<City> newCityList = citySearch.search(cities, cityToSearch);
		
		newCityList = giveScoreToCities(newCityList, cityToSearch, latitude, longitude);
		
		//Sort by score
		CityCompareScore cityScoreComparator = new CityCompareScore();
		Collections.sort(newCityList, cityScoreComparator); 
		
		return newCityList;				
	}
	
	private ArrayList<City> giveScoreToCities(ArrayList<City> potentialCityList, String cityToSearch, double latitude, double longitude){
		//If name matches 100% and no latitude and longitude is passed in argument, score is set to 1.
		//If name doesn't match 100% and no latitude,longitude is passed in argument, score is given by a % of extra letters
		//WEIGHT_SCORE_CITY_ONLY^howManyLettersMore is used. following a graph similar to y=0.8^x, where x is nb of extra letters. 
		
		//If lat and long are used, distance from the city to search is calculated from each city found 
		//and a weight is applied in a ratio of x% to distance y% to name match
		setScoreWithNameMatch(potentialCityList,cityToSearch);
		
		if (latitude != 0.0 && longitude != 0.0) {
			setScoreWithLongNLat(potentialCityList, cityToSearch, latitude, longitude);	
		}
		
		return potentialCityList;
	}
	
	private void setScoreWithLongNLat(ArrayList<City> potentialCityList, String cityToSearch, double latitude, double longitude) { 	
		//Adjust score according to distance
		for (int i = 0; i < potentialCityList.size(); i++) {
			double scoreForNameMatch = potentialCityList.get(i).getScore();

			double y1 = Double.parseDouble(potentialCityList.get(i).getLatitude());
			double x1 = Double.parseDouble(potentialCityList.get(i).getLongitude());
			double distanceFromOrigin = MathUtils.distanceCity(y1, x1, latitude, longitude);
			
			double scoreFromDistance = Math.pow(COEFF_SCORE_CITY, distanceFromOrigin);
			double finalScore = scoreForNameMatch * WEIGHT_SCORE_CITY_NAME_MATCHES + scoreFromDistance * WEIGHT_SCORE_CITY_LAT_LONG;
		
			potentialCityList.get(i).setScore(MathUtils.round(finalScore, 2));
		}
	}

	private void setScoreWithNameMatch(ArrayList<City> potentialCityList, String cityToSearch) {
		for (int i = 0; i < potentialCityList.size(); i++) {
			if (potentialCityList.get(i).getCityName().length() == cityToSearch.length()) {
				potentialCityList.get(i).setScore(1.0);
			}else {
				int howManyLettersMore = potentialCityList.get(i).getCityName().length() - cityToSearch.length();
				potentialCityList.get(i).setScore(MathUtils.round(Math.pow(COEFF_SCORE_CITY_ONLY,howManyLettersMore), 2));
			}
		}		
	}
	
	private void readCityList(ArrayList<City> cities) throws IOException {
		BufferedReader reader = null;
		
		try {
			URL url = new URL(FILE_PATH_GITHUB);
			
			HttpURLConnection githubHttp = (HttpURLConnection) url.openConnection();
			if (githubHttp.getResponseCode() == 200) {
				InputStream inputStream = githubHttp.getInputStream();
				reader = new BufferedReader(new InputStreamReader(inputStream));
			}
			
			// Read first line
			String line = reader.readLine(); 

			Boolean isFirstLine = true;
			String cityName;
			String cityNameASCII;			
			String latitude;
			String longitude;
			String country;
			String provinceOrState;
			

			while(line != null) {

				// Skip header row
				if(!isFirstLine) {
					String[] columns = line.split("\\t");
				
					cityName = columns[1];
					cityNameASCII = columns[2];
					latitude = columns[4];
					longitude = columns[5];
					country = columns[8];
					provinceOrState = columns[10];

					if (country.equals("CA")) {
						provinceOrState = CanadianProvinceUtil.getCanadianProvinceAbvr(provinceOrState);
					}

					City city = new City(cityName, provinceOrState, country, latitude, longitude);
					city.setCityNameASCII(cityNameASCII);

					cities.add(city);
				}

				isFirstLine = false;
				line = reader.readLine();
			}			
		} catch (FileNotFoundException e) {
			System.out.println("There was a problem reading city file : " + e);
			e.printStackTrace();
		}	
		
		CityCompareName cityNameComparator = new CityCompareName();
		Collections.sort(cities, cityNameComparator);
	}
}
