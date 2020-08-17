@WeatherCheck
Feature: Validating Place API's


Scenario Outline: Verify the Weather details from UI
Given User Navigate to Application
And Navigate to the Weather page
And Click on the WebPage and Provide City name "<city>"
And User Get the Weather Info

Examples:
|city|
|Indore|


  Scenario Outline: Verify the Weather details from API
    Given User hit the OpenWeatherMap API "<city>"
    When User calls weather details API with GET HTTP request
    And Validate the Status code 
 Examples:
|city|
|Indore|   


 Scenario: Verify the data from Web and API
    And User Validates the data coming From Web and API
   



