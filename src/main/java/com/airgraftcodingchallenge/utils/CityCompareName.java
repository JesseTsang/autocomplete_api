package com.airgraftcodingchallenge.utils;

import java.util.Comparator;

import com.airgraftcodingchallenge.model.City;

public class CityCompareName implements Comparator<City> {

	@Override
	public int compare(City c1, City c2) {
		return c1.getName().compareTo(c2.getName());
	}    
}
