package com.airgraftcodingchallenge.model;

// import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class City{
	private String cityName;
	private String cityNameASCII;
	private String name;
	private String latitude;
	private String longitude;
	private double score;
	
	public City(String cityName, String provinceOrState, String country, String latitude, String longitude) {
		this.cityName 	= cityName;
		this.name 		= cityName + ", " + provinceOrState + ", " + country;
		this.latitude 	= latitude;
		this.longitude 	= longitude;
		score 			= 0.0;
	}

	public City() {}

	@JsonIgnore
	public String getcityNameASCII() {
		return cityNameASCII;
	}
	
	public void setCityNameASCII(String cityNameASCII) {
		this.cityNameASCII = cityNameASCII;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	@JsonIgnore
	public String getCityName(){ 
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String cityName, String provinceOrState, String country) {
		this.name = cityName + ", " + provinceOrState + ", " + country;
	}

    @Override
    public String toString() {
        return "Location{" +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", score=" + score +
                '}';
    }
}

//Sort by decreasing score
// class CityCompareScore implements Comparator<City>{
// 	public int compare(City c1, City c2) {
// 		if (c1.getScore() < c2.getScore()) {
// 			return 1;
// 		}else {
// 			return -1;
// 		}
// 	}
// }

//Sort by alphabetic order
// class CityCompareName implements Comparator<City>{
// 	public int compare(City c1, City c2) {
// 		return c1.getName().compareTo(c2.getName());
// 	}
// }