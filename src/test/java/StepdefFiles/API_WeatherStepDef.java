package StepdefFiles;

import static org.junit.Assert.*;

import Utils.BaseClass;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import junit.framework.Assert;
//import org.junit.Assert;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class API_WeatherStepDef extends BaseClass{

public	RequestSpecification request;
public	Response response;
public JsonPath js;
	
	@Given("^User hit the OpenWeatherMap API \"([^\"]*)\"$")
	public void user_hit_the_OpenWeatherMap_API( String city) throws Throwable {
		RestAssured.baseURI = "http://api.openweathermap.org";
		String AuthKey=GetData("AuthKey", "PropertiesFile\\TestData.properties");
		request = given().log().all().queryParam("q", city).queryParam("appid", AuthKey);
		
	}

	@When("^User calls weather details API with GET HTTP request$")
	public void user_calls_weather_details_API_with_GET_HTTP_request() throws Throwable {
		response=request.when().get("data/2.5/weather").then().assertThat().log().all().statusCode(200).extract().response();
		js = new JsonPath(response.asString());
		String rain = js.getString("weather.main");
		String humidity= js.getString("main.humidity");
		System.out.println("Value of Rain obj is "+rain);
		System.out.println("Value of Humidity is "+humidity);
		SetData("Humidity_API",humidity,"PropertiesFile\\TestData.properties");
		SetData("Rain_API",rain,"PropertiesFile\\TestData.properties");
	}
	
	@And("Validate the Status code")
	public void Validate_The_Status_Code() throws Throwable {
		assertEquals(response.getStatusCode(), 200);
		
	}
	
	@And("User Validates the data coming From Web and API")
	public void User_Validates_the_data_coming_From_Web_and_API() throws Throwable {
		String Web_Humidity=GetData("Humidity", "PropertiesFile\\TestData.properties");
		String API_Humid=GetData("Humidity_API", "PropertiesFile\\TestData.properties");
		assertEquals(Web_Humidity, API_Humid);
	}
	
}
