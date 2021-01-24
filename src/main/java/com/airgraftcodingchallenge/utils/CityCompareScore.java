package com.airgraftcodingchallenge.utils;

import java.util.Comparator;

import com.airgraftcodingchallenge.model.City;

public class CityCompareScore implements Comparator<City> {

	@Override
	public int compare(City c1, City c2) {
		if (c1.getScore() < c2.getScore()) {
			return 1;
		}else {
			return -1;
		}
	}    
}
