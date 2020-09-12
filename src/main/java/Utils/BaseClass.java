package Utils;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver; 
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.utils.FileUtil;

import gherkin.lexer.Sr_cyrl;


public class BaseClass {
public BaseClass(){
		
	}
	
    public static WebDriver driver;
    public static FileInputStream fis;
    public static  Properties prop;
    public static  WebDriverWait wait;
    
    
	public void WebDriverInit(String Browser) throws IOException {
		if(Browser.equalsIgnoreCase("Chrome")) {
			deleteScreenshotFolder();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
			driver = new ChromeDriver(options);
			
		}
		
		else if(Browser.equalsIgnoreCase("IE")) {
			
		}
	
		else if(Browser.equalsIgnoreCase("FireFox")) {
		
	}
		
	}
	
	public void LoadProperties(String path) throws FileNotFoundException {
		 fis = new FileInputStream(path);
		 prop= new Properties();
		 try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String GetData(String Key, String path) throws FileNotFoundException {
		LoadProperties(path);
		String Value = prop.getProperty(Key);
		return Value;
	}
	
	public void SetData(String Key, String Value, String path) throws FileNotFoundException {
		LoadProperties(path);
		prop.setProperty(Key, Value);
		StoreData(path);
		
	}
	
	public void StoreData(String path) throws FileNotFoundException {
		FileOutputStream fos = new FileOutputStream(path);
		try {
			prop.store(fos, "This prop stored");
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	public void initURL(String URL) throws FileNotFoundException {
          driver.get(URL);
          driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
          driver.manage().window().maximize();
	}
	
	public void waitForTheElementToVisible(WebElement Element, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
	     wait.until(ExpectedConditions.visibilityOf(Element));
	}
	
	
	public void takeSCreenshot(String fileName) throws IOException {
		TakesScreenshot scrShot =((TakesScreenshot)driver);
	    File srcFile= scrShot.getScreenshotAs(OutputType.FILE);
	    String location = "Print\\Screenshots";
	    File DestFile=new File(location + fileName + "_" + ".png");
	    FileUtils.copyFile(srcFile, DestFile);
	}
	
	public static void deleteScreenshotFolder() throws IOException {
		
		String filePath = "Print";
	      File file = new File(filePath);
	      FileUtils.deleteDirectory(file);
	      System.out.println("Files deleted........");
		
	}
	
	public void AssertWithScreenshot() {
		System.out.println("Need to write code here");
		System.out.println("Changes Commited by others");
	}
	
	
	
}
