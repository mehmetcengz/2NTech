package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.NewsPage;
import utilities.Driver;


public class SearchTest {
    WebDriver driver;
    MainPage mainPage = new MainPage(Driver.getDriver());
    NewsPage newsPage = new NewsPage(Driver.getDriver());

    @BeforeClass
    public void setUp() {
        driver = Driver.getDriver();
        mainPage = new MainPage(driver);
        newsPage = new NewsPage(driver);
        mainPage.open();
    }

    @Test
    public void clickSearchButton() throws InterruptedException {
        mainPage.searchFor("İstanbul");
        WebElement newsLink = mainPage.getNewsLink(8);
        JavascriptExecutor js =(JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",newsLink);
        Thread.sleep(1000);
        newsLink.click();
        String newsPageTitle = newsPage.getTitle();
        String expectedTitle = "İletişim";
        Assert.assertTrue(newsPageTitle.contains(expectedTitle),"Haber başlığı ve içeriği yanlış" );
    }
    @AfterClass
    public void tearDown(){
        Driver.closeDriver();
    }
}