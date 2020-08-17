package Utils;

import java.util.List;
import Utils.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NDTVWeatherPage  {

	WebDriver driver;
    public NDTVWeatherPage(WebDriver driver){
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
    
    
    @FindBy(xpath="//div[text()='New Delhi']")
    public WebElement city_element;
    
    @FindBy(xpath="//div[@class='infoHolder']")
    public WebElement infoHeader;
    
    @FindBy(xpath="//span[contains(text(),'Pin your City')]//following::input[1]")
    public WebElement SearchBox;
    
    @FindBy(xpath="//div[text()='Alwar']")
    public WebElement your_city;

    @FindBy(xpath="//label[contains(text(),'Alwar')]")
    public WebElement ClickonCity;
    
    @FindBy(xpath="//span[contains(text(),'Alwar')]//following::b")
    public List<WebElement> WeatherInfo;
    

}

