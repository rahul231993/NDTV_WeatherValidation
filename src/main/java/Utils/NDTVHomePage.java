package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NDTVHomePage {

	WebDriver driver;
    public NDTVHomePage(WebDriver driver){
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
    
    @FindBy(xpath="//div[@class='searchicon_wrap']//preceding::a[1]")
    public WebElement threedots;
    
    @FindBy(xpath="//a[contains(text(),'WEATHER')]")
    public WebElement Weathertab;
}
