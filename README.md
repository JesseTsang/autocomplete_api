## Introduction

- This is a deliverable for the Airgraft coding challenge. Details of the challenge please see the PDF file.

## About the API Algorithm

1. It will first find all the cities that contain the search string from the file (cities15000.txt in this example). 
2. It will also check against the city's formal name and its ASCII name (name without any accent like "Montréal" and "Montreal").
3. If the ratio of the search string and the found city name is within trehsold then the city is added to a list for further processing.
4. Then, if search string and city name is a 100% match, even if no latitude and longitude is passed in argument, score is set to 1.
5. If search string and city name is NOT 100% match, and no latitude and longitude is passed in argument, score is set based on a % of extra letters (more letters = lower score);
6. If latitude and longitude is passed in argument, then the algorithm will calculate the distance between the parameters and the city's data. A weight will then applied in a ratio of x% to distance and y% to name match.
7. Return result

## How to test the API

- Codes are tested with Visual Studio Codes, Chrome, and Postman
- I run the project with Spring Boot Dashboard addon
- Then, go to your favourite browser and use http://localhost:8085/suggestions?q={cityName} to start

## Sample API Queries

- http://localhost:8085/suggestions?q=Londo&latitude=43.70011&longitude=-79.4163
- http://localhost:8085/suggestions?q=SomeRandomCityInTheMiddleOfNowhere
- http://localhost:8085/suggestions?q=Montreal
- http://localhost:8085/suggestions?q=Montréal

## Notes

- Not all locations are presented due to file size. Only cities with >= 15,000 population can be searched. 
- A xlsx version of the cities list are enclosed in the resources folder.

## Possible Improvements to Make Better Suggestion

- Mechanism that can check if the search string is a possible typo of a location
- Additional parameters such to support state or province to refine the search
- Allow custom origin, maybe the user would want to search for London, UK, but he/she is in Canada, then returning London, Canada would be less accurate
- Allow radius, if the location's name is common or the location is a shop or restaurant, then a radius search would refine the result
