package calculator;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SeleniumTester {

    private static ChromeDriver driver;

    /**
     * Opens the driver browser.
     * Is done once, before all the tests run.
     */
    @BeforeAll
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
//        driver.manage().deleteAllCookies();
    }

    /**
     * Tests a Google search by typing a query, submitting the form and asserting the result page title.
     */
    @Test
    public void chrome_Google_Search_ReturnsResults(){
        driver.get("https://www.bing.com/");
        WebElement billederLink = driver.findElement(By.xpath("//a[@href='/images?FORM=Z9LH']"));
        billederLink.click();

// Wait for the Images page to load

// Find the search box and enter the search query
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Hades angry face Hercules");
        searchBox.submit();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleContains("Hades angry face Hercules"));

        WebElement firstImage = driver.findElement(By.cssSelector("img.mimg"));

// click on the image to open it
        firstImage.click();

// Wait for the image to load

        assert (driver.getTitle().contains("Hades angry face Hercules - Bing images"));

    }

    /**
     * Tests a web shop by adding the first product to the shopping cart, asserting a product in the cart.
     */
/*    @Test
    public void chrome_ShoppingCart_AddFirstProduct_ReturnsOneInCart(){

        driver.get("https://www.amazon.com/");

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Java book");
        searchBox.submit();

        WebElement firstResult = driver.findElement(By.xpath("[data-cel-widget='search_result_6']"));
        firstResult.click();

        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();

        String items = driver.findElement(By.id("nav-cart-count")).getText();

        saveScreenshot(driver, "selenium-amazon-shoppingcart-" + getDate() +".png");

        Assert.assertTrue( "1".equals(items));
    }*/

 /*   @Test
    public void chrome_W3Schools_LearnJavascript_LocateElement(){

        driver.get("https://www.w3schools.com/");


        WebElement acceptAllButton = driver.findElement(By.xpath("//div[@class='sn-b-def sn-blue' and @id='accept-choices']"));
        if (acceptAllButton.isDisplayed()) {
            acceptAllButton.click();
        }

        WebElement learnJavaScriptLink = driver.findElement(By.xpath("//a[@href='/js/default.asp' and @class='w3-button w3-block tut-button']"));
        learnJavaScriptLink.click();

// Wait for the Learn JavaScript page to load
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.titleContains("JavaScript Tutorial"));

// Assert that the Learn JavaScript link is displayed
        Assert.assertTrue(learnJavaScriptLink.isDisplayed());
    }*/

    private void saveScreenshot(RemoteWebDriver driver, String fileName) {
        File s = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotFileName = fileName;
            FileUtils.copyFile(s, new File(screenshotFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @AfterAll
    public void closeBrowser(){
        driver.quit();
    }
}