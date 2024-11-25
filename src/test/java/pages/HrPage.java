package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;

public class HrPage {
    public HrPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void open() {
        Driver.getDriver().get(ConfigReader.getProperty("hrPageURL"));
    }

    @FindBy(xpath = "//*[@id='name']")
    public WebElement idField;

    @FindBy(xpath = "//*[@id='birth']")
    public WebElement birthField;

    @FindBy(xpath = "//*[@id='tcKimlik']")
    public WebElement tcKimlikField;

    @FindBy(xpath = "//*[@id='phone']")
    public WebElement phoneField;

    @FindBy(xpath = "//*[@id='email']")
    public WebElement emailField;

    @FindBy(id = "cv_field")
    public WebElement cvField;

    @FindBy(css = "button.py-3")
    public List<WebElement> educationButtons;

    @FindBy(css = "[type='submit']")
    public WebElement nextPageButton;

    @FindBy(css = "div.flex.flex-col.cursor-pointer")
    public List<WebElement> pozisionButtons;

    @FindBy(xpath = "//div[@class='text-white flex justify-center items-center text-[14px] py-2 px-4 rounded-full bg-[#DF1F29] cursor-pointer']")
    public WebElement gonderButton;

    @FindBy(xpath = "//p[contains(text(),'Form Başarı ile gönderildi')]")
    public WebElement successMessage;
}
