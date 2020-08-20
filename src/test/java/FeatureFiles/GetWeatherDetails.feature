@WeatherCheck
Feature: Validate the Weather Details From Web and API for NDTV Weather Page

  Scenario Outline: Fetching the Weather details from NDTV WeatherPage UI for a city
    Given User Navigate to Application 
	And Navigate to the Weather page 
	And Click on the WebPage and Provide City name "<city>" 
	And User Get the Weather Info 

Examples:
|city|
|Pune|

  Scenario Outline: Fetching the Weather details from OpenWeatherMap API for a City
    Given User hit the OpenWeatherMap API "<city>" 
	When User calls weather details API with GET HTTP request 
	And Validate the Status code 
 Examples:
|city|
|Pune|   

 Scenario: Validate Weather information from  NDTV Weather WEb Page and OpenWeatherMap API
    And User Validates the data coming From Web and API
   



