package StepdefFiles;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utils.BaseClass;
import Utils.NDTVHomePage;
import Utils.NDTVWeatherPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class UI_WeatherCheckUIStepDef extends BaseClass {
	
private NDTVHomePage pfhomepage= 	PageFactory.initElements(driver, NDTVHomePage.class);
private NDTVWeatherPage pfweatherpage= 	PageFactory.initElements(driver, NDTVWeatherPage.class);
public String CityName= "";	

//Fetching the URL from Properties file and hitting the Website after Initializing the WebDriver
	@Given("^User Navigate to Application$")
	public void User_Navigate_to_Application() throws Throwable {
		String url = GetData("url", "PropertiesFile\\Environment.properties");
		initURL(url);
		takeSCreenshot("NDTVHomePage");
	}
	
//Navigation till NDTV Weather Page,
	@And("^Navigate to the Weather page$")
	public void Navigate_to_the_Weather_page() throws Throwable {

		String title1 = driver.getTitle();
		System.out.println("Title of the Page is " + title1);
		waitForTheElementToVisible(pfhomepage.threedots,30);
		pfhomepage.threedots.click();
		waitForTheElementToVisible(pfhomepage.Weathertab,30);
		pfhomepage.Weathertab.click();
//Fetching the Title of the Weather Page after Navigation 		
		String title2 = driver.getTitle();
		System.out.println("Title of the Page is " + title2);
		takeSCreenshot("WeatherHomePage");
		
	}
	
	

	@And("^User search the desired City name \"([^\"]*)\" in the Search box$")
	public void User_Search_The_Desired_City_Name_In_The_SearchBox(String cityName) throws Throwable {
		CityName= cityName;
//Clicking on any random Value on Weather Map
		waitForTheElementToVisible(pfweatherpage.city_element,50);
		pfweatherpage.city_element.click();
//Moving to Search Box Section and search for a given city name 		
		waitForTheElementToVisible(pfweatherpage.infoHeader,50);
		System.out.println(pfweatherpage.infoHeader.getText());
		waitForTheElementToVisible(pfweatherpage.SearchBox,50);
		pfweatherpage.SearchBox.sendKeys(CityName);
		WebElement searchedCity=driver.findElement(By.xpath("//label[contains(text(),'"+CityName+"')]"));
		waitForTheElementToVisible(searchedCity,50);
		searchedCity.click();
		takeSCreenshot("SearchedCity");
	}

	@And("^User click on the searched city on Map with City name as \"([^\"]*)\"$")
	public void User_click_on_the_searched_city_on_Map(String cityName) throws Throwable {
		CityName= cityName;
//Clicking on the Searched City on Weather Map 		
		WebElement searchedCityOnMap= driver.findElement(By.xpath("//div[text()='"+CityName+"']"));
		waitForTheElementToVisible(searchedCityOnMap,50);
		searchedCityOnMap.click();
		takeSCreenshot("DesiredCityWeatherDetails");
	}
	
	
	
	@And("^User Store the WeatherInfo to Properties file$")
	public void User_Stor_the_WeatherInfo_to_Properties_file() throws Throwable {
//Fetching all the Weather details for a city and storing it into a List of WebElement
		List<WebElement> weather_data = driver.findElements(By.xpath("//span[contains(text(),'"+CityName+"')]//following::b"));

//Traversing the Above list and Storing Weather information into TestData.properties File		
		for (int i = 0; i < weather_data.size(); i++) {
			String data = weather_data.get(i).getText();
			String [] weatherdata= data.split(":");
			String key=weatherdata[0].trim().replaceAll("\\s+", "_");
			String value=weatherdata[1].trim().replaceAll("\\s+", "_");
			
			System.out.println(key);
			System.out.println(value);
			SetData(key,value,"PropertiesFile\\TestData.properties");
		}
	}
}
