package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class NewsPage {
    private final WebDriver driver;
    public NewsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle(){
        WebElement titleElement = driver.findElement(By.cssSelector("h1"));
        return titleElement.getText();
    }
}
