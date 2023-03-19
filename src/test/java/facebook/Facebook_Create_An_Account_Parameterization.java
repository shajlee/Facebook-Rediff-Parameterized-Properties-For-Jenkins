package facebook;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Facebook_Create_An_Account_Parameterization {
	public static WebDriver driver;
	public static ChromeOptions options;
	public static Select select;
	
	@BeforeTest
	@Parameters({"url"})
	public void launchUrl(String url) {
		options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--Incognito");
		driver =  new ChromeDriver(options);
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
	}
	@Test(priority =1)
	public void clickOnCreateANewAccountButton() throws Exception {
		driver.findElement(By.cssSelector("div._8icz+div a")).click();
		Thread.sleep(2000);
		
	}
	@Test(priority=2, dependsOnMethods = "clickOnCreateANewAccountButton")
	@Parameters({"firstnameuser", "lastnameuser","emailuser","emailconfirmationuser", "passworduser"})
	public void logInCredentials(String firstnameuser, String lastnameuser, String emailuser, String emailconfirmationuser, String passworduser) {
		WebElement userFirstName = driver.findElement(By.name("firstname"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(userFirstName)).sendKeys(firstnameuser);
		
		WebElement userLastName = driver.findElement(By.name("lastname"));
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.elementToBeClickable(userLastName)).sendKeys(lastnameuser);
		
		WebElement userEmail = driver.findElement(By.name("reg_email__"));
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait2.until(ExpectedConditions.elementToBeClickable(userEmail)).sendKeys(emailuser);
		
		
		WebElement userEmailConfirmation = driver.findElement(By.name("reg_email_confirmation__"));
		WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait3.until(ExpectedConditions.elementToBeClickable(userEmailConfirmation)).sendKeys(emailconfirmationuser);
		
		
		WebElement userPassword = driver.findElement(By.id("password_step_input"));
		WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait4.until(ExpectedConditions.elementToBeClickable(userPassword)).sendKeys(passworduser);
	}
	@Test(priority =3, dependsOnMethods = {"clickOnCreateANewAccountButton", "logInCredentials"})
	@Parameters({"birthmonth", "birthday", "birthyear"})
	public void birthDayCredentials(String birthmonth, String birthday, String birthyear) {
		select = new Select(driver.findElement(By.id("month")));
		select.selectByVisibleText(birthmonth);
		
		select = new Select(driver.findElement(By.id("day")));
		select.selectByVisibleText(birthday);
		
		select = new Select(driver.findElement(By.id("year")));
		select.selectByVisibleText(birthyear);
	}
	@Test(priority =4, dependsOnMethods = {"clickOnCreateANewAccountButton", "logInCredentials", "birthDayCredentials"})
	public void genderSelect() {
		WebElement radioButton = driver.findElement(By.className("_8esa"));
		radioButton.click();
		System.out.println(radioButton.isEnabled());
	}
	@Test(priority =5, dependsOnMethods = {"clickOnCreateANewAccountButton", "logInCredentials", "birthDayCredentials", "genderSelect"})
	public void signUpButton() {
		WebElement signupButton = driver.findElement(By.name("websubmit"));
		signupButton.click();
		System.out.println(signupButton.isEnabled());
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
		
		
		

		
	
		
		
		
	}
	


	
	

}
