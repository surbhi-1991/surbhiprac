import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bson.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebScrapTest {
	
	WebDriver driver;
	MongoCollection<Document> webCollection;
	
//	public void WebSrapTest(WebDriver driver){
//		this.driver=driver;
//		
//	}
	
	
	@BeforeSuite
	public void CoonectMangoDB() {
		
		Logger mongoLogger= Logger.getLogger("org.mongodb.driver");
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database=mongoClient.getDatabase("autoDB");
		webCollection = database.getCollection("web");
	}
	
	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
//		ChromeOptions co= new ChromeOptions();
//		co.addArguments("--headless--");
//		driver= new ChromeDriver(co);
	}
//	@DataProvider
//	public Object[][] getWebData(){
//		return new Object[][] {
//			{"https://www.amazon.com"},
//			{"https://www.walmart.com"}
//		};
//	}
	
	@Test//(dataProvider="getWebData")
	public void webScrapTest(String aapurl) {
		driver.get("http://www.amazon.com");
		String url = driver.getCurrentUrl();
		String title = driver.getTitle();
		
		Document d1 = new Document();
		d1.append("url", url);
		d1.append("title", title);
		List<Document> doclist= new ArrayList<Document>();
		doclist.add(d1);
		webCollection.insertMany(doclist);
		
	}
	
	
//	@AfterTest
//	public void tearDown() {
//		driver.quit();
//	}
	

}
