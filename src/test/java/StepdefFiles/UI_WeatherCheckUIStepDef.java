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
	@Given("^User Navigate to Application$")
	public void User_Navigate_to_Application() throws Throwable {

		System.out.println("Working");
		String url = GetData("url", "PropertiesFile\\Environment.properties");
		initURL(url);
	}

	@And("^Navigate to the Weather page$")
	public void Navigate_to_the_Weather_page() throws Throwable {

		String title1 = driver.getTitle();
		System.out.println("Title of the Page is " + title1);

		waitForTheElementToVisible(pfhomepage.threedots,30);
		pfhomepage.threedots.click();
		
		waitForTheElementToVisible(pfhomepage.Weathertab,30);
		pfhomepage.Weathertab.click();
		
		String title2 = driver.getTitle();
		System.out.println("Title of the Page is " + title2);
	}

	@And("^Click on the WebPage and Provide City name \"([^\"]*)\"$")
	public void Click_on_the_WebPage_and_Provide_CityName(String cityName) throws Throwable {
		CityName= cityName;

		waitForTheElementToVisible(pfweatherpage.city_element,50);
		pfweatherpage.city_element.click();

		waitForTheElementToVisible(pfweatherpage.infoHeader,50);
		System.out.println(pfweatherpage.infoHeader.getText());

		waitForTheElementToVisible(pfweatherpage.SearchBox,50);
		pfweatherpage.SearchBox.sendKeys(CityName);
		
		WebElement searchedCity=driver.findElement(By.xpath("//label[contains(text(),'"+CityName+"')]"));
		waitForTheElementToVisible(searchedCity,50);
		searchedCity.click();
		
		WebElement searchedCityOnMap= driver.findElement(By.xpath("//div[text()='"+CityName+"']"));
		waitForTheElementToVisible(searchedCityOnMap,50);
		searchedCityOnMap.click();
	}

	@And("^User Get the Weather Info$")
	public void User_Get_the_Weather_Info() throws Throwable {

		List<WebElement> weather_data = driver.findElements(By.xpath("//span[contains(text(),'"+CityName+"')]//following::b"));
				
		for (int i = 0; i < weather_data.size(); i++) {
			String data = weather_data.get(i).getText();
			String [] weatherdata= data.split(":");
			String key=weatherdata[0].trim().replaceAll("\\s+", "_");
			String value=weatherdata[1].trim().replaceAll("\\s+", "_");
			
			System.out.println(key);
			System.out.println(value);
			SetData(key,value,"PropertiesFile\\TestData.properties");
		}
		driver.quit();
	}
}
