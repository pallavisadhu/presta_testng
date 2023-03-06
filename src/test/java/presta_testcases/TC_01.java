package presta_testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TC_01 {
	
	String url="http://3.130.25.128/administration/index.php/sell/orders/new?_token=eZQR-Oao8Cnq3TNjXuGqwGpzNbL36XqhSFIvg8JjXTU";
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void init() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get(url);
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		
	}

	
	@Test(priority=1)
	public void verifyNavigationToDashboard() {
		driver.findElement(By.id("email")).sendKeys("admin@presta.com");
		driver.findElement(By.id("passwd")).sendKeys("Welcome123");
		driver.findElement(By.id("submit_login")).click();
		String actual = wait.until(ExpectedConditions.presenceOfElementLocated
				(By.xpath("//ul[@class='breadcrumb page-breadcrumb']//following-sibling::h1"))).getText();
		Assert.assertEquals(actual, "Dashboard");
		
	}
	
	@Test(priority=2)
	public void verifyEmailId() {
		wait.until(ExpectedConditions.presenceOfElementLocated
				(By.xpath("//i[text()='shopping_basket']//following-sibling::span"))).click();
		driver.findElement(By.xpath("//ul[@id='collapse-3']//li[@id='subtab-AdminOrders']//a")).click();
		driver.findElement(By.xpath("//a[@title='Add new order']//following-sibling::i")).click();
		driver.findElement(By.id("customer-search-input")).sendKeys("john deo");
		String email = wait.until(ExpectedConditions.presenceOfElementLocated
				(By.xpath("//div[@class='row js-customer-search-results']//p[@class='mb-0 js-customer-email']"))).getText();
		Assert.assertEquals(email, "pub@prestashop.com");
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
