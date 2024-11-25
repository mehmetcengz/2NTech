package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MainPage;
import utilities.Driver;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class NavbarTest {
    private WebDriver driver;
    private Actions actions;
    private MainPage mainPage;

    @BeforeClass
    public void setUp() {
        driver = Driver.getDriver();
        actions = new Actions(driver);
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Test
    public void testAllMenuItems() {
        List<WebElement> mainMenus = mainPage.getNavbarElements();
        for (WebElement mainMenu : mainMenus) {
            testSubMenu(mainMenu);
            testMainMenuLink(mainMenu);
        }
    }

    private void testSubMenu(WebElement mainMenu) {
        actions.moveToElement(mainMenu).perform();
        waitForSubMenuToLoad(mainMenu);
        mainPage.getSubMenuItems(mainMenu).forEach(subMenuItem -> {
            String subMenuHref = subMenuItem.getAttribute("href");
            if (subMenuHref != null && !subMenuHref.isEmpty()) {
                clickAndVerifyUrl(subMenuItem, subMenuHref);
            }
        });
    }

    private void testMainMenuLink(WebElement mainMenu) {
        String mainMenuHref = mainMenu.getAttribute("href");
        if (mainMenuHref != null && !mainMenuHref.isEmpty()) {
            clickAndVerifyUrl(mainMenu, mainMenuHref);
        }
    }

    private void clickAndVerifyUrl(WebElement menuItem, String expectedUrl) {
        String menuLinkText = menuItem.getText().trim();
        menuItem.click();
        waitForPageToLoad();
        String currentURL = driver.getCurrentUrl();
        if (currentURL.equals(expectedUrl)) {
            System.out.println(menuLinkText + " Linki doğru yönlendirildi: " + currentURL);
        } else {
            System.out.println(menuLinkText+ " Linkde yönlendirme hatası! Beklenen: " + expectedUrl + ", Mevcut: " + currentURL);
        }
        driver.navigate().back();
        waitForPageToLoad();
    }

    private void waitForPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver -> Objects.equals(((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState"), "complete"));
    }

    private void waitForSubMenuToLoad(WebElement mainMenu) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(mainMenu, By.tagName("ul")));
    }

    @AfterClass
    public void tearDown(){
        Driver.closeDriver();
    }
}

