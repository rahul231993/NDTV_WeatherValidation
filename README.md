# NDTV_WeatherValidation

Agenda:
fetch the  Weather information of a city from UI(https://www.ndtv.com/) and API(https://openweathermap.org) and compare Temperature Details with a variance.

Framework Description:
Cucumber JUnit Framefork for UI and API automation where wew are using Java, Selenium, RestAssured and Extent Reporting with capturing Screenshots.
Below components are used in the Framework:

1. Feature File:
   Scenarios are maintained in the GetWeatherDetails.feature  file with multiple steps.(1 for UI, 1 for API and 1 for Comparision)

2. Step Definition Files:
  UI_WeatherCheckUIStepDef- For Prviding Step definition for UI Steps 
  API_WeatherStepDef -For Prviding Step definition for API Steps 

3. Object Repository:
  Page Object Approach is used in the Framework and WebElemts for the Pages are maintained in the below files seperatedly, which are being accessed in through PageFactory class in Step def files.
   NDTVHomePage
   NDTVWeatherPage

4. Base Class 
   A reusable class is maintained in the Framework and all the reusable methods like- Wait method, delete method, Browser initialization activities are maintained here.

5. Reporting:
   Extent Reports are implemented in the Framework, Reports are being generated in the Target Folder.

6. Screenshots:
   Screenshot folder is being maintained in the Framework where we captoring the Screenshots at varios steps.
    Delete Files method is being invoked to get this folder empty before every run.

7. Pom.xml
 We have maintained Maven project, hence maintaing Pom.xml file and all the required  dependecy and plugins in this. 

8. Properties File:
  for storing the data seperately, these files are maintained and we are fetching the data at run time.
  For comparision : we have stored the data from UI and API to Properties file and then comapred that info.



