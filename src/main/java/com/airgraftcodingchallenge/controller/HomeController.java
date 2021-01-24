package com.airgraftcodingchallenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "Hello from Spring Boot";
    }

    @RequestMapping(value = "/suggestions", method = RequestMethod.GET)
	public Cities getCityWithParam(	@RequestParam("q") String q, 
									@RequestParam(value = "latitude", required=false, defaultValue = "0") double latitude, 
									@RequestParam(value = "longitude", required=false, defaultValue = "0") double longitude ) {
		Cities suggestedCitiesList = new Cities(q, latitude, longitude);
		
		return suggestedCitiesList;		
	}    
}
