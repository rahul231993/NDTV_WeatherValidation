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
	}
//Navigation till NDTV Weather Page
	@And("^Navigate to the Weather page$")
	public void Navigate_to_the_Weather_page() throws Throwable {

		String title1 = driver.getTitle();
		System.out.println("Title of the Page is " + title1);
//Applying the Wait for 30 Second 
		waitForTheElementToVisible(pfhomepage.threedots,30);
		pfhomepage.threedots.click();
//Applying the Wait for 30 Second 		
		waitForTheElementToVisible(pfhomepage.Weathertab,30);
		pfhomepage.Weathertab.click();
//Fetching the Title of the Weather Page after Navigation 		
		String title2 = driver.getTitle();
		System.out.println("Title of the Page is " + title2);
		
	}

	@And("^Click on the WebPage and Provide City name \"([^\"]*)\"$")
	public void Click_on_the_WebPage_and_Provide_CityName(String cityName) throws Throwable {
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
//Clicking on the Searched City on Weather Map 		
		WebElement searchedCityOnMap= driver.findElement(By.xpath("//div[text()='"+CityName+"']"));
		waitForTheElementToVisible(searchedCityOnMap,50);
		searchedCityOnMap.click();
	}

	@And("^User Get the Weather Info$")
	public void User_Get_the_Weather_Info() throws Throwable {
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
