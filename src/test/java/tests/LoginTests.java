package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
//Login tests are maintained here
//without page object approach
public class LoginTests {
    WebDriver driver;

    @BeforeClass
    public void setUp () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void loginTest1() {
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test" + Keys.ENTER);
        String title = driver.getTitle();
        Assert.assertEquals(driver.getTitle(),"Web Orders");


    }
    @Test
    public void negativeLoginTest() {
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester2");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test2" + Keys.ENTER);
        String errorMessage = driver.findElement(By.id("ctl00_MainContent_status")).getText();
        driver.findElement(By.id("ctl00_logout")).click();
        Assert.assertEquals(driver.getTitle(), "Web Orders Login");
    }

    @Test
    public void LooutTest(){
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test" + Keys.ENTER);
        driver.findElement(By.id("ctl00_logout")).click();
        Assert.assertEquals(driver.getTitle(),"Web Orders Login");
    }



    @AfterMethod
    public void cleanUp() {
        driver.close();

    }
}
