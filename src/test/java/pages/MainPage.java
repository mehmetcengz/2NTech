package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void open(){Driver.getDriver().get(ConfigReader.getProperty("mainPageURL"));}

    public List<WebElement> getNavbarElements() {
        return driver.findElements(By.cssSelector("#menu-1-5dc673f1 > li"));
    }

    public List<WebElement> getSubMenuItems(WebElement dropdownElement) {
        try {
            return dropdownElement.findElement(By.cssSelector(".sub-menu"))
                    .findElements(By.cssSelector("a"));
        } catch (NoSuchElementException e) {
            return List.of();
        }
    }

    @FindBy(xpath = "//*[@id=\"cmsmasters-scroll-top\"]/div/div/div/section/div/div[3]/div/div[2]/div/div")
    public WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"cmsmasters-scroll-top\"]/div/div/div/section/div/div[3]/div/div[2]/div/div/div[1]/div/form/div/input")
    public WebElement searchBar;

    public void searchFor(String searchText){
        searchButton.click();
        searchBar.sendKeys(searchText);
        searchBar.sendKeys(Keys.ENTER);
    }
    public WebElement getNewsLink(int index){
        return driver.findElement(By.xpath("(//h3[@class='entry-title cmsmasters-widget-title__heading']/a)[" + index + "]"));
    }
}
