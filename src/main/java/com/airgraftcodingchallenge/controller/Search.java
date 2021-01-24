package com.airgraftcodingchallenge.controller;

import java.util.ArrayList;

import com.airgraftcodingchallenge.model.City;

public interface Search {
	
	public ArrayList<City> search(ArrayList<City> cities, String name);
}
