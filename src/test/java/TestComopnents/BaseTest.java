 package TestComopnents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public 	WebDriver driver;
	public WebDriver intializeDriver() throws IOException{
	Properties prop= new Properties();
	FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//GlobalData.properties");
    prop.load(fis);
	String browserName=prop.getProperty("browser");
	
		if(browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
	
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
	}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	 public void launchMyApplication() throws IOException {
		driver= intializeDriver();
	 }
}

