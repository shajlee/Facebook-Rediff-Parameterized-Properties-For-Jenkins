package rediff;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Rediff_Fullflow_Properties {
	public static WebDriver driver;
	public static ChromeOptions options;
	public static Properties prop;
	public static FileInputStream ips;
	
	@BeforeTest
	public void launchUrl() throws Exception {
		String path = System.getProperty("user.dir") + "\\src\\test\\java\\rediff\\RediffFullFlow.properties";
		prop = new Properties();
		ips = new FileInputStream(path);
		prop.load(ips);
		
		options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--Incognito");
		driver=new ChromeDriver(options);
		driver.get("https://rediff.com");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	}
	@Test(priority=1)
	public void clickOnSignInLink() throws Exception {
		driver.findElement(By.cssSelector(prop.getProperty("signInLinkcssSelector"))).click();
		Thread.sleep(2000);
	}
	@Test(priority=2, dependsOnMethods = "clickOnSignInLink")
	public void logInCredentials() throws Exception {
		driver.findElement(By.cssSelector(prop.getProperty("userNameBoxcssSelector"))).sendKeys(prop.getProperty("userName"));
		driver.findElement(By.cssSelector(prop.getProperty("passwordBoxcssSelector"))).sendKeys(prop.getProperty("password"));
		driver.findElement(By.cssSelector(prop.getProperty("signInButtoncssSelector"))).click();
		Thread.sleep(2000);
	}
	@Test(priority=3, dependsOnMethods = {"clickOnSignInLink", "logInCredentials"})
	public void logOut() {
		driver.findElement(By.cssSelector(prop.getProperty("logOutcssSelector"))).click();
	}
	@Test(priority=4, dependsOnMethods = {"clickOnSignInLink", "logInCredentials", "logOut"})
	public void rediffHome() {
		driver.findElement(By.cssSelector(prop.getProperty("rediffHomecssSelector"))).click();
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
		
		
		
	}
	

}
